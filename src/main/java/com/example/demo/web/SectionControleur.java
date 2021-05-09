package com.example.demo.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Section;
import com.example.demo.entities.Site;
import com.example.demo.services.SectionService;
import com.example.demo.services.SiteService;

@Controller
@RequestMapping("/sections")
public class SectionControleur {
	@Autowired
	private SectionService sectionService;
	@Autowired
	private SiteService siteService;
    @RequestMapping("/Index")
	public String Index(Model modele) {
    	List<Section> sections=sectionService.sections();
    	modele.addAttribute("sections",sections);
		return "IndexSection";
	}
    @RequestMapping("/FormSection")
    public String FormSection(Model modele) {
    	List<Site> sites=siteService.sites();
    	modele.addAttribute("sites",sites);
    	modele.addAttribute("section",new Section());
    	return "FormSection";
    }
    @RequestMapping(value="/FormSection", method=RequestMethod.POST)
    public String Sauver(@RequestParam(name="site")int reference ,@Valid Section section,Errors errors)
    {
    	if(errors.hasErrors()) {return "FormSection";}
    	Site site=siteService.UnSite(reference);
        section.setSite(site);
    	sectionService.AjouterSection(section);
    	return "redirect:/sections/Index";
    }
    @RequestMapping(value="/Editer")
    public String Edition(@RequestParam(name="idSection")int id,Model modele)
    {
    	List<Site> sites=siteService.sites();
    	Section section=sectionService.UneSection(id);
    	modele.addAttribute("sites",sites);
    	modele.addAttribute("section",section);
    	return "FormUpdateSection";
    }
    @RequestMapping(value="/ModifierSection", method=RequestMethod.POST)
    public String Modifier(@Valid Section section,Errors errors)
    {
    	if(errors.hasErrors()) {return "FormUpdateSection";}
    	sectionService.AjouterSection(section);
    	return "redirect:/sections/Index";
    }
    @RequestMapping("/DeleteSection")
    public String Supprimer(@RequestParam(name="idSection") int id) {
    	sectionService.SupprimerSection(id);
    	return "redirect:/sections/Index";
    }
    @RequestMapping(value="/Details")
    public String Details(@RequestParam(name="idSection")int id,Model modele)
    {
    	Section section=sectionService.UneSection(id);
    	modele.addAttribute("section",section);
    	return "DetailsSection";
    }
}

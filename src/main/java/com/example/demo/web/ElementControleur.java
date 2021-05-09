package com.example.demo.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Element;
import com.example.demo.entities.Section;
import com.example.demo.services.ElementService;
import com.example.demo.services.SectionService;

@Controller
@RequestMapping("elements")
public class ElementControleur {
	@Autowired
    private ElementService elementService; 
	@Autowired
	private SectionService sectionService;
	@Value("${dir.imagesElement}")
	private String imagesDir;
	@RequestMapping("/Index")
	public String Index(Model modele) {
		List<Element> elements=elementService.Elements();
		modele.addAttribute("elements",elements);
		return "IndexElement";
	}
	@RequestMapping("/Details")
	public String Details(@RequestParam(name="id") int id, Model modele) {
		Element element=elementService.UnElement(id);
		modele.addAttribute("el",element);
		return "DetailsElement";
	}
	@RequestMapping("FormElement")
	public String FormElement( Model modele ) {
		List<Section> sections=sectionService.sections();
		modele.addAttribute("sections",sections);
		modele.addAttribute("element",new Element());
		return "FormElement";
	}
	@RequestMapping(value="FormElement",method=RequestMethod.POST)
    public String Sauver(@Valid Element element,BindingResult errors,
    		@RequestParam(name="photo") MultipartFile file) throws Exception, IOException {
		if(errors.hasErrors()) {
			return "FormElement";
		}
		elementService.AjouterElement(element);
		if(!(file.isEmpty())) {
    		file.transferTo(new File(imagesDir+element.getReference()));
    	}
		return "redirect:/elements/Index";
	}
	@RequestMapping("/Edit")
	public String Edit(@RequestParam(name="reference") int id,
			Model modele) {
		List<Section> sections=sectionService.sections();
		Element element=elementService.UnElement(id);
		modele.addAttribute("sections",sections);
		modele.addAttribute("element",element);
		
		return "FormUpdateElement";
	}
	@RequestMapping("/Update")
	public String Modifier(@Valid Element element,Errors errors,
			@RequestParam(name="photo") MultipartFile file) throws Exception, IOException {
		if(errors.hasErrors()) {
			return "FormUpdateElement";
		}
		elementService.AjouterElement(element);
		if(!(file.isEmpty())) {
    		file.transferTo(new File(imagesDir+element.getReference()));
    	}
		return "redirect:/elements/Index";
	}
	@RequestMapping("/Delete")
	public String Supprimer(@RequestParam(name="id") int id ) {
		elementService.SupprimerElement(id);
		return "redirect:/elements/Index";
	}
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(Long id) throws IOException {
    	return Files.readAllBytes(Paths.get(imagesDir+id));
    }
}

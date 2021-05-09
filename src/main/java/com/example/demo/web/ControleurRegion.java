package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Region;
import com.example.demo.services.RegionService;
@RestController
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/api")
public class ControleurRegion {
	@Autowired
	private RegionService regionService;
	
    @GetMapping(value="/regions")
	public List<Region> Index(){
		return regionService.regions();
	}
    
    @GetMapping("/regions/{id}")
  	public Region Index1(@PathVariable int id){
  		return regionService.UneRegion(id);
  	}
    @PostMapping(value="/regions")
	public Region Ajouter(@RequestBody Region r){
		return regionService.AjouterRegion(r);
	}
    @PutMapping("/regions/{id}")
	public Region Modifier(@RequestBody Region r,@PathVariable int id){
		r=regionService.UneRegion(id);
    	return regionService.AjouterRegion(r);
	}
    @DeleteMapping("regions/{id}")
   	public void Supprimer(@PathVariable int id){
       	regionService.SupprimerRegion(id);
   	}
}

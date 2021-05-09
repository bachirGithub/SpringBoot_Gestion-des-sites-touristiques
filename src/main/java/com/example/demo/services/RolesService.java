package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RolesRepository;
import com.example.demo.dao.RolesRepository;
import com.example.demo.entities.Roles;
@Service
public class RolesService {
	@Autowired
	private RolesRepository rolesRepository;
    
	public List<Roles> Userss()
	{
	   return rolesRepository.findAll();	
	}
	
	public Roles UnRoles(String id) 
	{
		return rolesRepository.findById(id).get();
	}
	public Roles AjouterUsers(Roles s) 
	{
		return rolesRepository.save(s);
	}

	public void SupprimerUsers(String id) 
	{
		rolesRepository.deleteById(id);
	}
}

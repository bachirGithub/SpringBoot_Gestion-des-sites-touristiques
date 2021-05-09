package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UsersRepository;
import com.example.demo.entities.Users;
@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
    
	public List<Users> Userss()
	{
	   return usersRepository.findAll();	
	}
	
	public Users UnUsers(String id) 
	{
		return usersRepository.findById(id).get();
	}
	public Users AjouterUsers(Users s) 
	{
		return usersRepository.save(s);
	}

	public void SupprimerUsers(String id) 
	{
		usersRepository.deleteById(id);
	}
}

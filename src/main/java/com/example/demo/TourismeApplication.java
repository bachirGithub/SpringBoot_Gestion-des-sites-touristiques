package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.SiteRepository;
import com.example.demo.entities.Region;
import com.example.demo.entities.Roles;
import com.example.demo.entities.Site;
import com.example.demo.entities.Users;
import com.example.demo.services.RegionService;
import com.example.demo.services.RolesService;
import com.example.demo.services.SiteService;
import com.example.demo.services.UsersService;

@SpringBootApplication
public class TourismeApplication implements CommandLineRunner{
   @Autowired
   private UsersService userService;
   @Autowired
   private RolesService rolesService;
	public static void main(String[] args) {
		SpringApplication.run(TourismeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    /*Roles role=new Roles("USER");
	    Roles role1=new Roles("ADMIN");
	    rolesService.AjouterUsers(role);
	    rolesService.AjouterUsers(role1);
	    Collection<Roles> roles=new ArrayList<Roles>();
	    Collection<Roles> roles1=new ArrayList<Roles>();
	    roles.add(role);roles.add(role1);
	    roles1.add(role);
	     Users users=new Users("AExpress", "1234", 1, roles);
	     Users users1=new Users("taj", "1234", 1, roles1);
	     userService.AjouterUsers(users);
	     userService.AjouterUsers(users1);*/
	}

}

package com.example.demo.entities;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Users {
@Id
private String username;
private String password;
private int active;
@ManyToMany
private Collection<Roles> roles;


public Users() {
	super();
	// TODO Auto-generated constructor stub
}


public Users(String username, String password, int active, Collection<Roles> roles) {
	super();
	this.username = username;
	this.password = password;
	this.active = active;
	this.roles = roles;
}


public String getUsername() {
	return username;
}


public void setUsername(String username) {
	this.username = username;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public int getActive() {
	return active;
}


public void setActive(int active) {
	this.active = active;
}


public Collection<Roles> getRoles() {
	return roles;
}

public void setRoles(Collection<Roles> roles) {
	this.roles = roles;
}

}

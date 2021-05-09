package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Roles {
@Id
private String role;

public Roles() {
	super();
	// TODO Auto-generated constructor stub
}

public Roles(String role) {
	super();
	this.role = role;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

}

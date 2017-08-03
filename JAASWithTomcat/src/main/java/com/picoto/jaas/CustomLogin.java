package com.picoto.jaas;

import java.util.ArrayList;
import java.util.List;

public class CustomLogin {

	private String username;
	private String password;

	public CustomLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public boolean login() {
		return "admin".equalsIgnoreCase(username) && "1234".equalsIgnoreCase(password);
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		return roles;
	}
	
}

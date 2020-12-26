package com.ftn.kts_nvt.dto;

import java.util.ArrayList;

public class Users {

	private ArrayList<UserDTO> users;

	
	public Users() {
		super();
	}

	public Users(ArrayList<UserDTO> users) {
		super();
		this.users = users;
	}

	public ArrayList<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserDTO> users) {
		this.users = users;
	}
	
	
	
}

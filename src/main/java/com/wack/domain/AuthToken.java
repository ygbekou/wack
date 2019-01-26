package com.wack.domain;

import java.util.List;

public class AuthToken {

	private String userName;
	private String password;
	private String firstName;
	private String lastName;
    private String token;
    private String roleName;
    private String picture;
    private List authorities;

    public AuthToken(){

    }

    public AuthToken(String token, String userName, String password, 
    		String firstName, String lastName, String roleName,
    		String picture, List authorities){
        this.token = token;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
        this.picture = picture;
        this.authorities = authorities;
    }
    
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public List getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List authorities) {
		this.authorities = authorities;
	}
}

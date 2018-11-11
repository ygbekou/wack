package com.qkcare.domain;

import java.util.List;

public class AuthToken {

	private String userName;
	private String password;
    private String token;
    private List authorities;

    public AuthToken(){

    }

    public AuthToken(String token, String userName, String password, List authorities){
        this.token = token;
        this.userName = userName;
        this.password = password;
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
	public List getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List authorities) {
		this.authorities = authorities;
	}
}

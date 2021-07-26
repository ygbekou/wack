package com.wack.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table; 

import org.hibernate.annotations.ColumnTransformer;

@Table(name = "SMPP")
@Entity
public class SMPP extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SMPP_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "URL")
	private String url;

	@Column(name = "USER_NAME")
	private String userName; 
	
	@Column(name="STATUS")
	private Short status=1;

	@ColumnTransformer(read = "AES_DECRYPT(UNHEX(password), UNHEX(SHA2('Softenza forever', 512)))", write = "HEX(AES_ENCRYPT(?, UNHEX(SHA2('The secret passphrase', 512))))")
	private String password;

	/**
	 * 0- local SMS only 1- International supported
	 */
	@Column(name = "INTER")
	private short inter;

	public boolean getStatus() {
		return status==1?true:false;
	}


	public void setStatus(boolean status) {
		this.status = (short) (status==true?1:0);
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public boolean getInter() {
		return inter==1?true:false;
	}

	public void setInter(boolean inter) {
		this.inter = (short) (inter==true?1:0);
	}

}

package com.wack.model.authorization;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.wack.model.BaseEntity;
import com.wack.model.User;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends BaseEntity {
	
	@Id
	@Column(name = "USER_ROLE_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "ROLE_ID")
	private Role role;
	private int status;

	
	public UserRole () {
	}
	
	public UserRole (User user, Role role) {
		this.setUser(user);
		this.setRole(role);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	// Transient
	public String getStatusDesc() {
		return status == 0 ? "Active" : "Inactive";
	}
}

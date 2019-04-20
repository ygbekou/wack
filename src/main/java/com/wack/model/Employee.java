package com.wack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {
	
	@Id
	@Column(name = "EMPLOYEE_ID")
	@GeneratedValue
	private Long id;
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	private String designation;
	private String resume;
	private int managing;
	private int status;
	
	
	public Employee() {}
	
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

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public int getManaging() {
		return managing;
	}

	public void setManaging(int managing) {
		this.managing = managing;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// TRansient fields for UI
	
		public String getFirstName() {
			return this.user.getFirstName();
		}
		public String getLastName() {
			return this.user.getLastName();
		}
		public String getMiddleName() {
			return this.user.getMiddleName();
		}
		public String getEmail() {
			return this.user.getEmail();
		}
		public String getPhone() {
			return this.user.getHomePhone();
		}
		public String getAddress() {
			return this.user.getAddress();
		}
		public String getSex() {
			return this.user.getSex();
		}
		public String getName() {
			return this.user.getFirstName() + " " + this.user.getLastName();
		}
		public String getGroupName() {
			return this.user.getUserGroup().getName();
		}
		
		// Overriding equals() to compare two Complex objects
	    @Override
	    public boolean equals(Object o) {
	 
	        // If the object is compared with itself then return true  
	        if (o == this) {
	            return true;
	        }
	 
	        /* Check if o is an instance of Complex or not
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof Employee)) {
	            return false;
	        }
	         
	        // typecast o to Complex so that we can compare data members 
	        Employee e = (Employee) o;
	         
	        // Compare the data members and return accordingly 
	        return Long.compare(id, e.id) == 0;
	    }
}

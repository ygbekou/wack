package com.qkcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DOCTOR_ORDER")
public class DoctorOrder extends BaseEntity {
	
	@Id
	@Column(name = "DOCTOR_ORDER_ID")
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "APPOINTMENT_ID")
	private Appointment appointment;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ORDER_TYPE_ID")
	private DoctorOrderType doctorOrderType;
	@Column(name = "DOCTOR_ORDER_DATETIME")
	private Timestamp doctorOrderDatatime;
	private String description;
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Appointment getAppointment() {
		return appointment;
	}
	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	public DoctorOrderType getDoctorOrderType() {
		return doctorOrderType;
	}
	public void setDoctorOrderType(DoctorOrderType doctorOrderType) {
		this.doctorOrderType = doctorOrderType;
	}
	public Timestamp getDoctorOrderDatatime() {
		return doctorOrderDatatime;
	}
	public void setDoctorOrderDatatime(Timestamp doctorOrderDatatime) {
		this.doctorOrderDatatime = doctorOrderDatatime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}

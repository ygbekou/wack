package com.qkcare.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ORDER_PRIORITY_ID")
	private DoctorOrderPriority doctorOrderPriority;
	@Column(name = "DOCTOR_ORDER_DATETIME")
	private Timestamp doctorOrderDatetime;
	private String description;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ORDER_KIND_ID")
	private DoctorOrderPriority doctorOrderKind;
	@ManyToOne
	@JoinColumn(name = "DOCTOR_ID")
	private Employee doctor;
	@Column(name = "RECEIVED_DATETIME")
	private Timestamp receivedDatetime;
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
	public DoctorOrderPriority getDoctorOrderPriority() {
		return doctorOrderPriority;
	}
	public void setDoctorOrderPriority(DoctorOrderPriority doctorOrderPriority) {
		this.doctorOrderPriority = doctorOrderPriority;
	}
	public Timestamp getDoctorOrderDatetime() {
		return doctorOrderDatetime;
	}
	public void setDoctorOrderDatetime(Timestamp doctorOrderDatetime) {
		this.doctorOrderDatetime = doctorOrderDatetime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public DoctorOrderPriority getDoctorOrderKind() {
		return doctorOrderKind;
	}
	public void setDoctorOrderKind(DoctorOrderPriority doctorOrderKind) {
		this.doctorOrderKind = doctorOrderKind;
	}
	public Employee getDoctor() {
		return doctor;
	}
	public void setDoctor(Employee doctor) {
		this.doctor = doctor;
	}
	public Timestamp getReceivedDatetime() {
		return receivedDatetime;
	}
	public void setReceivedDatetime(Timestamp receivedDatetime) {
		this.receivedDatetime = receivedDatetime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	// Transient attributes
	public String getPatientId() {
		return this.getAppointment().getPatient().getMatricule();
	}
	
	public String getPatientName() {
		return this.getAppointment().getPatient().getName();
	}
	
	public String getDoctorOrderTypeName() {
		return this.getDoctorOrderType().getName();
	}
}

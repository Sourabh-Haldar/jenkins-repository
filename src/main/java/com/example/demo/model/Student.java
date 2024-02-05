package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Table(name="student")
@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="student_id")
	private int id;
	@Column(name="student_name")
	private String name;
	@Column(name="student_email")
	private String email;
	public Student(String name,String email) {
		super();
		this.name=name;
		this.email=email;
	}
}

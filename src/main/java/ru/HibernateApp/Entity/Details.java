package ru.HibernateApp.Entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "person_info")
@Getter
@Setter
@ToString
public class Details {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int personid;
	@Column
	private String email;
	@Column
	private String country;
	@Column
	private String city;

	public Details() {

	}
}

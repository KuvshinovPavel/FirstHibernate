package ru.HibernateApp.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.*;

@Getter
@Setter
@Entity
public class Person {
	@Id
	@GeneratedValue
	private Integer personId;
	@Column
	private String firstName;
	@Column
	private String lastName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "groupid")
	private Group group;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "info_id", unique = true)
	private Details details;

	
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinTable(name="person_role",
	joinColumns=@JoinColumn(name="personid"), 
	inverseJoinColumns =@JoinColumn (name="roleid"))
	List<Role> roles= new ArrayList();
	
	public Person() {
		super();
	}

}

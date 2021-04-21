package ru.HibernateApp.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_table")
@Getter
@Setter
@ToString
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupId;
	@Column
	private String groupName;

	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	List<Person> persons = new ArrayList<Person>();

	public Group() {

	}

	public Group(int groupId, String groupName) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}

}

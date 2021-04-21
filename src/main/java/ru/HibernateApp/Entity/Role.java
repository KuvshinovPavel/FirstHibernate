package ru.HibernateApp.Entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column(name = "rolename")
	private String roleName;

	public Role() {
	}

	public Role(int roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

}

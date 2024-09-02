package com.mycom.myapp.user.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String Password;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_user_role", 
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	@ToString.Exclude
	private Set<UserRole> roles = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<UserAddress> addresses = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<UserPhone> phones = new ArrayList<>();
}

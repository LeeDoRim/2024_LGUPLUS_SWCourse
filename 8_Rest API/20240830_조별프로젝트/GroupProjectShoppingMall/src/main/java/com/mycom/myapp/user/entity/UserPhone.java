package com.mycom.myapp.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_phone")
@Getter
@Setter
@ToString
public class UserPhone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String phone;
	
	// Address가 조회될 때 N+1문제 해결을 위해 LAZY 사용
		@ManyToOne (fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		@ToString.Exclude
		private User user;
		
		// User와 연관관계가 없을 때
//		@Column(name = "user_id")
//		private Long userId;

}

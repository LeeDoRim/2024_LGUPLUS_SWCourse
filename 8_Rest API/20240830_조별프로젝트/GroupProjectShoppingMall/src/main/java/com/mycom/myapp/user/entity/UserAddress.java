package com.mycom.myapp.user.entity;

import jakarta.persistence.Column;
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
@Table(name = "user_address")
@Getter
@Setter
@ToString
public class UserAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 실제 주소 : 우편번호, 시도코드, 구군코드, 읍면동코드, 상세주소 ( 행안부 법정동코드... )
	@Column(name = "zip_code")
	private String zipCode;
	private String addr1;
	private String addr2;
	
	// Address가 조회될 때 N+1문제 해결을 위해 LAZY 사용
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	private User user;
	
	// User와 연관관꼐가 없을 때
//	@Column(name = "user_id")
//	private Long userId;

}

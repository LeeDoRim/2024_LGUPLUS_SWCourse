package com.mycom.springbootjparoleshop.orders.entity;

import com.mycom.springbootjparoleshop.product.entity.Product;
import com.mycom.springbootjparoleshop.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@ToString.Exclude
	private Product product;
	
	@Column(name = "order_quantity")
	private int orderQuantity;
	
	@Column(name = "order_date")
	private LocalDate orderDate;

}

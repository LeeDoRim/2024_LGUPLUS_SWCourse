package com.mycom.springbootjparoleshop.product.entity;

import com.mycom.springbootjparoleshop.orders.entity.Orders;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int price;
	private int quantity;
	private String country;
	
	@OneToMany(mappedBy = "product")
	@ToString.Exclude
	private List<Orders> orders;

}

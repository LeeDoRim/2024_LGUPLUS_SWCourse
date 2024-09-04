package com.mycom.springbootjparoleshop.orders.controller;

import com.mycom.springbootjparoleshop.orders.dto.OrdersDto;
import com.mycom.springbootjparoleshop.orders.dto.OrdersResultDto;
import com.mycom.springbootjparoleshop.orders.entity.Orders;
import com.mycom.springbootjparoleshop.orders.service.OrdersService;
import com.mycom.springbootjparoleshop.product.dto.ProductDto;
import com.mycom.springbootjparoleshop.product.dto.ProductResultDto;
import com.mycom.springbootjparoleshop.product.entity.Product;
import com.mycom.springbootjparoleshop.product.service.ProductService;
import com.mycom.springbootjparoleshop.user.dto.UserDto;
import com.mycom.springbootjparoleshop.user.dto.UserResultDto;
import com.mycom.springbootjparoleshop.user.entity.User;
import com.mycom.springbootjparoleshop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final OrdersService orderService;
    private final ProductService productService;

    @GetMapping("/orders")
    public OrdersResultDto listOrders(HttpSession session){
        OrdersResultDto ordersResultDto = new OrdersResultDto();
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto.getRoles().containsValue("role_admin")){
            List<Orders> listOrders = orderService.listOrders();
            List<OrdersDto> listOrdersDto = new ArrayList<>();
            listOrders.forEach(orders -> {
                OrdersDto dto = new OrdersDto();
                dto.setId(orders.getId());
                dto.setOrderDate(orders.getOrderDate());
                dto.setOrderQuantity(orders.getOrderQuantity());

                listOrdersDto.add(dto);
            });
            ordersResultDto.setResult("success");
            ordersResultDto.setListOrders(listOrdersDto);
        }else {
            ordersResultDto.setResult("fail");
            ordersResultDto.setListOrders(null);
        }
        return ordersResultDto;
    }

    @GetMapping("/users")
    public UserResultDto listUsers(HttpSession session){
        UserResultDto userResultDto = new UserResultDto();
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto.getRoles().containsValue("role_admin")){
            List<User> listUsers = userService.listUsers();
            List<UserDto> listUsersDto = new ArrayList<>();
            listUsers.forEach(user -> {
                UserDto dto = new UserDto();
                dto.setName(user.getName());
                dto.setPassword(user.getPassword());
                dto.setEmail(user.getEmail());

                listUsersDto.add(dto);
            });
            userResultDto.setResult("success");
            userResultDto.setUserList(listUsersDto);
        }else {
            userResultDto.setResult("fail");
            userResultDto.setUserList(null);
        }
        return userResultDto;
    }

    @PostMapping("/products")
    public ProductResultDto insertProduct(HttpSession session, @RequestBody ProductDto productDto){
        ProductResultDto productResultDto = new ProductResultDto();
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto.getRoles().containsValue("role_admin")){
            Product product = new Product();
            product.setName(productDto.getName());
            product.setCountry(productDto.getCountry());
            product.setPrice(productDto.getPrice());
            productService.insert(product);
            productResultDto.setResult("success");
            productResultDto.setProductDto(productDto);
        }else {
            productResultDto.setResult("fail");
            productResultDto.setProductDto(null);
        }
        return productResultDto;
    }

    @PutMapping("/products")
    public ProductResultDto updateProduct(HttpSession session, @RequestBody ProductDto productDto){
        ProductResultDto productResultDto = new ProductResultDto();
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto != null && userDto.getRoles().containsValue("role_admin")){
            Product product = productService.findById(productDto.getId()).get();
            if (product != null) {
                product.setName(productDto.getName());
                product.setCountry(productDto.getCountry());
                product.setPrice(productDto.getPrice());
                product.setQuantity(productDto.getQuantity());
                productService.update(product);
                productResultDto.setResult("success");
                productResultDto.setProductDto(productDto);
            }
        }else {
            productResultDto.setResult("fail");
            productResultDto.setProductDto(null);
        }
        return productResultDto;
    }

    @GetMapping("/products")
    public ProductResultDto listProducts(HttpSession session) {
        ProductResultDto productResultDto = new ProductResultDto();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if (userDto.getRoles().containsValue("role_admin")) {
            List<Product> listProducts = productService.listProducts();
            List<ProductDto> listProductsDto = new ArrayList<>();
            listProducts.forEach(product -> {
                ProductDto dto = new ProductDto();
                dto.setId(product.getId());
                dto.setName(product.getName());
                dto.setPrice(product.getPrice());
                dto.setQuantity(product.getQuantity());
                dto.setCountry(product.getCountry());

                listProductsDto.add(dto);
            });
            productResultDto.setResult("success");
            productResultDto.setProductList(listProductsDto);
        } else {
            productResultDto.setResult("fail");
            productResultDto.setProductList(null);
        }
        return productResultDto;
    }
}

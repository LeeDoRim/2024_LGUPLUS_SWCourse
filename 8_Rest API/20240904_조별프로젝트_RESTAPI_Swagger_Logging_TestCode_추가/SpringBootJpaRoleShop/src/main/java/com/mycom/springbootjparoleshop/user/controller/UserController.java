package com.mycom.springbootjparoleshop.user.controller;

import com.mycom.springbootjparoleshop.user.dto.UserDto;
import com.mycom.springbootjparoleshop.user.dto.UserRequestDto;
import com.mycom.springbootjparoleshop.user.dto.UserResultDto;
import com.mycom.springbootjparoleshop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    boolean isCustomer = false;  // 일반회원 권한 유무

    @PostMapping("/register")
    public UserResultDto registerUser(@ModelAttribute UserRequestDto userRequestDto) {
        return userService.insertUser(userRequestDto);
    }

    @PostMapping("/login")
    public UserResultDto login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session
            ) {
        UserResultDto userResultDto = userService.login(email, password);

        if (userResultDto.getResult().equals("success")) {
            session.setAttribute("userDto", userResultDto.getUserDto());
        }

        return userResultDto;
    }

    @GetMapping("/logout")
    public UserResultDto logout(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            session.invalidate();
            userResultDto.setResult("success");
        } catch (IllegalStateException e) {
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }

    @PostMapping("/register/validate")
    public UserResultDto validateEmail(@RequestParam("email") String email) {
        return userService.validateDuplicateEmail(email);
    }

    // UserInfo
    @GetMapping("/userlist")
    public UserResultDto detailUser(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();
        Long userId = ( (UserDto)session.getAttribute("userDto") ).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ( (UserDto)session.getAttribute("userDto") ).getRoles();
        roles.forEach( (key, role) -> {
            if(role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if(isCustomer) { // 일반 회원
            userResultDto = userService.detailUser(userId);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        System.out.println(userResultDto);

        isCustomer = false;
        return userResultDto;
    }

    // User update
    @PostMapping("/userupdate")
    public UserResultDto updateUser(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session
    ) {
        UserResultDto userResultDto = new UserResultDto();
        Long userId = ( (UserDto)session.getAttribute("userDto") ).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ( (UserDto)session.getAttribute("userDto") ).getRoles();
        roles.forEach( (key, role) -> {
            if(role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if(isCustomer) { // 일반 회원
            userResultDto = userService.updateUser(userId, email, name, password);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        isCustomer = false;
        return userResultDto;
    }

//	@GetMapping("/listUserAddress")
//	public UserResultDto detailAddress(HttpSession session) {
//		Long id = ( (UserDto)session.getAttribute("userDto") ).getId();
//		return userService.detailAddress(id);
//	}

    // Address Info
    @GetMapping("/addresses")
    public UserResultDto detailAddress(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();
        Long id = ( (UserDto)session.getAttribute("userDto") ).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
        roles.forEach((key, role) -> {
            if (role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if (isCustomer) { // 일반 회원
            userResultDto = userService.detailAddress(id);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        isCustomer = false;
        return userResultDto;
    }

    // 주소 추가
    @PostMapping("/addresses")
    public UserResultDto insertUserAddress(
            @RequestParam("zipCode") String zipCode,
            @RequestParam("addr1") String addr1,
            @RequestParam("addr2") String addr2,
            HttpSession session
    ) {
        UserResultDto userResultDto = new UserResultDto();
        Long id = ( (UserDto)session.getAttribute("userDto") ).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
        roles.forEach((key, role) -> {
            if (role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if (isCustomer) { // 일반 회원
            userResultDto = userService.insertAddress(id, zipCode, addr1, addr2);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        isCustomer = false;
        return userResultDto;
    }

    // Phone Info
    @GetMapping("/phones")
    public UserResultDto detailPhone(HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();
        Long id = ((UserDto) session.getAttribute("userDto")).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
        roles.forEach((key, role) -> {
            if (role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if (isCustomer) { // 일반 회원
            userResultDto = userService.detailPhone(id);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        isCustomer = false;
        return userResultDto;
    }

    // 연락처 추가
    @PostMapping("/phones")
    public UserResultDto insertPhone(@RequestParam("phone") String phone, HttpSession session) {
        UserResultDto userResultDto = new UserResultDto();
        Long id = ((UserDto) session.getAttribute("userDto")).getId();

        // 일반 회원 확인
        Map<Long, String> roles = ((UserDto) session.getAttribute("userDto")).getRoles();
        roles.forEach((key, role) -> {
            if (role.equals("role_customer")) { // 일반 회원인 경우
                isCustomer = true;
            }
        });

        if (isCustomer) { // 일반 회원
            userResultDto = userService.insertPhone(id, phone);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        isCustomer = false;
        return userResultDto;
    }
}

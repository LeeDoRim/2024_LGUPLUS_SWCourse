package com.mycom.myapp.user.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mycom.myapp.user.dto.UserAddressDto;
import com.mycom.myapp.user.dto.UserDto;
import com.mycom.myapp.user.dto.UserPhoneDto;
import com.mycom.myapp.user.dto.UserResultDto;
import com.mycom.myapp.user.entity.User;
import com.mycom.myapp.user.entity.UserAddress;
import com.mycom.myapp.user.entity.UserPhone;
import com.mycom.myapp.user.repository.UserAddressRepository;
import com.mycom.myapp.user.repository.UserPhoneRepository;
import com.mycom.myapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserAddressRepository userAddressRepository;
	private final UserPhoneRepository userPhoneRepository;
	
	// 로그인
	@Override
	public UserResultDto login(String email, String password) {
		UserResultDto userResultDto = new UserResultDto();
		
		User user = userRepository.findByEmail(email);
		
		if(user != null && user.getPassword().equals(password)) {
			
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setName(user.getName());
			userDto.setEmail(user.getEmail());
			
			// userDto 객체의 roles 필드를 참조하는 Map 객체를 userRoles 변수에 저장
			// userRoles에 데이터를 추가하면 그 내용이 userDto의 roles 필드에 그대로 반영
			Map<Long, String> userRoles = userDto.getRoles();
			user.getRoles().forEach( role -> userRoles.put(role.getId(), role.getName()));
			
			userResultDto.setUserDto(userDto);
			userResultDto.setResult("success");
		} else {
			userResultDto.setResult("fail");
		}
		
		return userResultDto;
	}

	// 회원 정보 조회
	@Override
	public UserResultDto detailUser(Long userId) {
		UserResultDto userResultDto = new UserResultDto();
		
		Optional<User> optionalUser = userRepository.findById(userId);
		
		optionalUser.ifPresentOrElse(
				user -> {
					UserDto userDto = new UserDto();
					
					userDto.setId(user.getId());
					userDto.setName(user.getName());
					userDto.setEmail(user.getEmail());
					userDto.setPassword(user.getPassword());
					
					userResultDto.setResult("success");
					userResultDto.setUserDto(userDto);
					
				}, 
				() -> {
					userResultDto.setResult("fail");	
				}
			);
		
		System.out.println(userResultDto);
		
		return userResultDto;
	}
	

	// 회원 정보 수정
	@Override
	public UserResultDto updateUser(Long userId, String email, String name, String password) {
		UserResultDto userResultDto = new UserResultDto();
		
		User user = new User();
		user.setId(userId);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		
		try {
			userRepository.save(user);
			userResultDto.setResult("success");
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult("fail");
		}
		
		return userResultDto;
	}

	// 회원 주소 확인
	@Override
	public UserResultDto detailAddress(Long id) {
		UserResultDto userResultDto = new UserResultDto();
		
		List<UserAddress> userAddressList = userAddressRepository.findByUserId(id);
		
		UserDto userDto = new UserDto();
		List<UserAddressDto> addresses = userDto.getAddresses();
		
		userAddressList.forEach( userAddresses -> {
			
			UserAddressDto userAddressDto = new UserAddressDto();
			userAddressDto.setId(userAddresses.getId());
			userAddressDto.setAddr1(userAddresses.getAddr1());
			userAddressDto.setAddr2(userAddresses.getAddr2());
			userAddressDto.setZipCode(userAddresses.getZipCode());
			
			addresses.add(userAddressDto);
			
		});
		
		userDto.setId(id);  // 단순 테스트 용도
		userResultDto.setUserDto(userDto);
		userResultDto.setResult("success");
		
		return userResultDto;
	}

	// 회원 주소 등록
	@Override
	public UserResultDto insertAddress(Long id, String zipCode, String addr1, String addr2) {
		UserResultDto userResultDto = new UserResultDto();
		
		UserAddress userAddress = new UserAddress();
//		userAddress.setUser(id);
		userAddress.setZipCode(zipCode);
		userAddress.setAddr1(addr1);
		userAddress.setAddr2(addr2);
		
//		userAddressRepository.save(userAddress);
//		userResultDto.setResult("success");
		
		Optional<User> optionalUser = userRepository.findById(id);
		
		optionalUser.ifPresentOrElse(
				user -> {
					userAddress.setUser(user);  // UserAddress Entity에 User Entity 연결
					try {
						userAddressRepository.save(userAddress);  // 정상적으로 수행되면 이후 라인 수행
						
						// 회원의 주소를 가져온다.
						List<UserAddress> userAddressesList = userAddressRepository.findByUserId(id);
						
						UserDto userDto = new UserDto();
						List<UserAddressDto> addresses = userDto.getAddresses();
						
						userAddressesList.forEach( userAddresses -> {
							
							UserAddressDto userAddressDto = new UserAddressDto();
							userAddressDto.setId(userAddresses.getId());
							userAddressDto.setAddr1(userAddresses.getAddr1());
							userAddressDto.setAddr2(userAddresses.getAddr2());
							userAddressDto.setZipCode(userAddresses.getZipCode());
							
							addresses.add(userAddressDto);
							
						});
						
						userDto.setId(id);  // 단순 테스트 용도
						userResultDto.setUserDto(userDto);
						userResultDto.setResult("success");
						
					} catch(Exception e) {
						e.printStackTrace();
						userResultDto.setResult("fail");	
					}
				}, 
				() -> {
					userResultDto.setResult("fail");	
				}
			);
		
		return userResultDto;
	}
	
	// 회원 연락처 확인
		@Override
		public UserResultDto detailPhone(Long id) {
			UserResultDto userResultDto = new UserResultDto();
			
			List<UserPhone> userPhoneList = userPhoneRepository.findByUserId(id);
			
			UserDto userDto = new UserDto();
			List<UserPhoneDto> phones = userDto.getPhones();
			
			userPhoneList.forEach( phone -> {
				
				UserPhoneDto userPhoneDto = new UserPhoneDto();
				userPhoneDto.setId(phone.getId());
				userPhoneDto.setPhone(phone.getPhone());
				
				phones.add(userPhoneDto);
				
			});
			
			userDto.setId(id);  // 단순 테스트 용도
			userResultDto.setUserDto(userDto);
			userResultDto.setResult("success");
			
			return userResultDto;
		}

		// 회원 연락처 등록
		@Override
		public UserResultDto insertPhone(Long id, String phone) {
			UserResultDto userResultDto = new UserResultDto();
			
			UserPhone userPhone = new UserPhone();
			userPhone.setPhone(phone);
			
			Optional<User> optionalUser = userRepository.findById(id);
			
			optionalUser.ifPresentOrElse(
					user -> {
						userPhone.setUser(user);  // UserAddress Entity에 User Entity 연결
						try {
							userPhoneRepository.save(userPhone);  // 정상적으로 수행되면 이후 라인 수행
							
							// 회원의 주소를 가져온다.
							List<UserPhone> userPhoneList = userPhoneRepository.findByUserId(id);
							
							UserDto userDto = new UserDto();
							List<UserPhoneDto> phones = userDto.getPhones();
							
							userPhoneList.forEach( phone1 -> {
								
								UserPhoneDto userPhoneDto = new UserPhoneDto();
								userPhoneDto.setId(phone1.getId());
								userPhoneDto.setPhone(phone1.getPhone());
								
								phones.add(userPhoneDto);
								
							});
							
							userDto.setId(id);  // 단순 테스트 용도
							userResultDto.setUserDto(userDto);
							userResultDto.setResult("success");
							
						} catch(Exception e) {
							e.printStackTrace();
							userResultDto.setResult("fail");	
						}
					}, 
					() -> {
						userResultDto.setResult("fail");	
					}
				);
			
			return userResultDto;
		}

}

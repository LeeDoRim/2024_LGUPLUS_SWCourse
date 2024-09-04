package com.mycom.springbootjparoleshop.user.service;

import com.mycom.springbootjparoleshop.user.dto.*;
import com.mycom.springbootjparoleshop.user.entity.User;
import com.mycom.springbootjparoleshop.user.entity.UserAddress;
import com.mycom.springbootjparoleshop.user.entity.UserPhone;
import com.mycom.springbootjparoleshop.user.entity.UserRole;
import com.mycom.springbootjparoleshop.user.repository.UserAddressRepository;
import com.mycom.springbootjparoleshop.user.repository.UserPhoneRepository;
import com.mycom.springbootjparoleshop.user.repository.UserRepository;
import com.mycom.springbootjparoleshop.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserPhoneRepository userPhoneRepository;

    private User convertToEntity(UserRequestDto userRequestDto) {
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setPassword(userRequestDto.getPassword());

        // address
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);
        userAddress.setAddr1(userRequestDto.getAddr1());
        userAddress.setAddr2(userRequestDto.getAddr2());
        userAddress.setZipCode(userRequestDto.getZipCode());
        user.getAddresses().add(userAddress);

        // phone
        UserPhone userPhone = new UserPhone();
        userPhone.setPhone(userRequestDto.getPhone());
        userPhone.setUser(user);
        user.getPhones().add(userPhone);

        return user;
    }

    @Override
    public UserResultDto insertUser(UserRequestDto userRequestDto) {
        final String DEFAUT_ROLE_NAME = "role_customer";
        UserResultDto userResultDto = new UserResultDto();

        try {
            User user = convertToEntity(userRequestDto);

            // 이메일 중복인지 검증
            if (validateDuplicateEmail(user.getEmail()).getResult().equals("fail")) {
                throw new IllegalStateException("AlreadyExistsEmail");
            }

            UserRole role = userRoleRepository.findByName(DEFAUT_ROLE_NAME);
            user.getRoles().add(role);

            User savedUser = userRepository.save(user);

            userResultDto.setResult("success");
        } catch (Exception e) {
            e.printStackTrace();
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }

    @Override
    public UserResultDto login(String email, String password) {
        UserResultDto userResultDto = new UserResultDto();

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());

            user.getRoles().forEach(role -> userDto.getRoles().put(role.getId(), role.getName()));
//            user.getPhones().forEach(phone -> userDto.getPhones().add(phone.getPhone()));
//            user.getAddresses().forEach(address -> {
//                UserAddressDto userAddressDto = new UserAddressDto();
//                userAddressDto.setZipCode(address.getZipCode());
//                userAddressDto.setAddr1(address.getAddr1());
//                userAddressDto.setAddr2(address.getAddr2());
//                userDto.getAddresses().add(userAddressDto);
//            });

            userResultDto.setUserDto(userDto);
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }

    @Override
    public UserResultDto validateDuplicateEmail(String email) {
        UserResultDto userResultDto = new UserResultDto();

        if (userRepository.existsByEmail(email)) {
            userResultDto.setResult("fail");
        } else {
            userResultDto.setResult("success");
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

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }
}

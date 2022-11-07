package com.cozastore.service.user;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cozastore.dto.reponse.PageResponse;
import com.cozastore.dto.reponse.UserInformationRespone;
import com.cozastore.dto.reponse.UserPageResponse;
import com.cozastore.dto.reponse.UserResponse;
import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.dto.request.UserInfoRequest;
import com.cozastore.dto.request.UserRoleRequest;
import com.cozastore.dto.request.UserStatusRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.PageMapper;
import com.cozastore.mappers.UserMapper;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;

@Service
public class UserService implements IUserService {
	PasswordEncoder passwordEncoder;

	IUserRepository userRepository;

	UserMapper userMapper;

	PageMapper pageMapper;

	@Override
	public UserInformationRespone getUserByEmail(String email) {
		Optional<User> optional = userRepository.findUserByEmail(email);
		User user = optional.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		UserInformationRespone informationRespone = userMapper.convertEntityToInfoResponse(user);
		return informationRespone;
	}

	@Autowired
	public UserService(PasswordEncoder passwordEncoder, IUserRepository userRepository, UserMapper userMapper,
			PageMapper pageMapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.pageMapper = pageMapper;
	}

	@Override
	public String changePassword(String email, String password, String confirmPassword, String passwordOld) {

		if (password != null && !password.equals(confirmPassword)) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new BadRequestException(ErrorString.PASS_OLD_NOT_MATCH);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		return "Password successfully changed!";
	}

	@Override
	public String registerUser(RegistrationRequest registrationRequest) {
		if (registrationRequest.getPassword() != null
				&& !registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())) {
			throw new BadRequestException(ErrorString.PASS_NOT_MATCH);
		}
		User user = userRepository.findUserByEmail(registrationRequest.getEmail()).orElse(null);
		if (user != null) {
			throw new BadRequestException(ErrorString.EMAIL_IN_USE);
		}
		user = userMapper.convertRegisterationRequestToUser(registrationRequest);
		user.setStatus(Status.ACTIVE.getValue());
		user.setRoles(Role.USER.getValue());
		user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
		long millis = System.currentTimeMillis();
		// creating a new object of the class Date
		Date date = new Date(millis);
		user.setDateCreate(date);
		user.setDateOfBirth(date);
		user.setPhone("");
		userRepository.save(user);
		return "User successfully registered.";
	}

	@Override
	public UserPageResponse getAllUserByPage(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		List<UserResponse> list = userMapper.convertListUserToResponse(page.getContent());
		PageResponse pageResponse = pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(),
				pageable.getPageSize());
		return UserPageResponse.builder().userResponses(list).pageResponse(pageResponse).build();
	}

	@Override
	public void changeStatusUser(UserStatusRequest statusRequest) {
		User user = userRepository.findUserByEmail(statusRequest.getEmail())
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		user.setStatus(statusRequest.getStatus().getValue());
		userRepository.save(user);
	}

	@Override
	public void changeRoleUser(UserRoleRequest roleRequest) {
		User user = userRepository.findUserByEmail(roleRequest.getEmail())
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		user.setRoles(roleRequest.getRole().getValue());
		userRepository.save(user);
	}

	@Override
	public void changeInfoUser(UserInfoRequest infoRequest, String email) {
		User user = userRepository.findUserByEmail(email)
				.orElseThrow(() -> new NotFoundException(ErrorString.USER_NOT_FOUND));
		User userUpdate = userMapper.convertInfoRequestToEntity(infoRequest, user);
		userRepository.save(user);
	}

}

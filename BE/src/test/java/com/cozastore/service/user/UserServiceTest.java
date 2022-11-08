package com.cozastore.service.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cozastore.dto.reponse.UserPageResponse;
import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.dto.request.UserRoleRequest;
import com.cozastore.dto.request.UserStatusRequest;
import com.cozastore.entity.User;
import com.cozastore.exception.BadRequestException;
import com.cozastore.exception.NotFoundException;
import com.cozastore.mappers.PageMapper;
import com.cozastore.mappers.UserMapper;
import com.cozastore.repository.user.IUserRepository;
import com.cozastore.utils.constant.ErrorString;
import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;
import com.cozastore.utils.constant.SuccessString;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService service;

	@Mock
	IUserRepository userRepository;

	@Mock
	UserMapper userMapper;

	@Mock
	PageMapper pageMapper;

	@Mock
	PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUpBefore() {
//		userRepository=mock(IUserRepository.class);
	}

	@Test
	void findAllByPage_whenDataValid_shouldReturnResponse() {
		Pageable pageable = PageRequest.of(0, 1);
		UserPageResponse expect = new UserPageResponse();
		List<User> content = new ArrayList<>();
		User value = new User();
		value.setUserId(1);
		value.setStatus(0);
		value.setRoles(0);
		content.add(value);
		Page<User> page = new PageImpl<>(content);
		when(userRepository.findAll(pageable)).thenReturn(page);
		expect.setPageResponse(
				pageMapper.convertPagetoPageResponse(page, pageable.getPageNumber(), pageable.getPageSize()));
		expect.setUserResponses(userMapper.convertListUserToResponse(content));
		assertThat(service.getAllUserByPage(pageable)).usingRecursiveComparison().isEqualTo(expect);
	}

	@Test
	void changePassword_whenEmailisNull_shouldThrowExeption() {
		when(userRepository.findUserByEmail("")).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> service.changePassword("", "", "", ""));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.USER_NOT_FOUND);
	}

	@Test
	void changePassword_whenPasswordOldNotMatch_shouldThrowExeption() {
		User user = new User();
		user.setPassword("");
		when(userRepository.findUserByEmail("")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("", "")).thenReturn(false);
		BadRequestException actual = Assertions.assertThrows(BadRequestException.class,
				() -> service.changePassword("", "", "", ""));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.PASS_OLD_NOT_MATCH);
	}

	@Test
	void changePassword_whenDataValid_shouldReturnString() {
		User user = new User();
		user.setPassword("");
		when(userRepository.findUserByEmail("")).thenReturn(Optional.of(user));
		when(passwordEncoder.matches("", "")).thenReturn(true);
		String success = service.changePassword("", "", "", "");
		assertThat(success).isEqualTo("Password successfully changed!");
	}

	@Test
	void changeStatus_whenDataInValid_shouldThrowExeption() {
		UserStatusRequest statusRequest = mock(UserStatusRequest.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> service.changeStatusUser(statusRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.USER_NOT_FOUND);
	}

	@Test
	void changeStatus_whenDataValid_shouldSaveEntity() {
		UserStatusRequest statusRequest = new UserStatusRequest();
		statusRequest.setStatus(Status.ACTIVE);
		User user = mock(User.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.of(user));
		service.changeStatusUser(statusRequest);
		verify(userRepository).save(user);
	}

	@Test
	void changeRole_whenDataInValid_shouldThrowExeption() {
		UserRoleRequest roleRequest = mock(UserRoleRequest.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.empty());
		NotFoundException actual = Assertions.assertThrows(NotFoundException.class,
				() -> service.changeRoleUser(roleRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.USER_NOT_FOUND);
	}

	@Test
	void changeRole_whenDataValid_shouldSaveEntity() {
		UserRoleRequest roleRequest = new UserRoleRequest();
		roleRequest.setRole(Role.USER);
		User user = mock(User.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.of(user));
		service.changeRoleUser(roleRequest);
		verify(userRepository).save(user);
	}

	@Test
	void registerUser_whenEmailInUser_shouldThrowExeption() {
		RegistrationRequest registrationRequest = mock(RegistrationRequest.class);
		User user = mock(User.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.of(user));
		BadRequestException actual = Assertions.assertThrows(BadRequestException.class,
				() -> service.registerUser(registrationRequest));
		assertThat(actual.getMessage()).isEqualTo(ErrorString.EMAIL_IN_USE);
	}

	@Test
	void registerUser_whenDataValid_shouldSaveUser() {
		RegistrationRequest registrationRequest = new RegistrationRequest();
		registrationRequest.setPassword("");
		registrationRequest.setConfirmPassword("");
		User user = mock(User.class);
		when(userRepository.findUserByEmail(null)).thenReturn(Optional.empty());
		when(passwordEncoder.encode("")).thenReturn("");
		when(userMapper.convertRegisterationRequestToUser(registrationRequest)).thenReturn(user);
		service.registerUser(registrationRequest);
		verify(userRepository).save(user);
	}

}

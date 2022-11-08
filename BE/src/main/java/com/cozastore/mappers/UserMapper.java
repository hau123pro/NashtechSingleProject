package com.cozastore.mappers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cozastore.dto.reponse.UserInformationRespone;
import com.cozastore.dto.reponse.UserResponse;
import com.cozastore.dto.request.RegistrationRequest;
import com.cozastore.dto.request.UserInfoRequest;
import com.cozastore.entity.User;
import com.cozastore.service.user.IUserService;
import com.cozastore.utils.constant.Role;
import com.cozastore.utils.constant.Status;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

	@Autowired
	private UtilMapper utilMapper;

	@Autowired
	PasswordEncoder passwordEncoder;

	public List<UserResponse> convertListUserToResponse(List<User> users) {
		List<UserResponse> list = new ArrayList<UserResponse>();
		for (User user : users) {
			UserResponse response = UserResponse.builder().userId(user.getUserId()).email(user.getEmail())
					.dateCreate(user.getDateCreate()).name(user.getName()).phone(user.getPhone())
					.status(Status.values()[user.getStatus()]).roles(Role.values()[user.getRoles()])
					.dateOfBirth(user.getDateOfBirth()).build();
			list.add(response);
		}
		return list;
	}

	public UserInformationRespone convertEntityToInfoResponse(User user) {
		return utilMapper.convertToResponse(user, UserInformationRespone.class);
	}

	public User convertInfoRequestToEntity(UserInfoRequest infoRequest, User user) {
		user.setDateOfBirth(infoRequest.getDateOfBirth());
		user.setName(infoRequest.getName());
		user.setPhone(infoRequest.getPhone());
		return user;
	}

	public User convertRegisterationRequestToUser(RegistrationRequest registrationRequest) {
		User user = User.builder().email(registrationRequest.getEmail()).name(registrationRequest.getName()).phone("")
				.status(Status.ACTIVE.getValue()).roles(Role.USER.getValue()).dateCreate(Date.valueOf(LocalDate.now()))
				.dateOfBirth(Date.valueOf(LocalDate.now())).build();
		return user;
	}

}

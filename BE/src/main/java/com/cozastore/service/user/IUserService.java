package com.cozastore.service.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;

import com.cozastore.dto.reponse.UserInformationRespone;
import com.cozastore.dto.reponse.UserPageResponse;
import com.cozastore.dto.request.UserInfoRequest;
import com.cozastore.dto.request.UserRoleRequest;
import com.cozastore.dto.request.UserStatusRequest;
import com.cozastore.entity.Cart;
import com.cozastore.entity.Orders;
import com.cozastore.entity.User;

public interface IUserService {
	public UserInformationRespone getUserByEmail(String email);

	public String registerUser(User user, String confirmPassword);

	public String changePassword(String email, String password, String confirmPassword,String passwordOld);

	public UserPageResponse getAllUserByPage(Pageable pageable);

	public void changeStatusUser(UserStatusRequest userStatus);
	
	public void changeRoleUser(UserRoleRequest roleRequest);
	
	public void changeInfoUser(UserInfoRequest infoRequest,String email);
}

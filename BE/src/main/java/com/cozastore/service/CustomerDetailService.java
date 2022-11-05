package com.cozastore.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cozastore.entity.User;
import com.cozastore.repository.user.IUserRepository;


@Service
public class CustomerDetailService implements UserDetailsService{
	@Autowired
	IUserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 Optional<User> userOptional = userRepo.findUserByEmail(email);
	     User user = userOptional.orElseThrow(()-> new UsernameNotFoundException("User not found"));
	    
	     //tạo list authoritis để nhận quyền của tài khoản
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);
		//trả về userdetail chứa username,password và list quyền của nó
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);

//		return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),getAuthorities(user.getRole().getRoleName().toUpperCase()));

	}
	private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+role));


    }

}

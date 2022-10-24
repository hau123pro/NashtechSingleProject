package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import entity.User;
import repository.user.IUserRepository;
import utils.constant.ErrorString;


@Service
public class CustomerDetailService implements UserDetailsService{
	@Autowired
	IUserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 System.out.print(email);
		 Optional<User> userOptional = userRepo.findUserByEmail(email);
		 
	     User user = userOptional.orElseThrow(()-> new UsernameNotFoundException(ErrorString.USER_NOT_FOUND));
	    
	     //tạo list authoritis để nhận quyền của tài khoản
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_USER");
		authorities.add(authority);
		//trả về userdetail chứa username,password và list quyền của nó
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);


	}

}

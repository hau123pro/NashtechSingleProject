package mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import dto.request.PasswordResetRequest;
import dto.request.RegistrationRequest;
import entity.User;
import exception.ApiException;
import exception.BadRequestException;
import exception.InputFieldException;
import lombok.RequiredArgsConstructor;
import service.auth.IAuthService;
import service.user.IUserService;

@Component
@RequiredArgsConstructor
public class AuthenticationMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private IUserService userService;
	
	public String registerUser( RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult.getFieldErrors());
            throw new InputFieldException(bindingResult);
        }
        User user = mapper.map(registrationRequest, User.class);
        return userService.registerUser(user, registrationRequest.getPassword2());
    }
	
	public String passwordReset( PasswordResetRequest passwordReset, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
            return userService.changePassword(passwordReset.getEmail(),passwordReset.getPassword(), passwordReset.getPassword2());
        }
    }
	public String sendPasswordResetCode(String email) {
		
		return null;
	}
}

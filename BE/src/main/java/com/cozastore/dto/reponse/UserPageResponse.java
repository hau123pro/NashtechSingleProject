package com.cozastore.dto.reponse;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserPageResponse implements Serializable{
	
	List<UserResponse> userResponses;
	
	PageResponse pageResponse;
	
	
}

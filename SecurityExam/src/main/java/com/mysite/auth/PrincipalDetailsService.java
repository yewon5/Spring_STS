package com.mysite.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mysite.model.User1;
import com.mysite.repository.User1Repository;

@Service
public class PrincipalDetailsService implements UserDetailsService{
	@Autowired
	private User1Repository user1Repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //loadUserByUsername사용자 정보를 가져옴
		
		User1 userEntity = user1Repository.findByUsername(username);
		//System.out.println("userEntity : " + userEntity);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity); //PrincipalDetails로 포장 리턴이 된 장소는 세션
		}

	return null;
	
	}
}

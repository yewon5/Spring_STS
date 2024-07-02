package com.mysite.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mysite.model.User1;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	private User1 user; //UserDetails에서 가져온 데이터 저장
	private Map<String, Object> attributes; //OAuth2User에서 가져온 데이터 저장
	
	//일반 로그인
	public PrincipalDetails(User1 user) {
		this.user = user;
	}
	
	//구글 로그인
	public PrincipalDetails(User1 user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //getAuthorities 어떤역활인지 반환
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return user.getRole();
			}
		});
		
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}

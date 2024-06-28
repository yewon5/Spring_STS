package com.mysite.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mysite.model.User1;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{
	private User1 user;

	public PrincipalDetails(User1 user) { //객체가 필요로할때 사용자 정보를 넘겨줌 @RequiredArgsConstructor와 같음
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//이사람이 어떤 권한/계정인지 알 수 있도록 값을 넘겨주면 됨. 한사람이 권한을 여러개를 갖고 있을 수 있기때문에 컬렉션.
		Collection<GrantedAuthority> collect = new ArrayList<>(); //여러개를 저장할 수 있게 공간 만들기
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		}); //무명클래스
		
		return collect;
	}

	@Override
	public String getPassword() {

		return user.getPassword();
	}

	@Override
	public String getUsername() {

		return user.getUsername();
	}

}

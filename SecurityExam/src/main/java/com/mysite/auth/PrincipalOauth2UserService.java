package com.mysite.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.mysite.model.User1;
import com.mysite.repository.User1Repository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{
	@Autowired
	private User1Repository user1Repository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); //구글로부터 인증 받은 정보
		System.out.println("getAccessToken : " + userRequest.getAccessToken());
		System.out.println("구글에서 가져온 사용자 정보 : " + super.loadUser(userRequest).getAttributes());
		
		// 회원 가입을 강제로 진행
		OAuth2User oauth2User = super.loadUser(userRequest); 
		String provider = userRequest.getClientRegistration().getClientId(); //구분하기 위한 값
		String providerId = oauth2User.getAttribute("sub");
		String email = oauth2User.getAttribute("email");
		String username = provider + "_" + providerId;
		String role = "ROLE_USER";
		
		User1 userEntity = user1Repository.findByUsername(username);
		if(userEntity == null) {
			/*
			userEntity = new User1();
			userEntity.setUsername(username);
			userEntity.setEmail(email);
			userEntity.setProvider(provider);
			userEntity.setProviderId(providerId);
			userEntity.setRole(role);
			*/
			
			userEntity = User1.builder()
							.username(username)
							.email(email)
							.role(role)
							.provider(provider)
							.providerId(providerId)
							.build();
			
			user1Repository.save(userEntity); //새로운 사용자 저장
		}
		
		//return super.loadUser(userRequest);
		return new PrincipalDetails(userEntity, oauth2User.getAttributes()); //생성자를 호출하면서 구글로그인 메서드
	}

}

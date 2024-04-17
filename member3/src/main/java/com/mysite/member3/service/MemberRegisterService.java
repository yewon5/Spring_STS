package com.mysite.member3.service;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.mysite.member3.model.Member;
import com.mysite.member3.model.RegisterRequest;
import com.mysite.member3.repository.MemberDao;

@Service("regSvc")
public class MemberRegisterService {
	//ctx(설정파일)은 하나만 있으면됨. 이미 객체로 생성이되어있어서 메모리에 올라가있음. 또 불러오는것 아님.
	//객체를 주입받아야하니까 그걸 받을 변수는 필요함.
	private MemberDao memberdao;
	
	//기본생성자는 그냥 준비해주기
	public MemberRegisterService() {}
	
	//인자 있는 생성자. 이제 설정파일에서 memberdao객체를 주입시켜주면됨.
	//어노테이션으로 memberdao 자동 주입
	@Autowired
	public MemberRegisterService(MemberDao memberdao) {
		this.memberdao = memberdao;
	}
	
	public void register(RegisterRequest req) {

		Member member = memberdao.selectByEmail(req.getEmail());
		
		if(member != null) {
			System.out.println("같은 이메일 주소가 있습니다.");
			return; //메서드를 호출한 곳으로 돌아감.
		}
		
		//두개의 패스워드가 맞는지 확인
		if(!req.getPassword().equals(req.getPasswordConfirm())) {
			System.out.println("패스워드가 같지 않습니다.");
			return;
		}
		
		//위의 두개다 통과하면 member로 담아서 dao에게 전달.
		//member객체로 넘겨줘야하니까 member객체 생성
		Member newMem = new Member(req.getName(), req.getEmail(), req.getPassword(), new Date());
		
		memberdao.insert(newMem);
	}
	
	//dao에있는 selectall호출
	public Collection<Member> selectAll(){
		return memberdao.selectAll();
	}
}

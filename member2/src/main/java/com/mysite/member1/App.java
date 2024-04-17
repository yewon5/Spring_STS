package com.mysite.member1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysite.member1.model.Member;
import com.mysite.member1.model.RegisterRequest;
import com.mysite.member1.repository.MemberDao;
import com.mysite.member1.service.ChangePasswordService;
import com.mysite.member1.service.MemberRegisterService;



/*
 * exit 종료 명령어.
 * new 회원가입 명령어. 이메일 이름 암호 암호확인
 * change 암호 변경 명령어. 이메일 현재암호 변경암호
 * list 전체 회원정보 출력 명령어.
 * info 한명의 정보만 출력하는 명령어. 이메일
 */

public class App {
	private static ApplicationContext ctx; //static으로 선언해서 딱 1개만 생성됨.

    public static void main( String[] args ) throws IOException{	
    	ctx = new ClassPathXmlApplicationContext("config/application_context_config.xml");
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //InputStreamReader 바이트스트림을 문자로 바꿔주는 것
    	
    	while(true) {
        	System.out.println("명령어를 입력하세요 : ");
	    	String command = br.readLine();
	    	
	    	if(command.equalsIgnoreCase("exit")) {
	    		System.out.println("프로그램을 종료합니다.");
	    		break;
	    	}
	    	else if (command.startsWith("new ")) { //new뒤에 공백 표시
	    		//회원 가입
	    		newCommand(command.split(" ")); //공백을 기준으로 입력 받은 값 배열로 분리
	    	}
	    	else if (command.startsWith("change ")) {
	    		//회원 정보(암호) 수정
	    		changeCommand(command.split(" "));
	    	}
	    	else if (command.equalsIgnoreCase("list")) {
	    		//전체 회원 조회
	    		listCommand();
	    	}
    	}//end while
    } //end 메인 메서드
    
    public static void newCommand(String[] commands) {
		RegisterRequest req = new RegisterRequest();
		req.setEmail(commands[1]);
		req.setName(commands[2]);
		req.setPassword(commands[3]);
		req.setPasswordConfirm(commands[4]);
		
		MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);
		regSvc.register(req);
    }
    
    public static void changeCommand(String[] commands) {
    	ChangePasswordService changePwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		changePwdSvc.changePassword(commands[1], commands[2], commands[3]);
		System.out.println("암호가 변경되었습니다.");
    }
    
    public static void listCommand() {
		MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);
    	Collection<Member> member = regSvc.selectAll();
		for(Member mem : member) {
			System.out.println(mem.getId() + "\t" + mem.getName() + "\t" + mem.getEmail() + "\t" + mem.getRegisterDate() + "\t" + mem.getPassword());
		}
    }
}

package com.mysite.member1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Scanner;

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
	//static MemberRegisterService regSvc = new MemberRegisterService(); //반복문때문에 객체가 계속 생성되어서 조건문 밖에다 선언해준다.
	//static ChangePasswordService changePwdSvc = new ChangePasswordService();
	private Factory factory = Factory.newInstance();
	
    public static void main( String[] args ) throws IOException{	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //InputStreamReader 바이트스트림을 문자로 바꿔주는 것
    	App app = new App();
    	
    	while(true) {
        	System.out.println("명령어를 입력하세요 : ");
	    	String command = br.readLine();
	    	
	    	if(command.equalsIgnoreCase("exit")) {
	    		System.out.println("프로그램을 종료합니다.");
	    		break;
	    	}
	    	else if (command.startsWith("new ")) { //new뒤에 공백 표시
	    		//회원 가입
	    		app.newCommand(command.split(" ")); //공백을 기준으로 입력 받은 값 배열로 분리
	    	}
	    	else if (command.startsWith("change ")) {
	    		//회원 정보(암호) 수정
	    		app.changeCommand(command.split(" "));
	    	}
	    	else if (command.equalsIgnoreCase("list")) {
	    		//전체 회원 조회
	    		app.listCommand();
	    	}
    	}//end while
    } //end 메인 메서드
    
    public void newCommand(String[] commands) {
		RegisterRequest req = new RegisterRequest();
		req.setEmail(commands[1]);
		req.setName(commands[2]);
		req.setPassword(commands[3]);
		req.setPasswordConfirm(commands[4]);
		
		MemberRegisterService regSvc = factory.getMemberRegisterService();
		regSvc.register(req);
    }
    
    public void changeCommand(String[] commands) {
    	factory.getChangePasswordService().changePassword(commands[1], commands[2], commands[3]);
		System.out.println("암호가 변경되었습니다.");
    }
    
    public void listCommand() {
    	Collection<Member> member = factory.getMemberRegisterService().selectAll();
		for(Member mem : member) {
			System.out.println(mem.getId() + "\t" + mem.getName() + "\t" + mem.getEmail() + "\t" + mem.getRegisterDate() + "\t" + mem.getPassword());
		}
    }
}

package com.mysite.member3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysite.member3.model.Member;
import com.mysite.member3.model.RegisterRequest;
import com.mysite.member3.service.ChangePasswordService;
import com.mysite.member3.service.InfoByemailService;
import com.mysite.member3.service.MemberRegisterService;

/*
 * new 이메일 이름 암호 암호확인
 * change 명령어 사용해서 이메일 현재암호 바꿀암호
 * exit 종료
 * list 전체회원정보
 * info 이메일 입력했을때 그사람 정보만 뜰수있도록.
 */
public class App {
	//spring
	private static ApplicationContext ctx;
	
	//xml에서 만든 bean불러오기
	MemberRegisterService regSvc = ctx.getBean("regSvc", MemberRegisterService.class);
	
    public static void main( String[] args ) throws IOException{
    	ctx = new ClassPathXmlApplicationContext("config/application_context_config.xml");
    	
    	App app = new App();
      //키보드로부터 입력받는 키 하나.
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
      //근데 스캐너쓰면 공백을 쓰면 끝으로 인식함.
      //Scanner br = new Scanner(System.in);
      
      while(true) {
    	  System.out.println("명령어를 입력하세요 : ");
	      String command = br.readLine();
	      
	      if(command.equalsIgnoreCase("exit")) {
	    	  System.out.println("프로그램을 종료합니다.");
	    	  break;
	      }
	      else if(command.startsWith("new ")) {
	    	  //new로 시작되면 회원가입
	    	app.newCommand(command.split(" "));
	      }
	      else if(command.startsWith("change ")) {
	    	  //회원 정보(암호) 수정
	    	app.changeCommand(command.split(" "));

	      }
	      
	      else if(command.equalsIgnoreCase("list")) {
	    	  //전체 회원 정보 조회
	    	app.listCommand();
	      }
	      
	      else if(command.startsWith("info ")) {
	    	 app.infoCommand(command.split(" "));
	      }// end if
	      
	   }// end while
      
    }// end main
    
    public void newCommand(String[] commands) {
    	//여기서 입력받은걸 저장하기위한 dto
    	  RegisterRequest req = new RegisterRequest();
    	  
    	  req.setEmail(commands[1]);
    	  req.setName(commands[2]);
    	  req.setPassword(commands[3]);
    	  req.setPasswordConfirm(commands[4]);

    	  //서비스로 넘겨준다음 , 패스워드를 맞게 입력했는지 또는 같은 이메일이 아닌지 등을 확인함.
    	  regSvc.register(req);

    }//end newCommand
    
    
    //static안에서는 static메서드만 사용할수있으므로, static지워주기.
    public void changeCommand(String[] commands) {
    	ChangePasswordService chSvc = ctx.getBean("chSvc", ChangePasswordService.class);
  	  	chSvc.changePassword(commands[1], commands[2], commands[3]);
  	 
    }// end changeCommand
    
    public void listCommand() {
    	Collection<Member> member = regSvc.selectAll();
   	  	for(Member m : member) {
   		  System.out.println("번호: " + m.getId() + "\n" + "이름: " + m.getName() + "\n" + "이메일: " + m.getEmail() + "\n" + "가입일: "+ m.getRegisterDate() + "\n" + "비밀번호: "+ m.getPassword() + "\n"+"--------------------------");
   	  }//end for
   	  	
    }//end listCommand
    
    public void infoCommand(String[] commands) {
    	InfoByemailService InfoSvc = ctx.getBean("InfoSvc", InfoByemailService.class);
    	Member memberInfoByEmail = InfoSvc.InfoByEmail(commands[1]);
    	if(memberInfoByEmail != null) {
    	System.out.println("이름: " + memberInfoByEmail.getName());
    	System.out.println("가입일: " + memberInfoByEmail.getRegisterDate());
    	System.out.println("비밀번호: " + memberInfoByEmail.getPassword());
    	}//end if
    	
    }//end infoCommand
    
  }// end class


package basic06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import basic05.MessageBean;

//
public class App {
	private static ApplicationContext ctx; //프로젝트 안에 있는 리소스를 가져다 씀
	
    public static void main( String[] args ){
    	ctx = new ClassPathXmlApplicationContext("config/basic06_config.xml"); //설정파일에서 만들어진 bean태그를 ctx에 저장
    	//ctx = new AnnotationConfigApplicationContext("basic06"); //어노테이션 스캐너
    	
    	//한국어로 자기소개
    	basic06.MessageBean bean = ctx.getBean("msgKr", basic06.MessageBean.class);
    	bean.sayHello();
    	
    	//영어로 자기소개
    	bean = ctx.getBean("msgEn", basic06.MessageBean.class);
    	bean.sayHello();
    }
}
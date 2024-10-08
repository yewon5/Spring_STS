package basic04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//스프링 프레임워크 방식.
public class App {
	private static ApplicationContext ctx; //프로젝트 안에 있는 리소스를 가져다 씀
	
    public static void main( String[] args ){
    	ctx = new ClassPathXmlApplicationContext("config/basic04_config.xml"); //설정파일에서 만들어진 ("")스프링이라는 메모리를 ctx에 저장
    	
    	//DI 패턴. 원래 여기서 객체 직접 생성 해야했는데 설정파일에서 객체 생성한 후 외부에서 전달(주입) 받음
    	MessageBean bean = ctx.getBean("msgKr", basic04.MessageBeanKr.class);
    	bean.sayHello("길동"); 
    	
    	bean = ctx.getBean("msgEn", basic04.MessageBeanEn.class);
    	bean.sayHello("hong");
    	
    	
    	bean = ctx.getBean("msgkr", basic04.MessageBeanKr.class);
    	bean.sayHello("홍홍홍");
    	
    	bean = ctx.getBean("kr", basic04.MessageBeanKr.class);
    	bean.sayHello("길길길");
    	
    	bean = ctx.getBean("mskr", basic04.MessageBeanKr.class);
    	bean.sayHello("동동동");
    }
}
package basic07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//설정파일 없이 Java 코드에 의한 Bean 등록
public class App {
	private static ApplicationContext ctx;
	
    public static void main( String[] args ){
    	ctx = new AnnotationConfigApplicationContext(ApplicationContextConfigure.class); //어노테이션 스캐너
    	
    	MessageBean bean = ctx.getBean("getMessageKr", basic07.MessageBean.class);
    	bean.sayHello();
    	
    	bean = ctx.getBean("getMessageEn", basic07.MessageBean.class);
    	bean.sayHello();
    }
}
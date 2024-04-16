package basic08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	private static ApplicationContext ctx;
	
    public static void main( String[] args ){
    	/* 스프링 사용 전 직접 객체를 생성하는 기존 방식의 코드 -> 단점은 유지보수가 어렵다. 의존성에 의해 A가 B를 가져다 사용했을 때 B에 변경/문제가 생겼을 경우 A까지 영향을 받는다.
    	MessageBeanImpl bean = new MessageBeanImpl();
    	FileOutputter outputter = new FileOutputter(); //파일저장객체생성
    	outputter.setFilePath("c:\\WON\\greeting8.txt"); //파일저장경로
    	bean.setOutputter(outputter);
    	bean.setName("홍길동");
    	bean.setAge(20);
    	
    	bean.sayHello();
    	*/
    	
    	ctx = new ClassPathXmlApplicationContext("config/basic08_config.xml");
    	
    	MessageBean bean = ctx.getBean("messageBean", MessageBean.class);
    	bean.sayHello();
    }
}
package basic05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//DI패턴
//소비자(Consumer) 클래스
public class App {
	private static ApplicationContext ctx; //프로젝트 안에 있는 리소스를 가져다 씀
	
    public static void main( String[] args ){
    	ctx = new ClassPathXmlApplicationContext("config/basic05_config.xml");
    	
    	//App클래스 말고 다른 클래스에서도 MessageBeanImpl를 쓴다고 가정했을때 Impl클래스에서 문제가 생겼을 경우 의존하고 있는 다른 클래스에 문제가 발생한다.
    	//MessageBean bean = new MessageBeanImpl("홍길동", 27, "안녕하세요");
    	//bean.sayHello();
    	
    	//인터페이스 여러개 상속 받을 수 있으니까 제일 부모클래스로 한다. 
    	//sayHello에 넘겨받을 매개 변수가 없어서 해당 코드를 실행해보면 null!~~ null님 이제 당신의 나이는 0세입니다. 결과값이 이렇게 나오는데 어떻게 해결할까?
    	//설정파일에서 constructor-arg 태그를 이용해 초기화 시키기
    	MessageBean bean = ctx.getBean("messageBean", basic05.MessageBean.class);
    	bean.sayHello();
    }
}
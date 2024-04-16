package basic07;

import org.springframework.stereotype.Component;

//어노테이션으로 MessageBeanKr 클래스의 객체 생성.
@Component
public class MessageBeanKr implements MessageBean{
	private String name;
	private String age;
	private Outputter outputter;
	
	//1번. 생성자 방식
	public MessageBeanKr() {}
	public MessageBeanKr(String name, String age, Outputter outputter) {
		this.name = name;
		this.age = age;
		this.outputter = outputter;
	}
	
	public void sayHello() {
		System.out.println("제 이름은 " + name + "이고 나이는 " + age + "세입니다.");
		
		try {
			outputter.output("제 이름은 " + name + "이고 나이는 " + age + "세입니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

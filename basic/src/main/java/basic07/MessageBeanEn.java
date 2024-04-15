package basic07;

import org.springframework.stereotype.Component;

@Component("msgEn")
public class MessageBeanEn implements MessageBean{
	private String name;
	private String age;
	private Outputter outputter;
	
	//2번 세터메서드 방식
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
	}
	
	public void sayHello() {
		System.out.println("My name is " + name + " and my age is " + age);
	}
}

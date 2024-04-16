package basic08;

import org.springframework.beans.factory.annotation.Autowired;

public class MessageBeanImpl implements MessageBean{
	private String name;
	private int age;
	private Outputter outputter;
	
	public MessageBeanImpl() {}
	public MessageBeanImpl(String name, int age, String greeting) {
		this.name = name;
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Autowired
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
	}
	
	@Override
	public void sayHello() {
		String msg = name + "님 당신의 나이는 " + age +"세입니다.";
		System.out.println(msg);
		
		try {
			outputter.output(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

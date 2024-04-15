package basic05;

//Kr, En 두 개의 클래스를 하나의 클래스로 만들어보기
public class MessageBeanImpl implements MessageBean{
	//sayHello() 메서드에 매개변수를 주지 않고 처리할 변수 따로 준비
	//private이니까 1번 생성자를 이용하거나 2번 세터를 이용하거나
	private String name;
	private int age;
	private String greeting;
	private Outputter outputter;
	//outputter를 상속 받는 다른 클래스에게 전달시 수정하지 않고 사용하기 위해서는 Outputter(부모클래스)로 선언해준다.
	
	//기본 생성자를 사용하여 객체 생성, 객체 초기화 시키기
	public MessageBeanImpl() {}
	//매개변수를 전달하여 객체 생성, 객체를 생성할때 괄호값을 넘겨줘야 함
	public MessageBeanImpl(String name, int age, String greeting) {
		this.name = name;
		this.age = age;
		this.greeting = greeting;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public void setOutputter(Outputter outputter) {
		this.outputter = outputter;
	}
	
	@Override
	public void sayHello() {
		String msg = greeting + "!~~ " + name + "님 이제 당신의 나이는 " + age +"세입니다.";
		System.out.println(msg);
		
		//객체를 생성하여 파일에 저장하는 기존 방식
		//FileOutputter outputter = new FileOutputter();
		//outputter.setFilePath("c:\\WON\\greeting.txt");
		
		//설정파일에서 bean객체 생성 후 getBean(소비자 클래스에서 사용하는 것을 권장)이라는 메서드를 사용하지 않고 어떻게 불러올까?
		//private Outputter outputter; 선언 후 setOutputter 메서드 생성해준다.
		//이때 바로 실행하면 outputter가 null값이라고 나오는데, setOutputter 메서드가 호출되지 않았기 때문이다. 
		//설정파일에서 <property name="outputter" ref="outputter" /> 값을 넣어 준다.
		try {
			outputter.output(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

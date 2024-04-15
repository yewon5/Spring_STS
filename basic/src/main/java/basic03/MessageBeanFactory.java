package basic03;

public class MessageBeanFactory {
	//싱글톤 패턴 만들기
	private MessageBeanFactory() {} //외부에서 인스턴스화할 수 없도록 하기 위해 private으로 지정
	private static MessageBeanFactory factory = new MessageBeanFactory(); //MessageBeanFactory 클래스 내부에서 유일한 인스턴스를 생성. 클래스 내부에서만 접근 가능하며, 정적으로 선언되어 있어 클래스가 로드될 때 한 번만 초기화
	public static MessageBeanFactory newInstance() { //외부에서 MessageBeanFactory의 인스턴스를 얻을 수 있는 정적 메서드입니다. 이 메서드는 이미 생성된 인스턴스를 반환하는 형태로, 항상 동일한 인스턴스를 반환하도록 설계
		return factory;
	}
	
	public MessageBean createMessage(String nation) {
		if(nation.equals("kr")) {
			return new MessageBeanKr();
		}else{
			return new MessageBeanEn();
		}
	}
}
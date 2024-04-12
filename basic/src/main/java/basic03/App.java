package basic03;

//팩토리 패턴. 객체를 생성하기 위한 MessageBeanFactory 클래스 만들기. 객체간의 의존성을 해소/분리시킴
public class App {
    public static void main( String[] args ){
    	MessageBeanFactory factory = MessageBeanFactory.newInstance(); //공장의 위치만 알면 된다.
    
    	MessageBean bean1 = factory.createMessage("kr"); //내가 원하는 객체를 넘겨 받음
    	bean1.sayHello("연두");
    	
    	bean1 = factory.createMessage("en");
    	bean1.sayHello("dobu");
    	
    	
    }
}
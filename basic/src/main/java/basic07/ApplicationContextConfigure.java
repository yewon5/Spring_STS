package basic07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//환경 설정 담당 클래스
@Configuration
public class ApplicationContextConfigure {
	@Bean
	public MessageBean getMessageKr() {
		//생성자 방식
		MessageBeanKr kr = new MessageBeanKr("홍길동", "20", getOutputter()); //설정 파일 <bean id="msgKr" class="basic06.MessageBeanKr">와 같은 코드
			return kr;
	}
	
	@Bean
	public MessageBean getMessageEn() {
		//세터메서드 방식
		MessageBeanEn en = new MessageBeanEn();
			en.setAge("30");
			en.setName("hong");
			en.setOutputter(getOutputter());
			return en;
	}
}

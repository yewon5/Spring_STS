package webproject1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class SpringController extends AbstractController{ //스프링에선 서블릿을 상속 받지 않고 컨트롤러를 상속 받는다.
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return new ModelAndView("/WEB-INF/views/spring_result.jsp");
	}
}

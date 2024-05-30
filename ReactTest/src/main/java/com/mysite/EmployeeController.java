package com.mysite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
	@Autowired
	public EmployeeService employeeService; 

	@GetMapping("/emp")
	public List<Employee> getAllEmployees(){
		System.out.println("result:" + employeeService.getAllEmployees());
		return employeeService.getAllEmployees();
	}
	
	@PostMapping("/emp")
	public String insertEmployee(@RequestBody Employee emp) { //RESTful 인자받을때는 @RequestBody 생략하면 안됨
		employeeService.insertEmployee(emp);
		return "저장완.";
		
		//public Employee return emp; 확인 코드
	}
	
	@DeleteMapping("/{id}") //RESTful에선 물음표로 전달하지 않음
	public String deleteEmployee(@PathVariable("id") int id) { //물음표로 받을때는 @Requestparam, @PathVariable 특정 ID를 URL 경로에서 추출하고 이를 메서드 매개변수로 사용
		employeeService.deleteEmployee(id);
		return "삭제완.";	
		
		//public String return String.valueOf(id); id값 전달되는지 확인 코드
	}
	
	@PutMapping("/{id}") //수정에는 2가지가 있다. put 기존 데이터를 지우고 덮어쓰기, patch 일부분만 수정하기
	public String updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		String result = String.valueOf(id) + ", " + emp;
		return result;
	}
}

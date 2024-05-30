package com.mysite;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

//DAO
//mapper -> service -> controller
@Mapper
@Repository
public interface EmployeeMapper {
	@Select("select * from emp")
	List<Employee> getAllEmployees();
	
	@Insert("insert into emp(id, name, email) values(seq_emp.nextVal, #{name}, #{email})")
	void insertEmployee(Employee emp);
	
	@Delete("delete from emp where id = #{id}")
	void deleteEmployee(int id);
}

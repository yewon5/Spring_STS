package com.mysite;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//DAO
@Mapper
@Repository
public interface EmployeeMapper {
	List<Employee> getAllEmployees();
}

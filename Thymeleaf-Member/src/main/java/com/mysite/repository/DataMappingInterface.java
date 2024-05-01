package com.mysite.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mysite.domain.Member;

@Mapper
public interface DataMappingInterface {
	@Select("SELECT * FROM boot_mem")
	List<Member> getSelectAll();
	
    @Insert("INSERT INTO boot_mem(num, name, addr) VALUES (#{num}, #{name}, #{addr})")
    boolean insertData(Member member);
    
    @Delete("DELETE FROM boot_mem WHERE num = #{num}")
    int deleteData(int num);
    
    
    @Select("SELECT * FROM boot_mem WHERE num = #{num}")
    int getMemberByNum(int num);
    
    @Update("UPDATE boot_mem SET name = #{name}, addr = #{addr} WHERE num = #{num}")
    int updateData(Member member);
}

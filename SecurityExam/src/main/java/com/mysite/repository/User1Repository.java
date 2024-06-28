package com.mysite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysite.model.User1;

public interface User1Repository extends JpaRepository<User1, Integer> {
	public User1 findByUsername(String username); //id를 넘겨주면 db조회해서 UserDetailsService 포장해서 시큐리티에 저장하고 보안처리
}
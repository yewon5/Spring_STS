package com.mysite;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Junit과 스프링 연결 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**.xml"})
public class DataSourceTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testHikari() throws SQLException {
		Connection conn = dataSource.getConnection();
		
		//null이 아니면 녹색
		assertNotNull(conn);
	}
}

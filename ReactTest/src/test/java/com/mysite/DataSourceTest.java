package com.mysite;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DataSourceTest {
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testDataSource() {
		try(Connection con = dataSource.getConnection()) {
			System.out.println(con);
		}
		catch(Exception err) {
			err.printStackTrace();
		}
	}
}

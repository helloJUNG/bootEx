package org.zerock;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Cleanup;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class B3ApplicationTests {
	
	@Setter(onMethod_=@Autowired)
	private DataSource ds;

	@Test
	public void contextLoads() {
	}
	
	@Test
	@SneakyThrows(Exception.class)//Throws Exception
	public void testConnection() {
		
		//autoClose 지원.
		@Cleanup Connection con = ds.getConnection();
		
		log.info(""+con); //log는 문자열만되서 이렇게 꼼수 쓴다.
		
	}

}

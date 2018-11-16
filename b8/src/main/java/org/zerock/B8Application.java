package org.zerock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="org.zerock.mapper") // mydatis 설정 할 때 추 가
public class B8Application {

	public static void main(String[] args) {
		SpringApplication.run(B8Application.class, args);
	}
}

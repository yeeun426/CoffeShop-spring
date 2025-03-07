package com.shop.cafe.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shop.cafe.dto.Member;
import com.shop.cafe.dto.Product;

@Component
public class MemberDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
		
	public List<Product> insertMember(Member m) throws Exception {
		System.out.println("MemberDao insertMember() 호출됨");
		
		Class.forName(DB_DRIVER); // Driver 클래스를 로드
		Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
		PreparedStatement stmt=con.prepareStatement("insert into member(email, pwd, nickname) values (?,?,?)");
		
		stmt.setString(1, m.getEmail());
		stmt.setString(2, m.getPwd());
		stmt.setString(3, m.getNickname());
		int i=stmt.executeUpdate();
		
		System.out.println(i+"행이 insert되었습니다");
		return null;
	}
}

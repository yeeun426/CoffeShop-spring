package com.shop.cafe.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shop.cafe.dto.Product;

@Component
public class ProductDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
		
	public List<Product> getAllProducts() throws Exception {
		System.out.println("getAllProducts() 호출됨");
		Class.forName(DB_DRIVER); // Driver 클래스를 로드
		
		try(
		Connection con=DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
		PreparedStatement stmt=con.prepareStatement("select * from product limit 6");
		ResultSet rs = stmt.executeQuery(); // select는 executeQuery가 좋음.
		) {
		List<Product> list = new ArrayList<>();
		while(rs.next()) {
			int prodcode = rs.getInt("prodcode");
			String prodname = rs.getString("prodname");
			String pimg = rs.getString("pimg");
			int price = rs.getInt("price");
			list.add(new Product(prodcode, prodname, pimg, price));
		}
		return list;
		}
	}
}

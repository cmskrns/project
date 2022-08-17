package com.jafa.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jafa.config.AppTest;

public class MemberTest extends AppTest{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	@Ignore
	public void memberInserTest() {
		String sql = "insert into member_tbl(userId,userPw,userName,userEmail,addr) values(?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "test");
			pstmt.setString(2, passwordEncoder.encode("1234"));
			pstmt.setString(3, "테스터");
			pstmt.setString(4, "test@naver.com");
			pstmt.setString(5, "대구 북구");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void adminInserTest() {
		String sql = "insert into member_tbl(userId,userPw,userName,userEmail,addr,phoneNumber,natalDay,gender) values(?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "member");
			pstmt.setString(2, passwordEncoder.encode("1234"));
			pstmt.setString(3, "회원");
			pstmt.setString(4, "admin@naver.com");
			pstmt.setString(5, "대구 북구");
			pstmt.setString(6,"010-1111-1111");
			pstmt.setString(7,"1996-01-01");
			pstmt.setString(8,"M");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

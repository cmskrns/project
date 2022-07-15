package com.jafa.config;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RootConfigTest extends AppTest{
	
	@Autowired
	DataSource dataSource;
	
	@Test
	public void test() {
		assertNotNull(dataSource);
	}

}

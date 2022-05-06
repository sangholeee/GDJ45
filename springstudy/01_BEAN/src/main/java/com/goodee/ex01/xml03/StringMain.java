package com.goodee.ex01.xml03;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class StringMain {

	public static void main(String[] args) throws Exception {

		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml/context03.xml");
		
		MyConnection myCon = ctx.getBean("myConnection", MyConnection.class);
		Connection con = myCon.getConnection();
		
		if(con != null) {
			con.close();
		}
		ctx.close();
	}

}

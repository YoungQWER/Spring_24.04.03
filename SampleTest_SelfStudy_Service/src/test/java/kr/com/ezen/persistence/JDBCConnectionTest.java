package kr.com.ezen.persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCConnectionTest {

		static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		@Test
		public void testConnection() {
			try {
				Connection con = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:xe",
						"test2",
						"1234"
						);
				log.info(con);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


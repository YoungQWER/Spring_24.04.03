package org.zerock.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionTest {
    public static void main(String[] args) {
        // JDBC 연결 정보 설정
        String url = "jdbc:mysql://localhost:3306/livecommerce";
        String username = "test";
        String password = "1234";

        // JDBC 연결 객체 초기화
        Connection connection = null;

        try {
            // JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL 데이터베이스에 연결
            connection = DriverManager.getConnection(url, username, password);

            // 연결 성공 메시지 출력
            System.out.println("MySQL 데이터베이스에 성공적으로 연결되었습니다.");
        } catch (ClassNotFoundException e) {
            // JDBC 드라이버 로드 실패 시 예외 처리
            System.err.println("JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
        } catch (SQLException e) {
            // MySQL 데이터베이스 연결 실패 시 예외 처리
            System.err.println("MySQL 데이터베이스 연결 중 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            // 연결 종료
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("MySQL 연결을 닫는 중 오류가 발생했습니다.");
                    e.printStackTrace();
                }
            }
        }
    }
}
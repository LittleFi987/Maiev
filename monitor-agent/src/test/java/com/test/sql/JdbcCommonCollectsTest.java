package com.test.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author yechenhao
 * @Date 25/04/2018
 */
public class JdbcCommonCollectsTest {
    public static void main(String[] args) throws Exception {
        Class.forName( "com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/monitor?useUnicode=true", "root", "123456");
        PreparedStatement preparedStatement = conn.prepareStatement("select * from user");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.toString());
        }
    }
}

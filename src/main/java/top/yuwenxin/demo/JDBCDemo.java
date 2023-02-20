package top.yuwenxin.demo;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) throws SQLException {
        // 不需要手动加载实现类，通过java spi机制加载
        var connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=utf8", "root", "....");
        var statement = connection.createStatement();
        var res = statement.execute("create database if not exists testdb;");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from sys_config;");
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            StringBuilder sb = new StringBuilder();
            for (int r = 1; r <= columnCount; r++) {
                sb.append("col name: ").append(metaData.getColumnName(r)).append(";")
                        .append("col value: ").append(resultSet.getObject(r));
            }
        }
        System.out.println(res);
    }
}

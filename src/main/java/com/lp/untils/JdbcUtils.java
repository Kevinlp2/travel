package com.lp.untils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    private static DataSource dataSource;


        static{
            //获取配置文件数据

            try {
                InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
                Properties properties = new Properties();
                properties.load(in);
                dataSource = DruidDataSourceFactory.createDataSource(properties);
            }  catch (Exception e) {
                e.printStackTrace();
            }

        }

    /**
     * 获取连接
     * @return 数据库连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }

    /**
     * 获取数据库连接池
     * @return
     */
    public static DataSource getDataSource(){
            return dataSource;
        }

    /**
     * 释放资源
     * @param statement 语句执行者
     * @param conn 数据库连接对象
     */
    public static void close(Statement statement,Connection conn){
            try {
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void close(Statement statement, Connection conn, ResultSet resultSet){
        try {
            if(statement != null){
                statement.close();
            }
           if(conn != null){
               conn.close();
           }
           if(resultSet != null){
               resultSet.close();
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

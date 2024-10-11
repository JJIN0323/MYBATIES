package com.ohgriffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Application {

    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";

    public static void main(String[] args) {

        // MyBatis 쓰기 전에 환경설정이 필요함.
        
        /* 목차. 1. 환경 구성 */
        /* 필기.
        *   JDBC TransactionFactory : 수동 커밋
        *   ManagedTransactionFactory :  자동 커밋
        *  
        *   PooledDataSource : ConnectionPoll 사용
        *   UnPooledDataSource : ConnectionPoll 미사용
        *    */
        Environment environment = new Environment(
                "dev", // 환경 설정 정보에 대한 이름
                new JdbcTransactionFactory(), // 여기서 수동 커밋.
                new PooledDataSource(DRIVER, URL, USER, PASSWORD) // driver, url, database 가 필요함. Connection 을 만드는 역할 (수영장같은것)
        );
    }
}

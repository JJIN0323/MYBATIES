package com.ohgiraffers.section03.remix.common;

import com.ohgiraffers.section03.remix.model.dao.MenuMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

// 이 방식 많이 씀!!!!!!!!!!!!!!!!!!!!!!!!!! JAVA 방식!!!!!!!!!!!!!!!!

public class Template {

    // properties 파일로 넘기면 어떨까 ~? 고민해보기.
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";

    // Sql Session 을 만들건데 크게 만듬. 딱 1개만
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory == null) { // sql 세션이 만들어진적이 없다고 한다면,
            Environment environment = new Environment( // 환경설정을 할거임.
                    "dev",
                    new JdbcTransactionFactory(),
                    new PooledDataSource(DRIVER, URL, USER, PASSWORD)
            );

            // 위에서 만들어둔 환경설정을 넣어줌.
            Configuration configuration = new Configuration(environment);

            //  필기. 작성한 MenuMapper 인터페이스를 mapper 로 등록!!!
            configuration.addMapper(MenuMapper.class);

            // 환경설정까지 만들어봄. ( XML 과 대조해봄. )

            // build 를 해줘야 작동함. 위에 configuration 을 넣어줌.
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }

        return sqlSessionFactory.openSession(false); // static 이라서 return 필요함
    }

}

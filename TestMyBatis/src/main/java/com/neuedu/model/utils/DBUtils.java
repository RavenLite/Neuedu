package com.neuedu.model.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBUtils {
    public static SqlSession getInstance() {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        SqlSession session = null;
        try {
            // 1. Create a sqlsessionFactory
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // org.apache.ibatis.session.defaults.DefaultSqlSessionFactory@35bbe5e8
            // 35bbe5e8 this is hashcode for this object
            // (this hashcode is calculated according to object memory address)
//        System.out.println(sqlSessionFactory);

            // 2. Use SqlSessionFactory to get SqlSession(equal to connection)
            session = sqlSessionFactory.openSession();
//        System.out.println(session);
            return session;
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

}

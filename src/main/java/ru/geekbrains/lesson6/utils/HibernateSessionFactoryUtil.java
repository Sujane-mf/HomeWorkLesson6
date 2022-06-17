package ru.geekbrains.lesson6.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try{
                sessionFactory = new Configuration()
                        .configure("configs/hibernate.cfg.xml")
                        .buildSessionFactory();
            }catch (Exception e){
                e.printStackTrace();
            }
        } return sessionFactory;
    }
}

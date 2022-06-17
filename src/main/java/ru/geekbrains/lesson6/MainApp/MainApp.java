package ru.geekbrains.lesson6.MainApp;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson6.config.ApplicationConfiguration;
import ru.geekbrains.lesson6.services.CustomerService;
import ru.geekbrains.lesson6.utils.HibernateSessionFactoryUtil;

public class MainApp {
    public static void main(String[] args) {
        CustomerService cs = new CustomerService();
        cs.findCustomer(1L);
        //cs.findAllCustomer();
        //cs.findAllCustomerOrdersByCustomerId(1L);
    }
}

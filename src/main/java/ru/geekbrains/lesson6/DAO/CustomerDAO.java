package ru.geekbrains.lesson6.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson6.config.ApplicationConfiguration;
import ru.geekbrains.lesson6.models.Customer;
import ru.geekbrains.lesson6.models.Order;
import ru.geekbrains.lesson6.utils.HibernateSessionFactoryUtil;

import java.util.List;
@Repository
public class CustomerDAO {
    @Autowired
    public CustomerDAO() {
    }

    public Customer findCustomerByID(Long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Customer.class, id);
    }

    public List<Order> findOrdersByCustomerId(Long id){
        List<Order> orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Customer.class, id).getOrders();
        return orders;
    }

    public void createCustomer(){
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Customer customer = new Customer("Catwoman");
            session.save(customer);
            session.getTransaction().commit();
        }
    }

    public void saveCustomer(Customer customer){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(customer);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(Customer customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }
// поправить, перевести Sessionfactory в bean?
//    public void updateCustomer(Long id) {
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
//            session.beginTransaction();
//            Customer customer= session.get(Customer.class, id);
//            Customer.setName("Joker");
//            session.getTransaction().commit();
//        }
//    }
    public void delete(Customer customer) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    public List<Customer> findAll() {
        List<Customer> customers = (List<Customer>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Customer").list();
        return customers;
    }
}

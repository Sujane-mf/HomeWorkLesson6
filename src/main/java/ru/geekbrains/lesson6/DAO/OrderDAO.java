package ru.geekbrains.lesson6.DAO;

import jdk.jfr.Registered;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson6.models.Customer;
import ru.geekbrains.lesson6.models.Order;
import ru.geekbrains.lesson6.utils.HibernateSessionFactoryUtil;

import java.util.List;
@Repository
public class OrderDAO {
    @Autowired
    public OrderDAO() {
    }

    public Order findOrderByID(Long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Order.class, id);
    }

    public void createOrderForCustomer(Customer customer){
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Order order = new Order(customer);
            session.save(order);
            session.getTransaction().commit();
        }
    }

    public void saveOrder(Order order){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(order);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(order);
        session.getTransaction().commit();
        session.close();
    }
    // поправить, перевести Sessionfactory в bean?
//    public void updateOrder(Long id, Customer customer) {
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
//            session.beginTransaction();
//            Order order = session.get(Order.class, id);
//            Order.setCustomer(customer);
//            session.getTransaction().commit();
//        }
//    }
    public void delete(Order order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
        session.close();
    }

    public List<Order> findAll() {
        List<Order> orders = (List<Order>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Order ").list();
        return orders;
    }

}

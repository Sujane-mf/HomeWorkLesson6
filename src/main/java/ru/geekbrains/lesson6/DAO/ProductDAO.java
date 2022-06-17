package ru.geekbrains.lesson6.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson6.models.Product;
import ru.geekbrains.lesson6.utils.HibernateSessionFactoryUtil;

import java.util.List;
@Repository
public class ProductDAO {
    @Autowired
    public ProductDAO() {
    }

    public Product findProductByID(Long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Product.class, id);
    }

    public void createProduct(){
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = new Product("Eggplant", 13);
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public void saveProduct(Product product){
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void update(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        session.close();
    }
    // поправить, перевести Sessionfactory в bean?
//    public void updateProduct(Long id, int cost) {
//        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession()) {
//            session.beginTransaction();
//            Product product = session.get(Product.class, id);
//            Product.setCost(cost);
//            session.getTransaction().commit();
//        }
//    }
    public void delete(Product product) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }

    public List<Product> findAll() {
        List<Product> products = (List<Product>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Product ").list();
        return products;
    }
}

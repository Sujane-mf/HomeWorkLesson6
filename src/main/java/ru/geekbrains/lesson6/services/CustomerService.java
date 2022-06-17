package ru.geekbrains.lesson6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.lesson6.DAO.CustomerDAO;
import ru.geekbrains.lesson6.DAO.OrderDAO;
import ru.geekbrains.lesson6.DAO.ProductDAO;
import ru.geekbrains.lesson6.models.Customer;
import ru.geekbrains.lesson6.models.Order;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CustomerService {
    private CustomerDAO cDAO = new CustomerDAO();
    private OrderDAO oDAO = new OrderDAO();
    private ProductDAO pDAO = new ProductDAO();

    public CustomerService() {
    }


    public Customer findCustomer(long id) {
        return cDAO.findCustomerByID(id);
    }

    public void saveCustomer(Customer customer) {
        cDAO.saveCustomer(customer);
    }

    public void deleteCustomer(Customer customer) {
        cDAO.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        cDAO.update(customer);
    }

    public List<Customer> findAllCustomer() {
        return cDAO.findAll();
    }

    //поиск всех заказов юзера по ID
    public List<Order> findAllCustomerOrdersByCustomerId(Long id){
        return cDAO.findOrdersByCustomerId(id);
    }
}

package com.cpf.custom;

import com.cpf.frame4j.annotation.FService;
import com.cpf.frame4j.dao.FrameDao;
import com.cpf.frame4j.dao.IDao;

import java.util.List;

@FService
public class CustomerService {

    IDao fDao = FrameDao.getInstance();

    public boolean insert(Customer customer) {
        return fDao.insert(customer);
    }

    public int delete(int id) {
        return fDao.delete(Customer.class, id);
    }

    public int delete(Customer customer) {
        return fDao.delete(customer);
    }

    public int update(Customer customer) {
        return fDao.update(customer);
    }

    public Customer find(int id) {
        return fDao.find("select * from customer where id = ?", Customer.class, id);
    }

    public List<Customer> findList(String keywords) {
        String sql = "select * from customer";
        return fDao.findList(sql, Customer.class);
    }

}

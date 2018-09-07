package com.cpfframe4j.model;

import com.cpfframe4j.annotation.DbTable;
import com.cpfframe4j.dao.BaseEntity;
import com.cpfframe4j.service.CustomerService;

@DbTable(table = "Customer2")
public class Record extends BaseEntity {

    public static void main(String[] args) {
        int id = 101;
        CustomerService service = new CustomerService();

        System.out.println(service.findList("s"));


        Customer customer = new Customer();
        customer.setId(id);
        customer.setRemark("remark");
        customer.setPhone("4343");
        customer.setName("cpf");
        customer.setEmail("ERMAILLLL");
        customer.setContact("Singjie");
        boolean insert = service.insert(customer);
        System.out.println("insert :" + insert);


        Customer customer1 = service.find(id);
        customer1.setRemark("更改备注");
        int update = service.update(customer1);
        System.out.println("update : " + update);

        System.out.println(service.find(id));

        System.out.println(service.findList("s"));
        System.out.println("delete");
        System.out.println(service.delete(customer));
        System.out.println(service.delete(11));
        System.out.println(service.delete(12));

        System.out.println(service.findList("s"));
    }

}

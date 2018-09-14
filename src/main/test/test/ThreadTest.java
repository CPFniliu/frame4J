package test;

import com.cpf.custom.Customer;
import com.cpf.frame4j.dao.FrameDao;

import java.util.List;
import java.util.Random;

public class ThreadTest extends Thread{
    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FrameDao instance = FrameDao.getInstance();
        String sql = "select * from customer where id >= (SELECT(FLOOR(RAND() * (select MAX(id) from customer)))) LIMIT 1";
        List<Customer> list = instance.findList(sql, Customer.class);
        System.out.println(list);
    }
}

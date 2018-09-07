package test;

import com.cpfframe4j.service.CustomerService;
import org.junit.Before;
import org.junit.Test;

public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest() {
        customerService = new CustomerService();
    }

    @Before
    public void init(){
        System.out.println("innnnn");
        // TODO 初始化数据库
    }

    {
        // TODO 所有继承 IdbEntity的对象都必须加上 DbTable的注解
    }

    @Test
    public void findCustomerList(){
        System.out.println("发现所有数据");
    }



}

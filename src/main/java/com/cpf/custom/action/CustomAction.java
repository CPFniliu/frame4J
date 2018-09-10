package com.cpf.custom.action;

import com.cpf.custom.Customer;
import com.cpf.custom.CustomerService;
import com.cpf.frame4j.annotation.FAction;
import com.cpf.frame4j.annotation.FController;
import com.cpf.frame4j.annotation.FInject;
import com.cpf.frame4j.controller.Param;
import com.cpf.frame4j.controller.View;
import com.cpf.frame4j.util.config.ERequestType;

import java.util.List;

@FController
public class CustomAction {

    @FInject
    private CustomerService customerService;

    @FAction(mapping = "/jsp/hello.jsp", type = ERequestType.post)
    public void findList(){

        List<Customer> f = customerService.findList("f");
        System.out.println(f);
        System.out.println(0000);
    }

    @FAction(mapping = "/jsp/hello.jsp")
    public View findList0(Param param){
        System.out.println(param.getParamMap().toString());
        System.out.println(33333);
        List<Customer> f = customerService.findList("f");
        System.out.println(f);
        System.out.println(1111);
//        return new View("/hello.jsp");
        return new View(null);
    }

}

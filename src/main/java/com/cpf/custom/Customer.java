package com.cpf.custom;

import com.cpf.frame4j.annotation.DbTable;
import com.cpf.frame4j.dao.BaseEntity;

@DbTable(table = "CUSTOMER", id = "id")
public class Customer extends BaseEntity {

    public int getId() {
        return super.getInteger("id");
    }

    public void setId(Integer id) {
        super.set("id", id);
    }

    public String getName() {
        return super.getStr("name");
    }

    public void setName(String name) {
        super.set("name", name);
    }

    public String getContact() {
        return super.getStr("contact");
    }

    public void setContact(String contact) {
        super.set("contact", contact);
    }

    public String getPhone() {
        return super.getStr("phone");
    }

    public void setPhone(String phone) {
        super.set("phone", phone);
    }

    public String getEmail() {
        return super.getStr("email");
    }

    public void setEmail(String email) {
        super.set("email", email);
    }

    public String getRemark() {
        return super.getStr("remark");
    }

    public void setRemark(String remark) {
        super.set("remark", remark);
    }

}

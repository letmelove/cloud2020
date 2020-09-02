package org.example.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Payment;

import java.util.List;

@Mapper
public interface PaymentDao {

    @Insert("insert into payment (serial) values(#{payment.serial})")
    int create(@Param("payment") Payment payment);

    @Select("select * from payment where id=#{id}")
    Payment getPaymentById(Integer id);

    @Select("select * from payment")
    List<Payment> getPaymentAll();
}

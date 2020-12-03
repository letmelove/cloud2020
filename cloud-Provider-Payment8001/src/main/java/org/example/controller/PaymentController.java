package org.example.controller;

import com.tx.pojo.CommonResult;
import com.tx.pojo.Payment;
import lombok.extern.slf4j.Slf4j;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment")
    public CommonResult create(Payment payment){
        //System.out.println("内容为："+payment.getSerial());
        int result=paymentService.create(payment);
        log.info("插入结果："+result);
        if(result>0){
            return new CommonResult(200,"插入数据库成功",result);
        }
        return new CommonResult(400,"插入数据库失败",null);

    }

    @GetMapping("/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Integer id){
        Payment payment=paymentService.getPaymentById(id);
        log.info("查询结果:"+payment.toString());
        if(payment!=null){
            return new CommonResult(200,"查询数据库成功",payment.toString());
        }
        return new CommonResult(500,"插入数据库成功",null);
    }

    @GetMapping("/all")
    public CommonResult getPaymentAll(){
        List<Payment> paymentList =paymentService.getPaymentAll();
        log.info("结果为："+paymentList.toString());
        if(paymentList!=null){
            return new CommonResult(200,"查询成功",paymentList.toString());
        }
        return new CommonResult(500,"查询失败",null);
    }

}

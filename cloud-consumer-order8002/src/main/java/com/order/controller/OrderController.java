package com.order.controller;


import com.tx.pojo.CommonResult;
import com.tx.pojo.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@EnableDiscoveryClient
public class OrderController {
    //单机版时使用的地址
    //public static final String PAYMENT_URL = "http://localhost:8001";
    //负载均衡后使用服务名作为地址(这里需要@LoadBalanced注解来开启负载均衡)
    //这里一定要加http://
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;

    /*服务发现*/
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL+"/payment?serial="+payment.getSerial(),payment,CommonResult.class);
    }

    //@PathVariable:接收请求路径中占位符的值,通过 @PathVariable 可以将URL中占位符参数{xxx}绑定到处理器类的方法形参中@PathVariable(“xxx“)
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") long id){
        return restTemplate.getForObject(PAYMENT_URL+"/get/"+id,CommonResult.class);
    }

    /**
     * 服务发现
     */
    @GetMapping("/service")
    public  void discovery(){
        List<String> serversList =discoveryClient.getServices();
        List<String> serviceName=new ArrayList<>();
        List<ServiceInstance> instanceList;
        for (String server:serversList){
            //得到eureka中注册的所有服务名
            serviceName.add(server);
        }
        for (int i = 0; i < serviceName.size(); i++) {
            instanceList=discoveryClient.getInstances(serviceName.get(i));
            for (ServiceInstance instance :instanceList) {
                System.out.println("服务名为："+instance.getServiceId()+"\t主机ip："+instance.getHost()+"\t端口号："+instance.getPort()+"\t地址：："+instance.getUri());
            }
        }
    }

}

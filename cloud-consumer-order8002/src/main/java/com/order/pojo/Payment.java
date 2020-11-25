package com.order.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data   //提供类的get、set、equals、hashCode、canEqual、toString方法
@AllArgsConstructor //注在类上，提供类的全参构造
@NoArgsConstructor //提供类的无参构造
public class Payment implements Serializable {
        private Integer id;
        private String serial;
}

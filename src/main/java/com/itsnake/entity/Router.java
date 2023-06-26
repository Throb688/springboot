package com.itsnake.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("router")
public class Router {

    private Integer id;
    private String routername;
    private String url;
}

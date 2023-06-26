package com.itsnake.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("log")
public class Log {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private  String keeptime;
    private String operation;
    private String controllerurl;
    private String username;
    private String timedate;
}

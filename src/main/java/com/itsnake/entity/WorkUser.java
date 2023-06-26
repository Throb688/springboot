package com.itsnake.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("work_user")
public class WorkUser {

    private Integer id ;
    private Integer wid;
    private String username;
    private String worktext;
    private String subtime;
}

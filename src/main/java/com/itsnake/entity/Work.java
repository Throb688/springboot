package com.itsnake.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("work")
public class Work {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String workname;
    private String statrtime;
    private String lasttime;
    private Integer rid;
    private Integer countnum;

    @TableField(exist = false)
    private String teaname;
    @TableField(exist = false)
    private boolean status;
}

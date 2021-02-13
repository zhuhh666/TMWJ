package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class QueryUserInfoResVo implements Serializable {
    private String email;

    private String phone;

    private String username;

    private String userid;

    @JSONField(name = "update_time")
    private String updateTime;

    private String area;

    private String status;
}

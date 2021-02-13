package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author: Victor
 * @date 2021/1/14 11:44
 **/
@Data
public class UpdateMeetingUser implements Serializable {
    @JSONField(ordinal = 4)
    private String email;
    @JSONField(ordinal = 2)
    private String phone;
    @JSONField(ordinal = 1)
    private String username;
    @JSONField(ordinal = 3)
    private String userid;
}

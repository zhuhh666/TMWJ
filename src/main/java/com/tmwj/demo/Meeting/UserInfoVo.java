package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * description:
 * 会议用户实体
 * @author: Victor
 * @date 2021/1/14 12:31
 **/
@Data
public class UserInfoVo {
    private String email;

    private String phone;

    private String username;

    private String userid;

    @JSONField(name = "update_time")
    private String updateTime;

    private String area;
    @JSONField(name = "avatar_url")
    private String avatarUrl;

    private String status;
}

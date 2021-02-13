package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class MeetingInfoVo implements Serializable {

    private String subject;

    @JSONField(name = "meeting_id")
    private String meetingId;

    @JSONField(name = "meeting_code")
    private String meetingCode;

    private String password;

    private String status;

    @JSONField(name = "start_time")
    private String startTime;

    @JSONField(name = "end_time")
    private String endTime;

    @JSONField(name = "join_url")
    private String joinUrl;

    private MediaSetting settings;

    private List<UserVo> hosts;

    private List<UserVo> participants;
}

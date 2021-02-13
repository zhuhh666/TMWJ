package com.tmwj.demo.Meeting;

import lombok.Data;

import java.util.List;

/**
 * description:
 * 快速会议
 * @author: Victor
 * @date 2021/1/14 14:21
 **/
@Data
public class QuickMeeting {
    private String userid;
    private Integer instanceid;
    private String subject;
    private Integer type;
    private String start_time;
    private String end_time;
    private List<String> authClass;
}

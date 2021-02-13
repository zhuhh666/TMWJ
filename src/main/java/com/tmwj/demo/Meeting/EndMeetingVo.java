package com.tmwj.demo.Meeting;

import lombok.Data;

/**
 * description:
 *
 * @author: Victor
 * @date 2021/1/14 13:47
 **/
@Data
public class EndMeetingVo {
    String meetingId;
    String userId;
    Integer instanceid=1;
    String reason="取消会议";
}

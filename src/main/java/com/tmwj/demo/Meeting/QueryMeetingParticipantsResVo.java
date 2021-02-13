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
public class QueryMeetingParticipantsResVo implements Serializable {

    @JSONField(name = "meeting_id")
    private String meetingId;

    @JSONField(name = "meeting_code")
    private String meetingCode;

    private String subject;

    @JSONField(name = "schedule_start_time")
    private String scheduleStartTime;

    @JSONField(name = "schedule_end_time")
    private String scheduleEndTime;

    private List<UserVo> participants;
}

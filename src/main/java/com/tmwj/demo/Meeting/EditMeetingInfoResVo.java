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
public class EditMeetingInfoResVo implements Serializable {

    @JSONField(name = "meeting_number")
    private Integer meetingNumber;

    @JSONField(name = "meeting_info_list")
    private List<MeetingInfoVo> meetingInfoVoList;
}

package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class QueryMeetingParticipantsReqVo implements Serializable {

    @JSONField(name = "meeting_id")
    private String meetingId;

    private String userId;


    public static final class Builder {
        private String meetingId;
        private String userId;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withMeetingId(String meetingId) {
            this.meetingId = meetingId;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public QueryMeetingParticipantsReqVo build() {
            QueryMeetingParticipantsReqVo queryMeetingParticipantsReqVo = new QueryMeetingParticipantsReqVo();
            queryMeetingParticipantsReqVo.setMeetingId(meetingId);
            queryMeetingParticipantsReqVo.setUserId(userId);
            return queryMeetingParticipantsReqVo;
        }
    }
}

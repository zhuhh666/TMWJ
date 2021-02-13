package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class QueryMeetingByCodeRegVo {

    @NonNull
    @JSONField(name = "meeting_code")
    private String meeting_code;

    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @NonNull
    @JSONField(name = "instanceid")
    private Integer instanceId;


    public static final class Builder {
        private String meeting_code;
        private String userId;
        private Integer instanceId;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withMeeting_code(String meeting_code) {
            this.meeting_code = meeting_code;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withInstanceId(Integer instanceId) {
            this.instanceId = instanceId;
            return this;
        }

        public QueryMeetingByCodeRegVo build() {
            return new QueryMeetingByCodeRegVo(meeting_code, userId, instanceId);
        }
    }
}

package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class QueryMeetingByIdRegVo {

    @NonNull
    @JSONField(name = "meetingId")
    private String meetingId;

    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @NonNull
    @JSONField(name = "instanceid")
    private int instanceId;


    public static final class Builder {
        private String meetingId;
        private String userId;
        private int instanceId;

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

        public Builder withInstanceId(Integer instanceId) {
            this.instanceId = instanceId;
            return this;
        }

        public QueryMeetingByIdRegVo build() {
            return new QueryMeetingByIdRegVo(meetingId, userId, instanceId);
        }
    }
}

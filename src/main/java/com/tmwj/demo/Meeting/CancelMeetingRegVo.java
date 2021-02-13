package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class CancelMeetingRegVo implements Serializable {

    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @NonNull
    @JSONField(name = "instanceid")
    private Integer instanceId;

    @NonNull
    @JSONField(name = "reason_code")
    private Integer reasonCode;

    @JSONField(name = "reason_detail")
    private String reasonDetail;


    public static final class Builder {
        private String userId;
        private Integer instanceId;
        private Integer reasonCode;
        private String reasonDetail;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withInstanceId(Integer instanceId) {
            this.instanceId = instanceId;
            return this;
        }

        public Builder withReasonCode(Integer reasonCode) {
            this.reasonCode = reasonCode;
            return this;
        }

        public Builder withReasonDetail(String reasonDetail) {
            this.reasonDetail = reasonDetail;
            return this;
        }

        public CancelMeetingRegVo build() {
            CancelMeetingRegVo cancelMeetingRegVo = new CancelMeetingRegVo( userId, instanceId, reasonCode);
            cancelMeetingRegVo.setReasonDetail(reasonDetail);
            return cancelMeetingRegVo;
        }
    }
}

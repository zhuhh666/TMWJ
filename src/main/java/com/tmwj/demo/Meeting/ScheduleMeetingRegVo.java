package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class ScheduleMeetingRegVo implements Serializable {

    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @NonNull
    @JSONField(name = "instanceid")
    private Integer instanceId;

    @NonNull
    private String subject;

    @NonNull
    private Integer type;

    private List<UserVo> hosts;

    private List<UserVo> invitees;

    @NonNull
    @JSONField(name = "start_time")
    private String startTime;

    @NonNull
    @JSONField(name = "end_time")
    private String endTime;

    private String password;

    private MediaSetting settings;
    private List<String> authClass;


    public static final class Builder {
        private String userId;
        private Integer instanceId;
        private String subject;
        private Integer type;
        private List<UserVo> hosts;
        private List<UserVo> invitees;
        private String startTime;
        private String endTime;
        private String password;
        private MediaSetting settings;

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

        public Builder withSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder withType(Integer type) {
            this.type = type;
            return this;
        }

        public Builder withHosts(List<UserVo> hosts) {
            this.hosts = hosts;
            return this;
        }

        public Builder withInvitees(List<UserVo> invitees) {
            this.invitees = invitees;
            return this;
        }

        public Builder withStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withSettings(MediaSetting settings) {
            this.settings = settings;
            return this;
        }

        public ScheduleMeetingRegVo build() {
            ScheduleMeetingRegVo scheduleMeetingRegVo = new ScheduleMeetingRegVo(userId, instanceId, subject, type, startTime, endTime);
            scheduleMeetingRegVo.setHosts(hosts);
            scheduleMeetingRegVo.setInvitees(invitees);
            scheduleMeetingRegVo.setPassword(password);
            scheduleMeetingRegVo.setSettings(settings);
            return scheduleMeetingRegVo;
        }
    }
}

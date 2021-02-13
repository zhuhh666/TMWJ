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
public class EditMeetingRegVo implements Serializable {


    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @NonNull
    @JSONField(name = "instanceid")
    private Integer instanceId;

    @NonNull
    private String subject;

    private List<UserVo> hosts;

    private List<UserVo> invitees;

    @JSONField(name = "start_time")
    private String startTime;

    @JSONField(name = "end_time")
    private String endTime;

    private String password;

    private MediaSetting settings;


    public static final class Builder {

        private String userId;
        private Integer instanceId;
        private String subject;
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

        public EditMeetingRegVo build() {
            EditMeetingRegVo editMeetingRegVo = new EditMeetingRegVo( userId, instanceId, subject);
            editMeetingRegVo.setHosts(hosts);
            editMeetingRegVo.setInvitees(invitees);
            editMeetingRegVo.setStartTime(startTime);
            editMeetingRegVo.setEndTime(endTime);
            editMeetingRegVo.setPassword(password);
            editMeetingRegVo.setSettings(settings);
            return editMeetingRegVo;
        }
    }
}

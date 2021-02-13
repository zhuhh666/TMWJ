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
public class MediaSetting implements Serializable {

    @NonNull
    @JSONField(name = "mute_enable_join")
    private Boolean muteEnableJoin;


    @JSONField(name = "allow_unmute_self")
    private Boolean allowUnmuteSelf;

    @JSONField(name = "mute_all")
    private Boolean muteAll;

    @JSONField(name = "host_video")
    private Boolean hostVideo;

    @JSONField(name = "participant_video")
    private Boolean participantVideo;

    @JSONField(name = "enable_record")
    private Boolean enableRecord;

    @JSONField(name = "play_ivr_on_leave")
    private Boolean playIvrOnLeave;

    @JSONField(name = "play_ivr_on_join")
    private Boolean playIvrOnJoin;

    @JSONField(name = "live_url")
    private Boolean liveUrl;


    public static final class Builder {
        private Boolean muteEnableJoin;
        private Boolean allowUnmuteSelf;
        private Boolean muteAll;
        private Boolean hostVideo;
        private Boolean participantVideo;
        private Boolean enableRecord;
        private Boolean playIvrOnLeave;
        private Boolean playIvrOnJoin;
        private Boolean liveUrl;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withMuteEnableJoin(Boolean muteEnableJoin) {
            this.muteEnableJoin = muteEnableJoin;
            return this;
        }

        public Builder withAllowUnmuteSelf(Boolean allowUnmuteSelf) {
            this.allowUnmuteSelf = allowUnmuteSelf;
            return this;
        }

        public Builder withMuteAll(Boolean muteAll) {
            this.muteAll = muteAll;
            return this;
        }

        public Builder withHostVideo(Boolean hostVideo) {
            this.hostVideo = hostVideo;
            return this;
        }

        public Builder withParticipantVideo(Boolean participantVideo) {
            this.participantVideo = participantVideo;
            return this;
        }

        public Builder withEnableRecord(Boolean enableRecord) {
            this.enableRecord = enableRecord;
            return this;
        }

        public Builder withPlayIvrOnLeave(Boolean playIvrOnLeave) {
            this.playIvrOnLeave = playIvrOnLeave;
            return this;
        }

        public Builder withPlayIvrOnJoin(Boolean playIvrOnJoin) {
            this.playIvrOnJoin = playIvrOnJoin;
            return this;
        }

        public Builder withLiveUrl(Boolean liveUrl) {
            this.liveUrl = liveUrl;
            return this;
        }

        public MediaSetting build() {
            MediaSetting mediaSetting = new MediaSetting(muteEnableJoin);
            mediaSetting.setMuteAll(muteAll);
            mediaSetting.setHostVideo(hostVideo);
            mediaSetting.setParticipantVideo(participantVideo);
            mediaSetting.setEnableRecord(enableRecord);
            mediaSetting.setPlayIvrOnLeave(playIvrOnLeave);
            mediaSetting.setPlayIvrOnJoin(playIvrOnJoin);
            mediaSetting.setLiveUrl(liveUrl);
            mediaSetting.setAllowUnmuteSelf(allowUnmuteSelf);
            return mediaSetting;
        }
    }
}

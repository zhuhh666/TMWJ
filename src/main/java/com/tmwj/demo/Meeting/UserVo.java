package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NonNull;

/**
 * description:
 *
 * @author: Victor
 * @date 2021/1/14 12:32
 **/
@Data
public class UserVo {
    @NonNull
    @JSONField(name = "userid")
    private String userId;

    @JSONField(name = "is_anonymous")
    private String isAnonymous;

    @JSONField(name = "nick_name")
    private String nickName;


    public static final class Builder {
        private String userId;
        private String isAnonymous;
        private String nickName;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withIsAnonymous(String isAnonymous) {
            this.isAnonymous = isAnonymous;
            return this;
        }

        public Builder withNickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public UserVo build() {
            UserVo userVo = new UserVo(userId);
            userVo.setIsAnonymous(isAnonymous);
            userVo.setNickName(nickName);
            return userVo;
        }
    }
}

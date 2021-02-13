package com.tmwj.demo.apiManager;


import com.tmwj.demo.Meeting.*;

import java.text.ParseException;
import java.util.Map;

/**
 * description:
 * 腾讯会议，业务无关
 * @author: Victor
 * @date 2021/1/12 14:24
 **/
public interface IMeetingService {
    String callbackMeetingEvent(CallbackMeetingParam entity);
    String callbackCheck(String check_str);

    /**
     * 创建用户 ok
     */
    String newMeetingUser(UpdateMeetingUser user);

    /**
     * 更新用户 code=999，字段错误
     */
    String updateMeetingUser(UpdateMeetingUser user);

    /**
     * 获取用户信息 ok
     */
    QueryUserInfoResVo getUserInfo(String userId);

    /**
     * 查询用户列表 ok
     */
    QueryUserInfoList queryUserInfoList(Integer page, Integer pageSize);

    /**
     * 删除用户 业务逻辑无用
     */
    String deleteUser(String userId);



    //会议管理
    /**
     * 预定会议 ok
     */
    QueryMeetingInfoResVo scheduleMeeting(ScheduleMeetingRegVo scheduleMeetingRegVo);

    /**
     * 快速会议，默认时长两个小时  ok
     */
    QueryMeetingInfoResVo quickStart(QuickMeeting entity);

    /**
     * 查询会议信息 ok
     */
    QueryMeetingInfoResVo queryMeetingInfo(QueryMeetingByIdRegVo queryMeetingByIdRegVo);

    /**
     * 取消会议 ok
     */
    String cancelMeeting(CancelMeetingRegVo cancelMeetingRegVo, String meetingId);

    /**
     * 结束会议
     */
    String endMeeting(EndMeetingVo entity);

    /**
     * 编辑会议
     */
    EditMeetingInfoResVo editMeeting(EditMeetingRegVo editMeetingRegVo, String meetingId);

    /**
     * 查询参会人员
     */
    QueryMeetingParticipantsResVo queryMeetingParticipants(QueryMeetingParticipantsReqVo entity);

    /**
     * 查询会议列表 ok
     */
    QueryMeetingInfoResVo queryMeetingInfoList(QueryMeetingInfoListVo entity);

}

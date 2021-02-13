package com.tmwj.demo.apiManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tmwj.demo.Meeting.*;
import com.tmwj.demo.MeetingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.*;


/**
 * description:
 *
 * @author: Victor
 * @date 2021/1/12 14:25
 **/
@Service
@Slf4j
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {

    @Autowired
    private MeetingMapper mapper;
    @Autowired
    HttpServletRequest request; //这里可以获取到request

    //会议管理的域名
    private static String meeting_uri = "/v1/meetings";
    //用户管理的域名
    private static String user_uri = "/v1/users";

    @Override
    public String callbackMeetingEvent(CallbackMeetingParam entity){
        String notifyBase64 = entity.getData();
        if(MeetingUtil.validateSign(request,notifyBase64))
        {
            return "failed!";
        }
        final Base64.Decoder decoder = Base64.getDecoder();
        String notifyJsonStr=new String(decoder.decode(notifyBase64), StandardCharsets.UTF_8);//Base64解码后为json字符串
        String resJson = "";
        System.out.println("{腾讯会议结束回调}接收到的报文：" + notifyJsonStr);

        JSONObject notifyJson = JSONObject.parseObject(notifyJsonStr);//fastjson转换



        System.out.println("开始执行我的业务逻辑");
        //不同事件执行不同逻辑
        switch (notifyJson.getString("event"))
        {
            case "meeting.end":
                String meetingId=notifyJson
                        .getJSONArray("payload")
                        .getJSONObject(0)
                        .getJSONObject("meeting_info")
                        .getString("meeting_id");
                handleMeetingEnd(meetingId);
                break;
            default:
                //有空了放异常处理
                break;
        }

        return "successfully received callback";
    }

    public void handleMeetingEnd(String mid)
    {
        UpdateWrapper<Meeting> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda()
                .set(Meeting::getCallback,1)
                .in(Meeting::getId,mid);
        mapper.update(null,updateWrapper);
    }

    @Override
    public String callbackCheck(String check_str) {
        if(MeetingUtil.validateSign(request,check_str))
        {
            final Base64.Decoder decoder = Base64.getDecoder();
            return new String(decoder.decode(check_str), StandardCharsets.UTF_8);//Base64解码后为json字符串
        }
        return "failed!";
    }


    @Override
    public String newMeetingUser(UpdateMeetingUser user) {
        String body = JSON.toJSONString(user);
        String result = MeetingUtil.requestPost(body,user_uri);
        if(result.equals("{}")){
            return "success";
        }else {
            return "error_message:"+result;
        }
    }

    @Override
    public String updateMeetingUser(UpdateMeetingUser user) {
        String body = JSON.toJSONString(user);
        String uri = user_uri+"/"+user.getUserid();
        log.info(uri);
        String result = MeetingUtil.requestPut(body,uri);
        if(result.equals("{}")){
            return "success";
        }else {
            return "error_message:"+result;
        }
    }

    @Override
    public QueryUserInfoResVo getUserInfo(String userId) {
        String body = "";
        String uri = user_uri+"/"+userId;
        String result = MeetingUtil.requestGet(body,uri);
        return JSON.parseObject(result, QueryUserInfoResVo.class);
    }

    @Override
    public QueryUserInfoList queryUserInfoList(Integer page, Integer pageSize) {
        String body = "";
        String uri = user_uri+"/list?page="+page+"&page_size="+pageSize;
        log.info(uri);
        String result = MeetingUtil.requestGet(body,uri);
        return JSON.parseObject(result, QueryUserInfoList.class);
    }

    @Override
    public String deleteUser(String userId) {
        String body = "";
        String uri = user_uri+"/"+userId;
        String result = MeetingUtil.requestDelete(body,uri);
        if(result.equals("{}")){
            return "success";
        }else {
            return "error_message:"+result;
        }
    }

    @Override
    public QueryMeetingInfoResVo scheduleMeeting(ScheduleMeetingRegVo scheduleMeetingRegVo) {
        String body = JSON.toJSONString(scheduleMeetingRegVo);
        String result = MeetingUtil.requestPost(body,meeting_uri);
        log.info("request body --->" + body);
        return JSON.parseObject(result, QueryMeetingInfoResVo.class);
    }

    @Override
    public QueryMeetingInfoResVo quickStart(QuickMeeting entity) {
        Long startTime = System.currentTimeMillis()/1000;
        Long endTime = startTime+7200;
        entity.setStart_time(String.valueOf(startTime));
        entity.setEnd_time(String.valueOf(endTime));
        entity.setType(1);
        String body = JSON.toJSONString(entity);
        System.out.println(body);
        String result = MeetingUtil.requestPost(body,meeting_uri);
        return JSON.parseObject(result,QueryMeetingInfoResVo.class);
    }

    @Override
    public QueryMeetingInfoResVo queryMeetingInfo(QueryMeetingByIdRegVo entity) {
        String body = "";
        String uri = meeting_uri+"/"+entity.getMeetingId()+"?userid="+entity.getUserId()+"&instanceid="+entity.getInstanceId();
        String result = MeetingUtil.requestGet(body,uri);
        return JSON.parseObject(result,QueryMeetingInfoResVo.class);
    }

    @Override
    public String cancelMeeting(CancelMeetingRegVo cancelMeetingRegVo, String meetingId) {
        String body = JSON.toJSONString(cancelMeetingRegVo);
        String uri = meeting_uri+"/"+meetingId+"/cancel";
        String result = MeetingUtil.requestPost(body,uri);
        if(result.equals("{}")){
            return "success";
        }else {
            return "error_message:"+result;
        }
    }

    @Override
    public String endMeeting(EndMeetingVo entity) {
        String body = JSON.toJSONString(entity);
        String uri = meeting_uri+"/"+entity.getMeetingId()+"/dismiss";
        String result = MeetingUtil.requestPost(body,uri);
        if(result.equals("{}")){
            return "success";
        }else {
            return "error_message:"+result;
        }
    }

    @Override
    public EditMeetingInfoResVo editMeeting(EditMeetingRegVo editMeetingRegVo, String meetingId) {
        String body = JSON.toJSONString(editMeetingRegVo);
        log.info("request body --->" + body);
        String uri = meeting_uri+"/"+meetingId;
        String result = MeetingUtil.requestPut(body,uri);
        return JSON.parseObject(result, EditMeetingInfoResVo.class);
    }

    @Override
    public QueryMeetingParticipantsResVo queryMeetingParticipants(QueryMeetingParticipantsReqVo entity) {
        String body = "";
        String uri = meeting_uri+"/"+entity.getMeetingId()+"/participants?userid="+entity.getUserId();
        String result = MeetingUtil.requestGet(body,uri);
        return JSON.parseObject(result, QueryMeetingParticipantsResVo.class);
    }

    @Override
    public QueryMeetingInfoResVo queryMeetingInfoList(QueryMeetingInfoListVo entity) {
        String body = "";
        String uri = meeting_uri+"?userid="+entity.getUserId()+"&instanceid="+entity.getInstanceId();
        String result = MeetingUtil.requestGet(body,uri);
        return JSON.parseObject(result,QueryMeetingInfoResVo.class);
    }

}

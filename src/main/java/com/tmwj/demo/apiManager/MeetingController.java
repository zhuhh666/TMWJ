package com.tmwj.demo.apiManager;

import com.tmwj.demo.Meeting.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  腾讯会议 前端控制器
 * </p>
 *
 * @author hhzhu
 * @since 2021-01-06 22:46
 */
@RestController
@Api(tags = "腾讯会议接入")
@RequestMapping("/Meeting")
public class MeetingController {
    @Autowired
    private IMeetingService meetingService;

    @ApiOperation("快速会议")
    @PostMapping("/quick_start")
    public QueryMeetingInfoResVo quick_start(@RequestBody QuickMeeting entity){
        return meetingService.quickStart(entity);
    }

    @ApiOperation("会议回调事件")
    @PostMapping("/callbackMeetingEvent")
    public String callbackMeetingEvent(@RequestBody CallbackMeetingParam entity){
        return meetingService.callbackMeetingEvent(entity);
    }

    @ApiOperation("会议回调GET方法检验")
    @GetMapping("/callbackMeetingEvent")
    public String callbackCheck(@RequestParam String check_str){
        return meetingService.callbackCheck(check_str);
    }

}

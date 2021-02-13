package com.tmwj.demo.Meeting;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Victor
 * @since 2021-01-13
 */
@Data
@TableName("meeting")
@EqualsAndHashCode(callSuper = false)
public class Meeting extends Model<Meeting> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议ID
     */
    @TableId
    private String id;

    /**
     * 会议主题
     */
    private String subject;

    /**
     * 会议类型，0是普通会议，1是快速会议
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 会议创建人
     */
    private String owner;
    private String meetingCode;
    private String password;
    private String joinUrl;
    private String replayUrl;
    @TableField("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    private int callback;
    @TableField(exist = false)
    private String status;
    @TableField("auth_class")
    private String authClass;

    /**
     * 0有效，1无效
     */
    @TableLogic
    private int isDeleted;

    //获取当前的会议状态
    public int getStatusR(){
        if(callback==1){
            status = "已结束";
        }else {
            if(type == 1){
                status = "进行中";
            }else {
                if(startTime.after(new Date())){
                    status = "进行中";
                }else {
                    status = "待开始";
                }
            }
        }
        return 1;
    }

}

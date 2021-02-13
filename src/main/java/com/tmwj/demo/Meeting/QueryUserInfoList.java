package com.tmwj.demo.Meeting;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Victor
 * @date 2021/01/14
 */
@Data
public class QueryUserInfoList implements Serializable {

    @JSONField(name = "total_count")
    private Integer totalCount;

    @JSONField(name = "current_size")
    private Integer currentSize;

    @JSONField(name = "current_page")
    private Integer currentPage;

    @JSONField(name = "page_size")
    private Integer pageSize;

    private List<UserInfoVo> users;


}

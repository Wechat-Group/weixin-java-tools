package me.chanjar.weixin.qidian.bean.call;

import lombok.Data;

@Data
public class SwitchBoard {
    private String switchboard;
    private String createTime;
    private Boolean callinStatus;
    private Boolean calloutStatus;
    private String spName;
    private String cityName;
}

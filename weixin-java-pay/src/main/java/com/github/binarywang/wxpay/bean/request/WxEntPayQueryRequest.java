package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.bean.entpay.EntPayQueryRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import me.chanjar.weixin.common.util.ToStringUtils;

/**
 * 请使用 {@link EntPayQueryRequest}
 */
@XStreamAlias("xml")
@Deprecated
public class WxEntPayQueryRequest extends EntPayQueryRequest {
}

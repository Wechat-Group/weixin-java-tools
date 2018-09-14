package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 微信开放平台小程序第三方提交代码的页面配置列表
 *
 * @author yqx
 * @date 2018/9/12
 */
@Data
public class WxOpenMaPageListResult implements Serializable {

  private String errcode;
  private String errmsg;

  @SerializedName("page_list")
  List<String> pageList;

  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}

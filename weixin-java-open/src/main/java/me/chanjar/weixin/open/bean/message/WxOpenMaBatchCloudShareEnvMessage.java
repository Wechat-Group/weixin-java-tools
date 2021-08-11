package me.chanjar.weixin.open.bean.message;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信第三方平台-批量代云开发-绑定环境
 * @author caiqy
 */
@Data
public class WxOpenMaBatchCloudShareEnvMessage implements Serializable {

  private static final long serialVersionUID = -4701246956074808502L;

  /**
   * 环境ID
   */
  @SerializedName("env")
  private String env;

  /**
   * app ids
   */
  @SerializedName("appids")
  private List<String> appIds;

}

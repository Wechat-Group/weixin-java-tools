package cn.binarywang.wx.miniapp.bean.live;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 直播间小助手用户信息
 */
@Data
public class WxMaAssistantResult implements Serializable {
  private static final long serialVersionUID = 5829108618580715870L;

  private Integer count;
  private Integer maxCount;
  private Integer errcode;

  private List<Assistant> list;

  public static WxMaAssistantResult fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaAssistantResult.class);
  }
  @Data
  public static class Assistant implements Serializable {
    private static final long serialVersionUID = 6362128855371134033L;
    /**
     * 修改时间
     */
    private Long timestamp;
    /**
     * 头像
     **/
    private String headimg;
    /**
     * 用户昵称
     **/
    private String nickname;
    /**
     * 微信号
     **/
    private String alias;
    /**
     * openid
     **/
    private String openid;
  }
}

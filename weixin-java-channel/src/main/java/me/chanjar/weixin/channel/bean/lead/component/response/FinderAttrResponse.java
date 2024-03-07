package me.chanjar.weixin.channel.bean.lead.component.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 视频号账号信息
 * @author imyzt
 * @date 2024/01/27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FinderAttrResponse extends WxChannelBaseResponse {

  /**
   * 用户留资信息列表
   */
  @JsonProperty("finder_attr")
  private FinderAttr finderAttr;

  /**
   * 用户留资信息
   */
  @Data
  @NoArgsConstructor
  public static class FinderAttr {

    /**
     * 视频号唯一标识
     */
    @JsonProperty("uniq_id")
    private String uniqId;

    /**
     * 视频号昵称
     */
    @JsonProperty("nickname")
    private String nickname;

    /**
     * 视频号的粉丝数
     */
    @JsonProperty("fans_count")
    private int fansCount;
  }
}

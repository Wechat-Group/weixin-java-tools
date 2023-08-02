package me.chanjar.weixin.channel.bean.sharer;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分享员订单
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class SharerOrder implements Serializable {

  private static final long serialVersionUID = 1528673402572025670L;
  /** 订单号 */
  @JsonProperty("order_id")
  private String orderId;

  /** 分享场景 {@link me.chanjar.weixin.channel.enums.ShareScene} */
  @JsonProperty("sharer_scene")
  private Integer sharerScene;

  /** 分享员openid */
  @JsonProperty("sharer_openid")
  private String sharerOpenid;

  /** 分享员类型 {@link me.chanjar.weixin.channel.enums.SharerType} */
  @JsonProperty("sharer_type")
  private Integer sharerType;

  /** 视频号场景信息 */
  @JsonProperty("finder_scene_info")
  private FinderSceneInfo sceneInfo;

}

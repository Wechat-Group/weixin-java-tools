package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liming1019
 * @date 2021/8/6
 */
@Data
public class WxMaShopRegisterApplySceneRequest implements Serializable {

  private static final long serialVersionUID = -3008686013597621522L;
  /**
   * 1:视频号、公众号场景
   */
  @SerializedName("scene_group_id")
  private Long sceneGroupId;
}


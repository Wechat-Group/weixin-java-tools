package cn.binarywang.wx.miniapp.bean.express;

import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 运单轨迹对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressPath implements Serializable {

  private static final long serialVersionUID = 5643624677715536605L;

  /**
   * 用户openid
   */
  private String openid;

  /**
   * 快递公司 ID
   */
  @SerializedName("delivery_id")
  private String deliveryId;

  /**
   * 运单 ID
   */
  @SerializedName("waybill_id")
  private String waybillId;

  /**
   * 轨迹节点数量
   */
  @SerializedName("path_item_num")
  private Integer pathItemNum;

  /**
   * 轨迹节点列表
   */
  @SerializedName("path_item_list")
  private List<PathItem> pathItemList;

  public static WxMaExpressPath fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxMaExpressPath.class);
  }

  @Data
  private static class PathItem{

    /**
     * 轨迹节点 Unix 时间戳
     */
    @SerializedName("action_time")
    private Long actionTime;

    /**
     * 轨迹节点类型
     */
    @SerializedName("action_type")
    private Integer actionType;

    /**
     * 轨迹节点详情
     */
    @SerializedName("action_msg")
    private String actionMsg;

  }
}

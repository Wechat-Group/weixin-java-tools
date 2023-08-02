package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 获取接口列表 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class GetPrivacyInterfaceResult extends WxOpenResult {

  /**
   * 隐私接口列表
   */
  @SerializedName("interface_list")
  private List<Interface> interfaceList;


  /**
   * 隐私接口
   */
  @Getter
  @Setter
  public static class Interface {

    /**
     * 接口英文名称，如：wx.chooseAddress/wx.choosePoi/wx.getLocation/wx.onLocationChange/wx.chooseLocation
     */
    @SerializedName("api_name")
    private String apiName;

    /**
     * 接口中文名称，如：获取用户收货地址/选择位置，支持模糊定位（精确到市）和精确定位混选/获取当前的地理位置、速度/监听实时地理位置变化事件/打开地图选择位置
     */
    @SerializedName("api_ch_name")
    private String apiChName;

    /**
     * api描述
     */
    @SerializedName("api_desc")
    private String apiDesc;

    /**
     * 申请时间 ，该字段发起申请后才会有
     */
    @Nullable
    @SerializedName("apply_time")
    private String applyTime;

    /**
     * 接口状态，该字段发起申请后才会有，1待申请开通，2无权限，3申请中，4申请失败，5已开通
     */
    @Nullable
    @SerializedName("status")
    private String status;

    /**
     * 申请单号，该字段发起申请后才会有
     */
    @Nullable
    @SerializedName("audit_id")
    private String auditId;

    /**
     * 申请被驳回原因或者无权限，该字段申请驳回时才会有
     */
    @Nullable
    @SerializedName("fail_reason")
    private String failReason;

    /**
     * api文档链接
     */
    @SerializedName("api_link")
    private String apiLink;

    /**
     * 分组名，如：地理位置
     */
    @SerializedName("group_name")
    private String groupName;
  }
}

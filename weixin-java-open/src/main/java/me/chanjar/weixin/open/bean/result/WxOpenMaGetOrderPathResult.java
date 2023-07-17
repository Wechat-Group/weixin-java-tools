package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * @Description 获取订单页path信息接口返回结果
 * @createTime 2023/05/23 15:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaGetOrderPathResult extends WxOpenResult {

  private static final long serialVersionUID = 1988636135032104851L;

  /**
   * 订单页path信息
   */
  @SerializedName("msg")
  private MsgBean msg;

  @Data
  public static class MsgBean implements Serializable {
    private static final long serialVersionUID = 2153432209800394925L;

    /**
     * 订单页path
     */
    @SerializedName("path")
    private String path;
    /**
     * 申请提交的图片url，审核版会显示
     */
    @SerializedName("img_list")
    private List<String> imgList;
    /**
     * 申请提交的视频url，审核版会显示
     */
    @SerializedName("video")
    private String video;
    /**
     * 申请提交的测试账号，审核版会显示
     */
    @SerializedName("test_account")
    private String testAccount;
    /**
     * 申请提交的测试密码，审核版会显示
     */
    @SerializedName("test_pwd")
    private String testPwd;
    /**
     * 申请提交的测试备注，审核版会显示
     */
    @SerializedName("test_remark")
    private String testRemark;
    /**
     * 申请状态说明
     * 2	审核中
     * 3	审核失败
     * 4	审核通过
     */
    @SerializedName("status")
    private int status;
    /**
     * 申请时间
     */
    @SerializedName("apply_time")
    private long applyTime;
  }
}

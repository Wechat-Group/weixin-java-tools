package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author xzh
 * @Description 申请设置订单页path信息 接口的相关信息
 * @createTime 2023/05/23 15:19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxOpenMaApplyOrderPathInfo implements Serializable {

  private static final long serialVersionUID = -1812688861780119219L;
  /**
   * 批量申请的信息
   */
  @SerializedName("batch_req")
  private BatchReqBean batchReq;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class BatchReqBean implements Serializable {

    private static final long serialVersionUID = 1816976472441827961L;
    /**
     * 申请提交的订单页path
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
     * 申请提交的批量的appid
     * NOTE: 一次提交不超过100个appid
     */
    @SerializedName("appid_list")
    private List<String> appIdList;
  }
}

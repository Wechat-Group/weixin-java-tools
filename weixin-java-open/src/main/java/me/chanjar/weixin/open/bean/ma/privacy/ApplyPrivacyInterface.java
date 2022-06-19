package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 申请隐私接口
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class ApplyPrivacyInterface {

  /**
   * 接口英文名称，如：wx.chooseAddress/wx.choosePoi/wx.getLocation/wx.onLocationChange/wx.chooseLocation
   */
  @SerializedName("api_name")
  private String apiName;

  /**
   * 申请说原因，不超过300个字符；需要以utf-8编码提交，否则会出现审核失败
   */
  @SerializedName("content")
  private String content;

  /**
   * (辅助网页)例如，上传官网网页链接用于辅助审核
   */
  @SerializedName("url_list")
  private List<String> urlList;

  /**
   * (辅助图片)填写图片的url ，最多10个
   */
  @SerializedName("pic_list")
  private List<String> picList;

  /**
   * (辅助视频)填写视频的链接 ，最多支持1个；视频格式只支持mp4格式
   */
  @SerializedName("video_list")
  private List<String> videoList;
}

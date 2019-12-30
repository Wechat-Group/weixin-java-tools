package me.chanjar.weixin.mp.bean.comment;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.IntegerArrayConverter;
import me.chanjar.weixin.mp.bean.device.WxDeviceQrCodeResult;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 群发图文评论数据.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-08-30
 */
@Data
public class WxMpCommentListVo implements Serializable {
  private static final long serialVersionUID = 7604754799359751247L;

  /**
   * 总数，非comment的size.
   */
  private Integer total;

  /**
   * 评论列表.
   */
  private List<WxMpComment> comment;

  @Data
  public static class Reply implements Serializable {
    private static final long serialVersionUID = 9174739515408520429L;

    /**
     * 作者回复时间 .
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * 作者回复内容.
     */
    private String content;
  }

  @Data
  public static class WxMpComment implements Serializable {
    private static final long serialVersionUID = 5401188720891942634L;

    /**
     * 用户评论id .
     */
    @SerializedName("user_comment_id")
    private Integer userCommentId;

    /**
     * 用户openid.
     */
    private String openid;

    /**
     * 评论时间.
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * 评论内容.
     */
    private String content;

    /**
     * 是否精选评论，0为即非精选，1为true，即精选.
     */
    @SerializedName("comment_type")
    private Integer commentType;

    /**
     * 作者回复.
     */
    private Reply reply;
  }

  public static WxMpCommentListVo fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCommentListVo.class);
  }
}

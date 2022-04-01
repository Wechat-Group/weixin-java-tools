package cn.binarywang.wx.miniapp.bean.code;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 提交审核的请求
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaCodeSubmitAuditRequest implements Serializable {
  private static final long serialVersionUID = 8854979405505241314L;

  /**
   * 提交审核项的一个列表（至少填写1项，至多填写5项）
   */
  @SerializedName("item_list")
  private List<WxMaCategory> itemList;

  /**
   * feedback_info	String	否	反馈内容，至多 200 字
   */
  @SerializedName("feedback_info")
  private String feedbackInfo;

  /**
   * feedback_stuff	String	否	用 | 分割的 media_id 列表，至多 5 张图片, 可以通过新增临时素材接口上传而得到
   */
  @SerializedName("feedback_stuff")
  private String feedbackStuff;

  /**
   * preview_info	Object	否	预览信息（小程序页面截图和操作录屏）
   */
  @SerializedName("preview_info")
  private PreviewInfo previewInfo;

  /**
   * version_desc	String	否	小程序版本说明和功能解释
   */
  @SerializedName("version_desc")
  private String versionDesc;

  /**
   * ugc_declare	Object	否	用户生成内容场景（UGC）信息安全声明
   */
  @SerializedName("ugc_declare")
  private UgcDeclare ugcDeclare;

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

  @Data
  @Accessors(chain = true)
  public static class PreviewInfo implements Serializable {
    private static final long serialVersionUID = -3391652096859063951L;

    /**
     * video_id_list	String Array	否	录屏mediaid列表，可以通过提审素材上传接口获得
     */
    @SerializedName("video_id_list")
    private List<String> videoIdList;

    /**
     * pic_id_list	String Array	否	截屏mediaid列表，可以通过提审素材上传接口获得
     */
    @SerializedName("pic_id_list")
    private List<String> picIdList;
  }

  @Data
  @Accessors(chain = true)
  public static class UgcDeclare implements Serializable {
    private static final long serialVersionUID = 201470564426848261L;

    /**
     * scene	Number Array	否	UGC场景 0,不涉及用户生成内容, 1.用户资料,2.图片,3.视频,4.文本,5其他, 可多选,当scene填0时无需填写下列字段
     */
    @SerializedName("scene")
    private Integer[] scene;

    /**
     * other_scene_desc	String	否	当scene选其他时的说明,不超时256字
     */
    @SerializedName("other_scene_desc")
    private String otherSceneDesc;

    /**
     * method	Number Array	否	内容安全机制 1.使用平台建议的内容安全API,2.使用其他的内容审核产品,3.通过人工审核把关,4.未做内容审核把关
     */
    @SerializedName("method")
    private Integer[] method;

    /**
     * has_audit_team	Number	否	是否有审核团队, 0.无,1.有,默认0
     */
    @SerializedName("has_audit_team")
    private Integer hasAuditTeam;

    /**
     * audit_desc	String	否	说明当前对UGC内容的审核机制,不超过256字
     */
    @SerializedName("audit_desc")
    private String auditDesc;
  }
}

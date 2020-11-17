package me.chanjar.weixin.cp.bean.external;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 修改客户备注信息请求.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-09-19
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpUpdateRemarkRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * <pre>
   * 字段名：userid
   * 是否必须：是
   * 描述：企业成员的userid
   * </pre>
   */
  @SerializedName("userid")
  private String userId;

  /**
   * <pre>
   * 字段名：external_userid
   * 是否必须：是
   * 描述：外部联系人userid
   * </pre>
   */
  @SerializedName("external_userid")
  private String externalUserId;

  /**
   * <pre>
   * 字段名：remark
   * 是否必须：否
   * 描述：此用户对外部联系人的备注，最多20个字符
   * </pre>
   */
  @SerializedName("remark")
  private String remark;

  /**
   * <pre>
   * 字段名：description
   * 是否必须：否
   * 描述：此用户对外部联系人的描述，最多150个字符
   * </pre>
   */
  @SerializedName("description")
  private String description;

  /**
   * <pre>
   * 字段名：remark_company
   * 是否必须：否
   * 描述：此用户对外部联系人备注的所属公司名称，最多20个字符
   * </pre>
   */
  @SerializedName("remark_company")
  private String remarkCompany;

  /**
   * <pre>
   * 字段名：remark_mobiles
   * 是否必须：否
   * 描述：此用户对外部联系人备注的手机号
   * </pre>
   */
  @SerializedName("remark_mobiles")
  private String[] remarkMobiles;

  /**
   * <pre>
   * 字段名：remark_pic_mediaid
   * 是否必须：否
   * 描述：备注图片的mediaid，
   * </pre>
   */
  @SerializedName("remark_pic_mediaid")
  private String remarkPicMediaId;

}

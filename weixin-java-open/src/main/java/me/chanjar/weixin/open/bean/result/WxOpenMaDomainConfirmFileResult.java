package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 业务域名校验文件
 *
 * @author <a href="https://github.com/vostro2013">vostro2013</a>
 * @date 2023/06/06
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaDomainConfirmFileResult extends WxOpenResult {
  /**
   * 文件名
   */
  @SerializedName("file_name")
  private String fileName;

  /**
   * 文件内容
   */
  @SerializedName("file_content")
  private String fileContent;

  public static WxOpenMaDomainConfirmFileResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenMaDomainConfirmFileResult.class);
  }
}
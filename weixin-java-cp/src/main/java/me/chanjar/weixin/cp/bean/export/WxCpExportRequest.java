package me.chanjar.weixin.cp.bean.export;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.io.Serializable;

/**
 * 异步导出参数
 *
 * @author zhongjun
 * @date 2022/4/21
 **/
@Data
public class WxCpExportRequest implements Serializable {
  private static final long serialVersionUID = -8127528999898984359L;

  /**
   * base64encode的加密密钥，长度固定为43，base64decode之后即得到AESKey。加密方式采用AES-256-CBC方式，数据采用PKCS#7填充至32字节的倍数；IV初始向量大小为16字节，取AESKey前16字节，详见：<a href="http://tools.ietf.org/html/rfc2315">http://tools.ietf.org/html/rfc2315</a>
   */
  @SerializedName("encoding_aeskey")
  private String encodingAesKey;

  /**
   * 每块数据的部门数，支持范围[104,106]，默认值为10^6
   */
  @SerializedName("block_size")
  private Integer blockSize;

  /**
   * 需要导出的标签
   * 导出标签成员时使用
   */
  @SerializedName("tagid")
  private Integer tagId;

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}

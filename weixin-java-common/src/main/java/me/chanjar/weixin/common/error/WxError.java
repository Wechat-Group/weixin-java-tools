package me.chanjar.weixin.common.error;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import lombok.Builder;
import lombok.Data;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

/**
 * 微信错误码.
 * 请阅读：
 * 公众平台：<a href="http://mp.weixin.qq.com/wiki/10/6380dc743053a91c544ffd2b7c959166.html">全局返回码说明</a>
 * 企业微信：<a href="https://work.weixin.qq.com/api/doc#10649">全局错误码</a>
 *
 * @author Daniel Qian & Binary Wang
 */
@Data
@Builder
public class WxError implements Serializable {
  private static final long serialVersionUID = 7869786563361406291L;

  /**
   * 微信错误代码.
   */
  private int errorCode;

  /**
   * 微信错误信息.
   * （如果可以翻译为中文，就为中文）
   */
  private String errorMsg;

  /**
   * 微信接口返回的错误原始信息（英文）.
   */
  private String errorMsgEn;

  private String json;

  public static WxError fromJson(String json) {
    return fromJson(json, null);
  }

  public static WxError fromJson(String json, WxType type) {
    final WxError wxError = WxGsonBuilder.create().fromJson(json, WxError.class);
    if (StringUtils.isNotEmpty(wxError.getErrorMsg())) {
      wxError.setErrorMsgEn(wxError.getErrorMsg());
    }

    if (type == null) {
      return wxError;
    }

    if (type == WxType.MP) {
      final String msg = WxMpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
      if (msg != null) {
        wxError.setErrorMsg(msg);
      }
    } else if (type == WxType.CP) {
      final String msg = WxCpErrorMsgEnum.findMsgByCode(wxError.getErrorCode());
      if (msg != null) {
        wxError.setErrorMsg(msg);
      }
    }

    return wxError;
  }

  @Override
  public String toString() {
    if (this.json != null) {
      return this.json;
    }
    return "错误: Code=" + this.errorCode + ", Msg=" + this.errorMsg;
  }

}

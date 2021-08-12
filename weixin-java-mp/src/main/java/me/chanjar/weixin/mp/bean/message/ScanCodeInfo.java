package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@XStreamAlias("ScanCodeInfo")
@Data
public class ScanCodeInfo implements Serializable {
  private static final long serialVersionUID = 4745181270645050122L;

  /**
   * 扫描类型，一般是qrcode.
   */
  @XStreamAlias("ScanType")
  private String scanType;

  /**
   * 扫描结果，即二维码对应的字符串信息.
   */
  @XStreamAlias("ScanResult")
  private String scanResult;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

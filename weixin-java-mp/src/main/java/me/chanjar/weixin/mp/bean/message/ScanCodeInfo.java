package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

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

  @XStreamAlias("ScanType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scanType;
  @XStreamAlias("ScanResult")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scanResult;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  /**
   * 扫描类型，一般是qrcode
   */
  public String getScanType() {
    return this.scanType;
  }

  /**
   * 扫描结果，即二维码对应的字符串信息
   */
  public String getScanResult() {
    return this.scanResult;
  }


}

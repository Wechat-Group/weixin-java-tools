package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@XStreamAlias("SendLocationInfo")
public class SendLocationInfo {

  @XStreamAlias("Location_X")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String locationX;

  @XStreamAlias("Location_Y")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String locationY;

  @XStreamAlias("Scale")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String scale;

  @XStreamAlias("Label")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String label;

  @XStreamAlias("Poiname")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String poiname;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public String getLocationX() {
    return this.locationX;
  }

  public void setLocationX(String locationX) {
    this.locationX = locationX;
  }

  public String getLocationY() {
    return this.locationY;
  }

  public void setLocationY(String locationY) {
    this.locationY = locationY;
  }

  public String getScale() {
    return this.scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }

  public String getLabel() {
    return this.label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getPoiname() {
    return this.poiname;
  }

  public void setPoiname(String poiname) {
    this.poiname = poiname;
  }
}

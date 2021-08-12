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
@XStreamAlias("SendLocationInfo")
@Data
public class SendLocationInfo implements Serializable {
  private static final long serialVersionUID = 6633214140499161130L;

  @XStreamAlias("Location_X")
  private String locationX;

  @XStreamAlias("Location_Y")
  private String locationY;

  @XStreamAlias("Scale")
  private String scale;

  @XStreamAlias("Label")
  private String label;

  @XStreamAlias("Poiname")
  private String poiName;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}

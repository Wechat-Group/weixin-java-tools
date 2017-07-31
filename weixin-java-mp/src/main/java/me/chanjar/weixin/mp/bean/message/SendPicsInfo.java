package me.chanjar.weixin.mp.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@XStreamAlias("SendPicsInfo")
public class SendPicsInfo {
  @XStreamAlias("PicList")
  protected final List<Item> picList = new ArrayList<>();
  @XStreamAlias("Count")
  private Long count;

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public Long getCount() {
    return this.count;
  }

  public void setCount(Long count) {
    this.count = count;
  }

  public List<Item> getPicList() {
    return this.picList;
  }

  @XStreamAlias("item")
  public static class Item {
    @XStreamAlias("PicMd5Sum")
    @XStreamConverter(value = XStreamCDataConverter.class)
    private String picMd5Sum;

    @Override
    public String toString() {
      return ToStringUtils.toSimpleString(this);
    }

    public String getPicMd5Sum() {
      return this.picMd5Sum;
    }

    public void setPicMd5Sum(String picMd5Sum) {
      this.picMd5Sum = picMd5Sum;
    }
  }
}

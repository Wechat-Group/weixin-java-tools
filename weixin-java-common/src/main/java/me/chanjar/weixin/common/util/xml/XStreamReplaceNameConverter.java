package me.chanjar.weixin.common.util.xml;

public class XStreamReplaceNameConverter extends XStreamCDataConverter {
  @Override
  public String toString(Object obj) {
    return "<ReplaceName>" + super.toString(obj) + "</ReplaceName>";
  }
}

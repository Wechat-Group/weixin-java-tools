package me.chanjar.weixin.mp.bean.membercard;

/**
 * Created by YuJian on 2017/7/11.
 */
public class MemberCardUserInfo {

  private NameValues[] commonFieldList;

  private NameValues[] customFieldList;

  public NameValues[] getCommonFieldList() {
    return commonFieldList;
  }

  public void setCommonFieldList(NameValues[] commonFieldList) {
    this.commonFieldList = commonFieldList;
  }

  public NameValues[] getCustomFieldList() {
    return customFieldList;
  }

  public void setCustomFieldList(NameValues[] customFieldList) {
    this.customFieldList = customFieldList;
  }
}

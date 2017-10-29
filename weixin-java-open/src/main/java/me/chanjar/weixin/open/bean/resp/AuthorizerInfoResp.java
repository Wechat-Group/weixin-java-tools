package me.chanjar.weixin.open.bean.resp;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by JasonY on 17/10/24.
 */
public class AuthorizerInfoResp implements Serializable {
    private static final long serialVersionUID = 7162415629719019945L;

    private String nickName;
    private String headImg;

    // 授权方公众号类型，0代表订阅号，1代表由历史老帐号升级后的订阅号，2代表服务号
    private Integer serviceTypeInfoId;

    /**
     * 授权方认证类型
     * -1代表未认证
     * 0代表微信认证
     * 1代表新浪微博认证
     * 2代表腾讯微博认证
     * 3代表已资质认证通过但还未通过名称认证
     * 4代表已资质认证通过、还未通过名称认证，但通过了新浪微博认证
     * 5代表已资质认证通过、还未通过名称认证，但通过了腾讯微博认证
     */
    private Integer verifyTypeInfoId;

    private String userName;

    // 授权方公众号所设置的微信号，可能为空
    private String alias;

    // 账号介绍 小程序授权信息有该字段
    private String signature;

    private String principalName;

    // 是否开通微信门店功能（0代表未开通，1代表已开通）
    private Integer openStore;
    // 是否开通微信扫商品功能（0代表未开通，1代表已开通）
    private Integer openScan;
    // 是否开通微信支付功能（0代表未开通，1代表已开通）
    private Integer openPay;
    // 是否开通微信卡券功能（0代表未开通，1代表已开通）
    private Integer openCard;
    // 是否开通微信摇一摇功能（0代表未开通，1代表已开通）
    private Integer openShake;

    // 二维码图片的URL，开发者最好自行也进行保存
    private String qrcodeUrl;

    // 授权方appid
    private String appid;

    // 公众号授权给开发者的权限集列表，ID为1到15
    private List<Integer> funcInfoIds = new ArrayList<>();

    private MiniProgramInfo miniProgramInfo;

    public static AuthorizerInfoResp fromJson(String json) {
        return WxOpenGsonBuilder.create().fromJson(json, AuthorizerInfoResp.class);
    }

    public Boolean ifMiniProgram() {
        return miniProgramInfo != null;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getServiceTypeInfoId() {
        return serviceTypeInfoId;
    }

    public void setServiceTypeInfoId(Integer serviceTypeInfoId) {
        this.serviceTypeInfoId = serviceTypeInfoId;
    }

    public Integer getVerifyTypeInfoId() {
        return verifyTypeInfoId;
    }

    public void setVerifyTypeInfoId(Integer verifyTypeInfoId) {
        this.verifyTypeInfoId = verifyTypeInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public Integer getOpenStore() {
    return openStore;
  }

  public void setOpenStore(Integer openStore) {
    this.openStore = openStore;
  }

  public Integer getOpenScan() {
    return openScan;
  }

  public void setOpenScan(Integer openScan) {
    this.openScan = openScan;
  }

  public Integer getOpenPay() {
    return openPay;
  }

  public void setOpenPay(Integer openPay) {
    this.openPay = openPay;
  }

  public Integer getOpenCard() {
    return openCard;
  }

  public void setOpenCard(Integer openCard) {
    this.openCard = openCard;
  }

  public Integer getOpenShake() {
    return openShake;
  }

  public void setOpenShake(Integer openShake) {
    this.openShake = openShake;
  }

  public MiniProgramInfo getMiniProgramInfo() {
    return miniProgramInfo;
  }

  public void setMiniProgramInfo(MiniProgramInfo miniProgramInfo) {
    this.miniProgramInfo = miniProgramInfo;
  }

  public MiniProgramInfo buildMiniProgramInfo() {
      return new MiniProgramInfo();
  }

  public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public List<Integer> getFuncInfoIds() {
        return funcInfoIds;
    }

    public void setFuncInfoIds(List<Integer> funcInfoIds) {
        this.funcInfoIds = funcInfoIds;
    }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public class MiniProgramInfo implements Serializable {
        private static final long serialVersionUID = 9092463057642240347L;

        private List<String> requestDomains;
        private List<String> wsRequestDomains;
        private List<String> uploadDomains;
        private List<String> downloadDomains;
        private List<Map<String, String>> categories;
        private Integer visitStatus;

    public List<String> getRequestDomains() {
      return requestDomains;
    }

    public void setRequestDomains(List<String> requestDomains) {
      this.requestDomains = requestDomains;
    }

    public List<String> getWsRequestDomains() {
      return wsRequestDomains;
    }

    public void setWsRequestDomains(List<String> wsRequestDomains) {
      this.wsRequestDomains = wsRequestDomains;
    }

    public List<String> getUploadDomains() {
      return uploadDomains;
    }

    public void setUploadDomains(List<String> uploadDomains) {
      this.uploadDomains = uploadDomains;
    }

    public List<String> getDownloadDomains() {
      return downloadDomains;
    }

    public void setDownloadDomains(List<String> downloadDomains) {
      this.downloadDomains = downloadDomains;
    }

    public List<Map<String, String>> getCategories() {
            return categories;
        }

        public void setCategories(List<Map<String, String>> categories) {
            this.categories = categories;
        }

        public Integer getVisitStatus() {
            return visitStatus;
        }

        public void setVisitStatus(Integer visitStatus) {
            this.visitStatus = visitStatus;
        }
    }
}

package cn.binarywang.wx.miniapp.bean;

/**
 * <pre>
 * lineColor 包装类
 * 用于描述二维码（小程序码）颜色（RGB参数值），
 * 详情请查看文档 https://mp.weixin.qq.com/debug/wxadoc/dev/api/qrcode.html
 * </pre>
 * @author Element
 */
public class WxMaCodeLineColor {
  private String r = "0", g = "0", b = "0";

  public WxMaCodeLineColor(String r, String g, String b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  public String getR() {
    return r;
  }

  public void setR(String r) {
    this.r = r;
  }

  public String getG() {
    return g;
  }

  public void setG(String g) {
    this.g = g;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }
}

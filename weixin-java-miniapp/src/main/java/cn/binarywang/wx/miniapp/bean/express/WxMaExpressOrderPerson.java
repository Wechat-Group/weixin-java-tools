package cn.binarywang.wx.miniapp.bean.express;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * 发件人/收件人信息对象
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2019-11-26
 */
@Data
public class WxMaExpressOrderPerson implements Serializable {

  private static final long serialVersionUID = -7816060207882761506L;

  /**
   * 发件人/收件人姓名
   * <pre>
   * 是否必填： 是
   * 描述： 不超过64字节
   * </pre>
   */
  private String name;

  /**
   * 发件人/收件人座机号码
   * <pre>
   * 是否必填： 否
   * 描述： 若不填写则必须填写 mobile，不超过32字节
   * </pre>
   */
  private String tel;

  /**
   * 发件人/收件人手机号码
   * <pre>
   * 是否必填： 否
   * 描述： 若不填写则必须填写 tel，不超过32字节
   * </pre>
   */
  private String mobile;

  /**
   * 发件人/收件人公司名
   * <pre>
   * 是否必填： 否
   * 描述： 不超过64字节
   * </pre>
   */
  private String company;

  /**
   * 发件人/收件人邮编
   * <pre>
   * 是否必填： 否
   * 描述： 不超过10字节
   * </pre>
   */
  @SerializedName("post_code")
  private String postCode;

  /**
   * 发件人/收件人所在国家
   * <pre>
   * 是否必填： 否
   * 描述： 不超过64字节
   * </pre>
   */
  private String country;

  /**
   * 发件人/收件人省份
   * <pre>
   * 是否必填： 是
   * 描述： 比如："广东省"，不超过64字节
   * </pre>
   */
  private String province;

  /**
   * 发件人/收件人地区或市
   * <pre>
   * 是否必填： 是
   * 描述： 比如："广州市"，不超过64字节
   * </pre>
   */
  private String city;

  /**
   * 发件人/收件人区或县
   * <pre>
   * 是否必填： 是
   * 描述： 比如："天河区"，不超过64字节
   * </pre>
   */
  private String area;

  /**
   * 发件人/收件人详细地址
   * <pre>
   * 是否必填： 是
   * 描述： 比如："XX路XX号XX大厦XX"，不超过512字节
   * </pre>
   */
  private String address;

}

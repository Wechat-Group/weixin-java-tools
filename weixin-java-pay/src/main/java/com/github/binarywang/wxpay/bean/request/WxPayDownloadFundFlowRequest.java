package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.constant.WxPayConstants.AccountType;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * <pre>
 *   微信支付下载资金账单请求参数类
 * Created by cwivan on 2018-08-02.
 * </pre>
 *
 * @author <a href="https://github.com/cwivan">cwivan</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxPayDownloadFundFlowRequest extends BaseWxPayRequest {
  private static final String[] ACCOUNT_TYPES = new String[]{AccountType.BASIC, AccountType.OPERATION, AccountType.FEES};
  private static final String SIGN_TYPE_HMAC_SHA256 = "HMAC-SHA256";
  private static final String TAR_TYPE_GZIP = "GZIP";

  /**
   * <pre>
   * 对账单日期
   * bill_date
   * 是
   * String(8)
   * 20140603
   * 下载对账单的日期，格式：20140603
   * </pre>
   */
  @Required
  @XStreamAlias("bill_date")
  private String billDate;

  /**
   * <pre>
   * 资金账户类型
   * account_type
   * 是
   * Basic
   * String(8)
   * --Basic，基本账户
   * --Operation，运营账户
   * --Fees，手续费账户
   * </pre>
   */
  @Required
  @XStreamAlias("account_type")
  private String accountType;

  /**
   * <pre>
   * 压缩账单
   * tar_type
   * 否
   * String(8)
   * GZIP
   * 非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
   * </pre>
   */
  @XStreamAlias("tar_type")
  private String tarType;

  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isNotBlank(this.getTarType()) && !TAR_TYPE_GZIP.equals(this.getTarType())) {
      throw new WxPayException("tar_type值如果存在，只能为GZIP");
    }

    if (!ArrayUtils.contains(ACCOUNT_TYPES, this.getAccountType())) {
      throw new WxPayException(String.format("account_type必须为%s其中之一,实际值：%s",
        Arrays.toString(ACCOUNT_TYPES), this.getAccountType()));
    }
    /**
     * 目前仅支持HMAC-SHA256
     */
    this.setSignType(SIGN_TYPE_HMAC_SHA256);
  }
}

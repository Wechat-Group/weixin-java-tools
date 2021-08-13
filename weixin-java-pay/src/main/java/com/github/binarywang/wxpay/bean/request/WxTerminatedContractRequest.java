package com.github.binarywang.wxpay.bean.request;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import me.chanjar.weixin.common.annotation.Required;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author chenliang
 * @date 2021-08-02 5:24 下午
 *
 * <pre>
 *   微信api申请解约
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class WxTerminatedContractRequest extends BaseWxPayRequest {
  //方式1. 使用contract_id查
  /**
   * <pre>
   * 字段名：委托代扣协议ID.
   * 变量名：contract_id
   * 是否必填：二选一
   * 类型：String(32)
   * 示例值：1000005698
   * 签约成功后由微信返回
   * </pre>
   */
  @XStreamAlias("contract_id")
  private String contractId;

  /**
   * <pre>
   * 字段名：解约备注.
   * 变量名：contract_termination_remark
   * 是否必填：是
   * 类型：String(256)
   * 示例值：解约原因
   * 例如：签约信息有误，须重新签约
   * </pre>
   */
  @Required
  @XStreamAlias("contract_termination_remark")
  private String contractTerminationRemark;

  /**
   * <pre>
   * 字段名：接口版本号.
   * 变量名：version
   * 是否必填：是
   * 类型：String(8)
   * 示例值：1.0
   * 固定填写1.0，
   * </pre>
   */
  @Required
  @XStreamAlias("version")
  private String version;

  //方式2. 使用plan_id和contract_code查

  /**
   * <pre>
   * 字段名：模板ID.
   * 变量名：plan_id
   * 是否必填：二选一
   * 类型：int
   * 示例值：123
   * 代扣模板ID
   * </pre>
   */
  @XStreamAlias("plan_id")
  private Integer planId;

  /**
   * <pre>
   * 字段名：签约协议号.
   * 变量名：contract_code
   * 是否必填：二选一
   * 类型：String(32)
   * 示例值：12332343
   * 商户侧唯一
   * </pre>
   */
  @XStreamAlias("contract_code")
  private String contractCode;


  @Override
  protected void checkConstraints() throws WxPayException {
    if (StringUtils.isNotBlank(contractId) &&
      (Objects.nonNull(planId) || StringUtils.isNotBlank(contractCode))) {
      throw new WxPayException("contractId 和 planId&contractCode 不能同时存在或同时为空，必须二选一");
    }
  }

  @Override
  protected boolean needNonceStr() {
    return false;
  }


  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("contract_id", contractId);
    map.put("contract_termination_remark", contractTerminationRemark);
    map.put("version", version);
    if (Objects.nonNull(planId)) {
      map.put("plan_id", planId.toString());
    }
    map.put("contract_code", contractCode);
  }
}

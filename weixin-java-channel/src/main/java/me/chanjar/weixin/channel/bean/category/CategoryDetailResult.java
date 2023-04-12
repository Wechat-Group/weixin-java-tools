package me.chanjar.weixin.channel.bean.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class CategoryDetailResult extends WxChannelBaseResponse {

  private static final long serialVersionUID = 4657778764371047619L;

  @JsonProperty("info")
  private Info info;

  @JsonProperty("attr")
  private Attr attr;


  @Data
  @NoArgsConstructor
  public static class Info implements Serializable {

    /** 类目ID */
    @JsonProperty("cat_id")
    private String id;
    /** 类目名称 */
    @JsonProperty("name")
    private String name;
  }

  @Data
  @NoArgsConstructor
  public static class Attr implements Serializable {

    /** 是否支持虚拟发货 */
    @JsonProperty("shop_no_shipment")
    private Boolean shopNoShipment;

    /** 是否定向准入 */
    @JsonProperty("access_permit_required")
    private Boolean accessPermitRequired;

    /** 是否支持预售 */
    @JsonProperty("pre_sale")
    private Boolean preSale;

    /** 是否必须支持7天无理由退货 */
    @JsonProperty("seven_day_return")
    private Boolean sevenDayReturn;

    /** 定准类目的品牌ID */
    @JsonProperty("brand_list")
    private List<BrandInfo> brands;

    /** 类目关联的保证金，单位分 */
    @JsonProperty("deposit")
    private Long deposit;

    /** 产品属性 */
    @JsonProperty("product_attr_list")
    private List<ProductAttr> productAttrs;

    /** 销售属性 */
    @JsonProperty("sale_attr_list")
    private List<ProductAttr> saleAttrs;

    /** 佣金信息 */
    @JsonProperty("transactionfee_info")
    private FeeInfo feeInfo;
  }

  @Data
  @NoArgsConstructor
  public static class BrandInfo implements Serializable {

    /** 定准类目的品牌ID */
    @JsonProperty("brand_id")
    private String id;
  }

  @Data
  @NoArgsConstructor
  public static class ProductAttr implements Serializable {

    /** 类目必填项名称 */
    @JsonProperty("name")
    private String name;

    /** 类目必填项类型，string为自定义，select_one为多选一 */
    @JsonProperty("type")
    private String type;

    /** 类目必填项值 */
    @JsonProperty("value")
    private String value;

    /** 是否类目必填项 */
    @JsonProperty("is_required")
    private Boolean required;


  }

  @Data
  @NoArgsConstructor
  public static class FeeInfo implements Serializable {

    /** 类目实收的交易佣金比例，单位万分比 */
    @JsonProperty("basis_point")
    private Integer basisPoint;

    /** 类目原始佣金比例，单位万分比 */
    @JsonProperty("original_basis_point")
    private Integer originalBasisPoint;

    /** 佣金激励类型，0：无激励措施，1：新店佣金减免 */
    @JsonProperty("incentive_type")
    private Integer incentiveType;

  }
}





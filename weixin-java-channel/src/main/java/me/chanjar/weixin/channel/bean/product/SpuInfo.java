package me.chanjar.weixin.channel.bean.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.AttrInfo;

/**
 * Spu信息
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SpuInfo extends SpuSimpleInfo {

  private static final long serialVersionUID = -1183209029245287297L;

  /** 标题，字符类型，最少不低于3，最长不超过60。商品标题不得仅为数字、字母、字符或上述三种的组合 */
  @JsonProperty("title")
  private String title;

  /** 副标题，最多18字符 */
  @JsonProperty("sub_title")
  private String subTitle;

  /** 主图，多张，列表，图片类型，最多不超过9张 */
  @JsonProperty("head_imgs")
  private List<String> headImgs;

  /** 发货方式，若为无需快递（仅对部分类目开放），则无需填写运费模版id。0:快递发货；1:无需快递；默认0 */
  @JsonProperty("deliver_method")
  private Integer deliverMethod;

  /** 商品详情 */
  @JsonProperty("desc_info")
  private DescriptionInfo descInfo;

  /** 商品类目，大小恒等于3（一二三级类目） */
  @JsonProperty("cats")
  private List<SpuCategory> cats;

  /** 商品参数 */
  @JsonProperty("attrs")
  private List<AttrInfo> attrs;

  /** 商品编码 */
  @JsonProperty("spu_code")
  private String spuCode;

  /** 品牌id，无品牌为2100000000 */
  @JsonProperty("brand_id")
  private String brandId;

  /** 商品资质图片（最多5张） */
  @JsonProperty("qualifications")
  private List<String> qualifications;

  /** 运费信息 */
  @JsonProperty("express_info")
  private ExpressInfo expressInfo;

  /** 售后说明 */
  @JsonProperty("aftersale_desc")
  private String afterSaleDesc;

  /** 限购信息 */
  @JsonProperty("limited_info")
  @JsonInclude(Include.NON_EMPTY)
  private LimitInfo limitInfo;

  /** 附加服务 */
  @JsonProperty("extra_service")
  private ExtraServiceInfo extraService;

  /** 商品线上状态 {@link me.chanjar.weixin.channel.enums.SpuStatus } */
  @JsonProperty("status")
  private Integer status;

  /** 商品草稿状态 */
  @JsonProperty("edit_status")
  private Integer editStatus;

  /** 最低价格 */
  @JsonProperty("min_price")
  private Integer minPrice;

  /** 创建时间 yyyy-MM-dd HH:mm:ss */
  @JsonProperty("create_time")
  private String createTime;

  /**
   * 商品草稿最近一次修改时间
   */
  @JsonProperty("edit_time")
  private Long editTime;

  /**
   * 商品类型。1: 小店普通自营商品；2: 福袋抽奖商品；3: 直播间闪电购商品。
   * 注意: 福袋抽奖、直播间闪电购类型的商品为只读数据，不支持编辑、上架操作，不支持用data_type=2的参数获取。
   */
  @JsonProperty("product_type")
  private Integer productType;


  /**
   * 商品的售后信息
   */
  @JsonProperty("after_sale_info")
  private AfterSaleInfo afterSaleInfo;
}

package me.chanjar.weixin.channel.bean.league.supplier;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;

/**
 * 合作小店详情响应
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShopDetailResponse extends WxChannelBaseResponse {

  private static final long serialVersionUID = 188954608418544735L;
  /** 小店详情 */
  @JsonProperty("shop_detail")
  private ShopDetail shopDetail;

  /** 本次翻页的上下文，用于顺序翻页请求 */
  @JsonProperty("next_key")
  private String nextKey;

  /** 是否还有剩余小店 */
  @JsonProperty("has_more")
  private Boolean hasMore;

  /** 小店详情 */
  @Data
  @NoArgsConstructor
  public static class ShopDetail implements Serializable {

    private static final long serialVersionUID = -3454074422563804378L;
    /** 小店基础信息 */
    @JsonProperty("base_info")
    private BizBaseInfo baseInfo;

    /** 小店数据信息 */
    @JsonProperty("data_info")
    private ShopDataInfo dataInfo;

    /** 合作状态Status 1邀请中 2已接受邀请 3已拒绝邀请 4取消邀请 5取消合作 */
    @JsonProperty("status")
    private Integer status;

    /** 开始合作时间戳 */
    @JsonProperty("approved_time")
    private Long approvedTime;

  }


  /** 小店数据信息 */
  @Data
  @NoArgsConstructor
  public static class ShopDataInfo implements Serializable {

    private static final long serialVersionUID = 6603460255046252283L;
    /** 合作动销GMV，单位：分 */
    @JsonProperty("gmv")
    private Integer gmv;

    /** 历史合作商品数 */
    @JsonProperty("product_number")
    private Integer productNumber;

    /** 已结算服务费，单位：分 */
    @JsonProperty("settle_amount")
    private Integer settleAmount;

    /** 预计待结算服务费，单位：分 */
    @JsonProperty("unsettle_amount")
    private Integer unsettleAmount;

    /** 今日新增合作商品数 */
    @JsonProperty("product_number_today")
    private Integer productNumberToday;

    /** 今日动销商品数 */
    @JsonProperty("product_number_sold_today")
    private Integer productNumberSoldToday;
  }

}

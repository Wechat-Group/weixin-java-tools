package me.chanjar.weixin.channel.bean.league.promoter;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 达人
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
@Data
@NoArgsConstructor
public class PromoterInfo implements Serializable {

  private static final long serialVersionUID = -8851711325343107780L;
  /** 视频号finder_id */
  @JsonProperty("finder_id")
  private String finderId;

  /** 合作状态 0初始值 1邀请中 2达人已接受邀请 3达人已拒绝邀请 4已取消邀请 5已取消合作 10已删除 */
  @JsonProperty("status")
  private Integer status;

  /** 达人邀请秒级时间戳 */
  @JsonProperty("invite_time")
  private Long inviteTime;

  /** 累计合作商品数 */
  @JsonProperty("sale_product_number")
  private Integer saleProductNumber;

  /** 合作动销GMV */
  @JsonProperty("sale_gmv")
  private Integer saleGmv;
}

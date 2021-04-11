package me.chanjar.weixin.open.bean.minishop.limitdiscount;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 小商店商品秒杀活动
 */
@Data
@ApiModel("小商店商品秒杀")
public class LimitDiscountGoods implements Serializable {
  @ApiModelProperty("小商店秒杀任务ID")
  private Long taskId;

  @ApiModelProperty("秒杀任务状态")
  private Integer status;

  @ApiModelProperty("小商店商品ID，需要检查该商品在小商店的状态，如果不是上线状态可以提示客户需要先上架到小商店再进行处理")
  private Long productId;

  @ApiModelProperty("开始时间,发给小商店的时候需要转换为getTime")
  private Date startTime;

  @ApiModelProperty("结束时间，发给小商店的时候需要转换为getTime")
  private Date endTime;

  @ApiModelProperty("商品sku列表")
  private List<LimitDiscountSku> limitDiscountSkuList;


  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    if (taskId != null) {
      jsonObject.addProperty("task_id", taskId);
    }
    if (status != null) {
      jsonObject.addProperty("status", status);
    }

    jsonObject.addProperty("product_id", productId);
    jsonObject.addProperty("start_time", startTime.getTime());
    jsonObject.addProperty("end_time", endTime.getTime());

    JsonArray jsonArray = new JsonArray();
    for (LimitDiscountSku sku : limitDiscountSkuList) {
      jsonArray.add(sku.toJsonObject());
    }

    jsonObject.add("limited_discount_sku_list", jsonArray);

    return jsonObject;
  }
}

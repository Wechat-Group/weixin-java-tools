package me.chanjar.weixin.open.bean.minishop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("小商店运费模版信息")
public class MinishopDeliveryTemplateResult implements Serializable {
  @ApiModelProperty(value = "错误码")
  private Integer errCode;

  @ApiModelProperty(value = "错误信息")
  private String errMsg;

  @ApiModelProperty(value = "运费模版列表")
  private List<MinishopDeliveryTemplate> templateList;
}

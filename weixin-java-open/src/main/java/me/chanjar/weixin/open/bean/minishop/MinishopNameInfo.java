package me.chanjar.weixin.open.bean.minishop;

import com.google.gson.JsonObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("小商店名称信息")
public class MinishopNameInfo implements Serializable {

  private static final long serialVersionUID = 1L;


  /**
   * 1）小程序名称可以由中文、数字、英文、空格及部分特殊符号(“空格”、“-”、“+”、“&”、“.”)组成。长度在4-30个字符之间，一个中文字等于2个字符。
   * 2）公众号、小程序在微信公众平台上的名称是唯一的，且属于同一主体下，可以重名。
   * 3）不得与不同主体的公众号名称重名。
   */
  @ApiModelProperty(value = "1）小程序名称可以由中文、数字、英文、空格及部分特殊符号(“空格”、“-”、“+”、“&”、“.”)组成。长度在4-30个字符之间，一个中文字等于2个字符。\n" +
    "   * 2）公众号、小程序在微信公众平台上的名称是唯一的，且属于同一主体下，可以重名。\n" +
    "   * 3）不得与不同主体的公众号名称重名。", required = true)
  private String nickName;


  /**
   * 1）小程序简称可以从小程序名称中按顺序截取字符创建。长度在4-10个字符之间，一个中文字等于2个字符。
   * 2）小程序简称在微信公众平台是不唯一的，可以重名。但对于仿冒、侵权等恶意情况，平台仍会做出相关处罚。开发者也可通过侵权投诉维护自己的正当权益。
   * 3）小程序简称设置后，将在客户端任务栏向用户展示。开发者可以凭借此功能，更好地实现产品品牌价值和展示。目前暂不支持名称的其他功能。
   */
  @ApiModelProperty(value = "   * 1）小程序简称可以从小程序名称中按顺序截取字符创建。长度在4-10个字符之间，一个中文字等于2个字符。\n" +
    "   * 2）小程序简称在微信公众平台是不唯一的，可以重名。但对于仿冒、侵权等恶意情况，平台仍会做出相关处罚。开发者也可通过侵权投诉维护自己的正当权益。\n" +
    "   * 3）小程序简称设置后，将在客户端任务栏向用户展示。开发者可以凭借此功能，更好地实现产品品牌价值和展示。目前暂不支持名称的其他功能。", required = true)
  private String abbr;


  /**
   * 请确认介绍内容不含国家相关法律法规禁止内容,介绍字数为4-120个字符，一个中文占2个字符。一个月内可申请5次修改。请提供可支持命名的材料
   */
  @ApiModelProperty(value = "请确认介绍内容不含国家相关法律法规禁止内容,介绍字数为4-120个字符，一个中文占2个字符。一个月内可申请5次修改。请提供可支持命名的材料", required = true)
  private String introduction;

  /**
   * 补充材料，传media id数组，当返回210047时必填
   */
  @ApiModelProperty(value = "补充材料，传media id数组，当返回210047时必填", required = false)
  private List<String> namingOtherStuff;

  public JsonObject toJsonObject() {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("nickname", nickName);
    jsonObject.addProperty("abbr", abbr);
    jsonObject.addProperty("introduction", introduction);
    return jsonObject;
  }
}

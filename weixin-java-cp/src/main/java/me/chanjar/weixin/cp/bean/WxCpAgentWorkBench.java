package me.chanjar.weixin.cp.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchKeyData;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchList;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;
import java.util.List;

/**
 * @author songshiyu
 * @date : create in 16:09 2020/9/27
 * @description: 工作台自定义展示
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAgentWorkBench implements Serializable {
  private static final long serialVersionUid = 1L;

  /*
   * 展示类型，目前支持 “keydata”、 “image”、 “list” 、”webview”
   * */
  private String type;
  /*
   * 用户的userid
   * */
  private String userId;
  /*
   * 应用id
   * */
  private Long agentId;
  /*
   * 点击跳转url，若不填且应用设置了主页url，则跳转到主页url，否则跳到应用会话窗口
   * */
  private String jumpUrl;
  /*
   * 若应用为小程序类型，该字段填小程序pagepath，若未设置，跳到小程序主页
   * */
  private String pagePath;
  /*
   * 图片url:图片的最佳比例为3.35:1;webview:渲染展示的url
   * */
  private String url;
  /*
   * 是否覆盖用户工作台的数据。设置为true的时候，会覆盖企业所有用户当前设置的数据。若设置为false,则不会覆盖用户当前设置的所有数据
   * */
  private Boolean replaceUserData;

  private List<WorkBenchKeyData> keyDataList;

  private List<WorkBenchList> lists;

  // 生成模板Json字符串
  public String toTemplateString() {
    JsonObject templateObject = new JsonObject();
    templateObject.addProperty("agentid", this.agentId);
    templateObject.addProperty("type", this.type);
    if (this.replaceUserData != null) {
      templateObject.addProperty("replace_user_data", this.replaceUserData);
    }
    this.handle(templateObject);
    return templateObject.toString();
  }

  // 生成用户数据Json字符串
  public String toUserDataString() {
    JsonObject userDataObject = new JsonObject();
    userDataObject.addProperty("agentid", this.agentId);
    userDataObject.addProperty("userid", this.userId);
    userDataObject.addProperty("type", this.type);
    this.handle(userDataObject);
    return userDataObject.toString();
  }

  // 处理不用类型的工作台数据
  private void handle(JsonObject templateObject) {
    WxCpConsts.WorkBenchType workBenchType = WxCpConsts.WorkBenchType.getType(this.getType());
    switch (workBenchType) {
      case KEYDATA: {
        JsonArray keyDataArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchKeyData keyDataItem : this.keyDataList) {
          JsonObject keyDataObject = new JsonObject();
          keyDataObject.addProperty("key", keyDataItem.getKey());
          keyDataObject.addProperty("data", keyDataItem.getData());
          keyDataObject.addProperty("jump_url", keyDataItem.getJumpUrl());
          keyDataObject.addProperty("pagepath", keyDataItem.getPagePath());
          keyDataArray.add(keyDataObject);
        }
        itemsObject.add("items", keyDataArray);
        templateObject.add("keydata", itemsObject);
        break;
      }
      case IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("url", this.url);
        image.addProperty("jump_url", this.jumpUrl);
        image.addProperty("pagepath", this.pagePath);
        templateObject.add("image", image);
        break;
      }
      case LIST: {
        JsonArray listArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchList listItem : this.lists) {
          JsonObject listObject = new JsonObject();
          listObject.addProperty("title", listItem.getTitle());
          listObject.addProperty("jump_url", listItem.getJumpUrl());
          listObject.addProperty("pagepath", listItem.getPagePath());
          listArray.add(listObject);
        }
        itemsObject.add("items", listArray);
        templateObject.add("list", itemsObject);
        break;
      }
      case WEBVIEW: {
        JsonObject webview = new JsonObject();
        webview.addProperty("url", this.url);
        webview.addProperty("jump_url", this.jumpUrl);
        webview.addProperty("pagepath", this.pagePath);
        templateObject.add("webview", webview);
        break;
      }
      default: {
        //do nothing
      }
    }
  }

}

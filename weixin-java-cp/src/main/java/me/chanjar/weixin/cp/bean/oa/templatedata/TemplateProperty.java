package me.chanjar.weixin.cp.bean.oa.templatedata;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import me.chanjar.weixin.cp.bean.oa.WxCpTemplateResult;
import me.chanjar.weixin.cp.bean.oa.templatedata.control.TemplateContact;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author gyv12345@163.com
 */
public class TemplateProperty implements Serializable {

  private static final long serialVersionUID = -3429251158540167453L;

  private String control;

  private String id;

  private List<TemplateTitle> title;

  /**
   * 控件说明，向申请者展示的控件填写说明，若配置了多语言则会包含中英文的控件说明，默认为zh_CN中文
   */
  private List<TemplateTitle> placeholder;

  /**
   * 是否必填：1-必填；0-非必填
   */
  private Integer require;
  /**
   * 是否参与打印：1-不参与打印；0-参与打印
   */
  @SerializedName("un_print")
  private Integer unPrint;

  private TemplateConfig config;
}

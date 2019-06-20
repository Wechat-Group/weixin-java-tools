package me.chanjar.weixin.cp.bean;

import java.util.List;

import lombok.Data;

/**
 * 群聊
 *
 * @author gaigeshen
 */
@Data
public class WxCpChat {
  
  private String id;
  private String name;
  private String owner;
  private List<String> users;

}

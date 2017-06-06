package me.chanjar.weixin.mp.bean;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rememberber on 2017/6/5.
 *
 * @author rememberber
 */
public class WxMpShakeQuery {

  private String ticket;

  private int needPoi;

  public String toJsonString() {
    Map<String, Object> map = new HashMap<>();
    map.put("ticket", this.ticket);
    map.put("need_poi", this.needPoi);
    return new Gson().toJson(map);
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public int getNeedPoi() {
    return needPoi;
  }

  public void setNeedPoi(int needPoi) {
    this.needPoi = needPoi;
  }
}

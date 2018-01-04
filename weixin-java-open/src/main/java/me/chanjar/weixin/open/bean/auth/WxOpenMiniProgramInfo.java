package me.chanjar.weixin.open.bean.auth;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;

@Data
public class WxOpenMiniProgramInfo {
  private Map<String, List<String>> network;
  private List<Pair<String, String>> categories;
  private Integer visitStatus;
}

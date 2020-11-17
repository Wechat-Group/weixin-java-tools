package me.chanjar.weixin.cp.bean.taskcard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 *  任务卡片按钮
 *  Created by Jeff on 2019-05-16.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a>
 * @date 2019-05-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskCardButton {
  private String key;
  private String name;
  private String replaceName;
  private String color;
  private Boolean bold;
}

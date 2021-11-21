package me.chanjar.weixin.cp.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenderList {
  @SerializedName("user_list")
  private List<String> userList;
  @SerializedName("department_list")
  private List<String> departmentList;
}

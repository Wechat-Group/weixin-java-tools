package me.chanjar.weixin.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.Serializable;

/**
 * 通用文件上传参数
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUploadParam implements Serializable {

  /**
   * 文件对应的接口参数名称(非文件名)，如：media
   */
  @NotNull
  private String name;

  /**
   * 上传数据
   */
  @NotNull
  private CommonUploadData data;

  /**
   * 从文件构造
   *
   * @param name 参数名，如：media
   * @param file 文件
   * @return 文件上传参数对象
   */
  @SneakyThrows
  public static CommonUploadParam fromFile(String name, File file) {
    return new CommonUploadParam(name, CommonUploadData.fromFile(file));
  }

  /**
   * 从字节数组构造
   *
   * @param name  参数名，如：media
   * @param bytes 字节数组
   * @return 文件上传参数对象
   */
  @SneakyThrows
  public static CommonUploadParam fromBytes(String name, @Nullable String fileName, byte[] bytes) {
    return new CommonUploadParam(name, new CommonUploadData(fileName, new ByteArrayInputStream(bytes), bytes.length));
  }

  @Override
  public String toString() {
    return String.format("{name:%s, data:%s}", name, data);
  }
}

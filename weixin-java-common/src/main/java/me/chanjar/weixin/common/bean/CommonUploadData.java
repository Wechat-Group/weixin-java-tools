package me.chanjar.weixin.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.io.*;
import java.nio.file.Files;

/**
 * 通用文件上传数据
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonUploadData implements Serializable {

  /**
   * 文件名，如：1.jpg
   */
  @Nullable
  private String fileName;

  /**
   * 文件内容
   *
   * @see FileInputStream 文件输入流
   * @see ByteArrayInputStream 字节输入流
   */
  @NotNull
  private InputStream inputStream;

  /**
   * 文件内容长度(字节数)
   */
  private long length;

  /**
   * 从文件构造
   *
   * @param file 文件
   * @return 通用文件上传数据
   */
  @SneakyThrows
  public static CommonUploadData fromFile(File file) {
    return new CommonUploadData(file.getName(), Files.newInputStream(file.toPath()), file.length());
  }


  /**
   * 读取所有字节,此方法会关闭输入流
   *
   * @return 字节数组
   */
  @SneakyThrows
  public byte[] readAllBytes() {
    byte[] bytes = new byte[(int) length];
    //noinspection ResultOfMethodCallIgnored
    inputStream.read(bytes);
    inputStream.close();
    return bytes;
  }

  @Override
  public String toString() {
    return String.format("{fileName:%s, length:%s}", fileName, length);
  }
}

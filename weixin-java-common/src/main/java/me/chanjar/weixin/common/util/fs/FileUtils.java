package me.chanjar.weixin.common.util.fs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtils {

  /**
   * 创建临时文件.
   *
   * @param inputStream 输入文件流
   * @param name        文件名
   * @param ext         扩展名
   * @param tmpDirFile  临时文件夹目录
   */
  public static File createTmpFile(InputStream inputStream, String name, String ext, File tmpDirFile) throws IOException {
    File resultFile = File.createTempFile(name, '.' + ext, tmpDirFile);

    resultFile.deleteOnExit();
    org.apache.commons.io.FileUtils.copyToFile(inputStream, resultFile);
    return resultFile;
  }

  /**
   * 创建临时文件.
   *
   * @param inputStream 输入文件流
   * @param name        文件名
   * @param ext         扩展名
   */
  public static File createTmpFile(InputStream inputStream, String name, String ext) throws IOException {
    return createTmpFile(inputStream, name, ext, Files.createTempDirectory("weixin-java-tools-temp").toFile());
  }

}

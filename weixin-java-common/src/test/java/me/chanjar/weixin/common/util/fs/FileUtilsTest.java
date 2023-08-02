package me.chanjar.weixin.common.util.fs;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

  @Test
  public void testCreateTmpFile() throws IOException {
    String strings = "abc";
    File tmpFile = FileUtils.createTmpFile(new ByteArrayInputStream(strings.getBytes()), "name", "txt");
    System.out.println(tmpFile);
    List<String> lines = IOUtils.readLines(Files.newInputStream(tmpFile.toPath()), Charset.defaultCharset());
    assertThat(lines).hasSize(1);
    assertThat(lines.get(0)).isEqualTo(strings);
  }

  @Test
  public void testTestCreateTmpFile() {
  }

  @Test
  public void testImageToBase64ByStream() {
  }
}

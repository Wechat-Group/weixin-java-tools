package me.chanjar.weixin.common.util.http;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下载媒体文件请求执行器，请求的参数是String, 返回的结果是File
 * 视频文件不支持下载
 * @author Daniel Qian
 */
public class MediaDownloadRequestExecutor implements RequestExecutor<File, String> {

  private File tmpDirFile;

  public MediaDownloadRequestExecutor() {
  }

  public MediaDownloadRequestExecutor(File tmpDirFile) {
    this.tmpDirFile = tmpDirFile;
  }

  @Override
  public File execute(String uri, String queryParam) throws WxErrorException, IOException {
    if (queryParam != null) {
      if (uri.indexOf('?') == -1) {
        uri += '?';
      }
      uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
    }

    HttpRequest httpRequest = HttpRequest.post(uri);
    HttpResponse response = httpRequest.send();
    String contentType =response.header("Content-Type");
    if(contentType !=null && contentType.startsWith("application/json")){
      // application/json; encoding=utf-8 下载媒体文件出错
      throw new WxErrorException(WxError.fromJson(response.bodyText()));
    }

    String fileName = getFileName(response);
    if (StringUtils.isBlank(fileName)) {
        return null;
      }

    InputStream inputStream = new ByteArrayInputStream(response.bodyBytes());
    String[] nameAndExt = fileName.split("\\.");
      return FileUtils.createTmpFile(inputStream, nameAndExt[0], nameAndExt[1], this.tmpDirFile);


  }

  private String getFileName(HttpResponse response) throws WxErrorException {
    String content =response.header("Content-disposition");
    if(content == null || content.length() == 0){
      throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
    }

    Pattern p = Pattern.compile(".*filename=\"(.*)\"");
    Matcher m = p.matcher(content);
    if(m.matches()){
      return m.group(1);
    }
    throw new WxErrorException(WxError.newBuilder().setErrorMsg("无法获取到文件名").build());
  }

}

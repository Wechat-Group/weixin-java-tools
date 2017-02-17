package me.chanjar.weixin.mp.bean.material;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class WxMpMaterial {

  private String name;
  private File file;
  private String videoTitle;
  private String videoIntroduction;

  public WxMpMaterial() {
  }

  public WxMpMaterial(String name, File file, String videoTitle, String videoIntroduction) {
    this.name = name;
    this.file = file;
    this.videoTitle = videoTitle;
    this.videoIntroduction = videoIntroduction;
  }

  public Map<String, String> getForm() {
    Map<String, String> form = new HashMap<>();
    form.put("title", this.videoTitle);
    form.put("introduction", this.videoIntroduction);
    return form;
  }

  public String getVideoTitle() {
    return this.videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }

  public String getVideoIntroduction() {
    return this.videoIntroduction;
  }

  public void setVideoIntroduction(String videoIntroduction) {
    this.videoIntroduction = videoIntroduction;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public File getFile() {
    return this.file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public String toString() {
    return "WxMpMaterial [" + "name=" + this.name + ", file=" + this.file + ", videoTitle=" + this.videoTitle + ", videoIntroduction=" + this.videoIntroduction + "]";
  }
}

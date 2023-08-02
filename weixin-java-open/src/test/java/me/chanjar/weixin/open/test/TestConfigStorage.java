package me.chanjar.weixin.open.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;

@Getter
@Setter
@XStreamAlias("xml")
public class TestConfigStorage extends WxOpenInMemoryConfigStorage {

  private String testMpAppId;

  private String testMaAppId;
}

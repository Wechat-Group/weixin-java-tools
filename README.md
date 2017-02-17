Weixin Java Tools —— 微信公众号&企业号开发 Java SDK
=====================================
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.binarywang/weixin-java-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.binarywang/weixin-java-parent)
[![Build Status](https://travis-ci.org/Wechat-Group/weixin-java-tools.svg?branch=develop)](https://travis-ci.org/Wechat-Group/weixin-java-tools)

### 注意事项：
1. 声明： ***本项目Fork自chanjarster/weixin-java-tools，但由于原项目已停止维护，故单独维护和发布，且发布到maven上的groupId也会不同，详细信息见下文。***
1. 最新更新：**2017-2-12 发布[【2.5.0正式版】](https://github.com/Wechat-Group/weixin-java-tools/releases)**！
1. 开源中国网站的本项目首页链接地址：https://www.oschina.net/p/weixin-java-tools-new
1. 自2.0.0版本以来，公众号的接口调整比较大，主要是为了解决主接口类过于庞大不方便管理的问题，将接口实现代码按模块进行拆分。
1. SDK详细开发文档请查阅 [【Wiki】](https://github.com/wechat-group/weixin-java-tools/wiki)，部分文档可能未能及时更新，如有发现，可以及时上报或者自行修改。
1. 各个模块的Javadoc可以在线查看（有可能是最新的测试版本的，请注意观察版本号）：[weixin-java-mp](https://binarywang.github.io/weixin-java-mp-javadoc/)、[weixin-java-common](https://binarywang.github.io/weixin-java-common-javadoc/)、[weixin-java-cp](https://binarywang.github.io/weixin-java-cp-javadoc/)
1. 本SDK要求的最低JDK版本是7，还在使用JDK6的用户请参考[【此项目】]( https://github.com/binarywang/weixin-java-tools-for-jdk6) ，而其他更早的JDK版本则需要自己改造实现。
1. 如有新功能需求，发现BUG，或者由于微信官方接口调整导致的代码问题，可以直接在[【Issues】](https://github.com/Wechat-Group/weixin-java-tools/issues)页提出issue，便于讨论追踪问题；
1. 如果想贡献代码，请阅读[【代码贡献指南】](CONTRIBUTION.md)。

===========

## SDK使用交流方式说明：
1. QQ群： [![加入QQ群](https://img.shields.io/badge/QQ群-343954419-blue.svg)](http://shang.qq.com/wpa/qunwpa?idkey=731dc3e7ea31ebe25376cc1a791445468612c63fd0e9e05399b088ec81fd9e15) 或 [![加入QQ群](https://img.shields.io/badge/QQ群-343954419-blue.svg)](http://jq.qq.com/?_wv=1027&k=40lRskK)，推荐点击按钮入群，当然如果无法成功操作，请自行搜索群号343954419进行添加 ）
1. 由于群容量有限，即将爆满，故开启付费入群模式以保证只有真实交流需求的人进入，并为保证群的活跃度，将不定期清理长时间不活跃的同学；
1. 微信群： 因微信群已达到100人限制，故如有想加入微信群的，请入QQ群后联系管理员，提供微信号以便邀请加入；
1. 新手提问前，请先阅读此文章：http://t.cn/RV93MRB
1. 寻求帮助时需贴代码或大长串异常信息的，请利用http://paste.ubuntu.com

===========

## 版本说明
1. 本项目定为大约每两个月发布一次正式版，版本号格式为X.X.0（如2.1.0，2.2.0等），遇到重大问题需修复会及时提交新版本，欢迎大家随时提交Pull Request；
1. BUG修复和新特性一般会先发布成小版本作为临时测试版本（如2.4.5.BETA，2.4.6.BETA等，即尾号不为0，并添加BETA字样，以区别于正式版）；
1. 目前最新版本号为 [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.binarywang/weixin-java-parent/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.binarywang/weixin-java-parent) ，也可以通过访问链接 [【公众号】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-mp%22) 、[【企业号】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-cp%22)
分别查看所有最新的版本。 

===========
#### 本项目在几个著名的代码托管网站同步更新，地址分别是:
* 码云：http://git.oschina.net/binary/weixin-java-tools
* GitHub: https://github.com/wechat-group/weixin-java-tools
* Bitbucket：https://bitbucket.org/binarywang/weixin-java-tools
* Coding: https://git.coding.net/binarywang/weixin-java-tools.git

===========
## 可参考的Demo项目
#### 目前都是公众号的，风格不同，欢迎提供更多的demo供新手参考:
* https://github.com/wechat-group/weixin-java-mp-demo 
* https://github.com/wechat-group/weixin-java-mp-multi-demo (支持多公众号)
* https://github.com/wechat-group/weixin-java-tools-springmvc
* https://github.com/wechat-group/weixin-java-mp-demo-springboot

## Maven & Gradle 最新正式版本

* 公众号（订阅号、服务号）：

maven：
```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>2.5.0</version>
</dependency>
```
gradle：
```groovy
compile 'com.github.binarywang:weixin-java-mp:2.5.0'
```

* 企业号：

maven：
```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-cp</artifactId>
  <version>2.5.0</version>
</dependency>
```
gradle：
```groovy
compile 'com.github.binarywang:weixin-java-cp:2.5.0'
```


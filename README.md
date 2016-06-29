# Weixin Java Tools 微信公众号/企业号开发Java SDK
## [![Build Status](https://travis-ci.org/binarywang/weixin-java-tools.svg?branch=develop)](https://travis-ci.org/binarywang/weixin-java-tools) ![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/weixin-java-parent.svg) <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=078f7a153d243853e24cf2b542e7a6ccbf2a592bc138080f84d11297f736ec46"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="weixin-java-tools" title="weixin-java-tools"></a>

### 声明：本项目基于chanjarster/weixin-java-tools，由于原作者长期没有维护，故单独维护和发布，且发布到maven上的groupId也会不同。
#### 最新更新：1.3.4版发布！！！ on 2016-06-01

### 详细开发文档请看 [wiki](https://github.com/chanjarster/weixin-java-tools/wiki)。

===========
## 使用开发交流工具：
* QQ群：343954419 <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=078f7a153d243853e24cf2b542e7a6ccbf2a592bc138080f84d11297f736ec46"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="weixin-java-tools" title="weixin-java-tools"></a>
* 微信群： 因二维码有时间限制，如有想加入微信群的，请入QQ群后咨询获取最新入群二维码。
 
===========

## 发布周期
暂定为每月发布一次，月初或月底发布新版本，遇到重大问题需修复会及时提交新版本，欢迎大家随时提交Pull Request。

## Quick Start

如果要开发公众号（订阅号、服务号）应用，在你的maven项目中添加：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-mp</artifactId>
  <version>1.3.4</version>
</dependency>
```

如果要开发企业号应用，在你的maven项目中添加：

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>weixin-java-cp</artifactId>
  <version>1.3.4</version>
</dependency>
```

## SNAPSHOT版

本项目的BUG修复和新特性一般会先发布在*-SNAPSHOT版里供大家预览，如果要使用*-SNAPSHOT版，则需要在你的pom.xml中添加这段：

```xml
<repositories>
  <repository>
      <snapshots />
      <id>sonatype snapshots</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots/</url>
  </repository>
</repositories>
```

## 关于Pull Request

非常欢迎和感谢对本项目发起Pull Request的同学，不过本项目基于[git flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)开发流程，因此在发起Pull Request的时候请选择develop分支。

且本项目代码风格是用2个空格代表一个tab，因此在发起PR时注意一下，否则很容易发生在IDE格式化代码后与原代码产生大量diff，这样我在阅读PR的时候就很困难。

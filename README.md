## WxJava - 微信开发 Java SDK（开发工具包） [![LICENSE](https://img.shields.io/badge/License-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE) [![Badge](https://img.shields.io/badge/Link-996.icu-red.svg)](https://996.icu/#/zh_CN)

[![码云Gitee](https://gitee.com/binary/weixin-java-tools/badge/star.svg?theme=blue)](https://gitee.com/binary/weixin-java-tools)
[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=Wechat-Group&repo=WxJava&style=flat&background=1081C1)](https://github.com/Wechat-Group/WxJava)
[![GitHub release](https://img.shields.io/github/release/Wechat-Group/WxJava.svg)](https://github.com/Wechat-Group/WxJava/releases)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/wx-java.svg)](http://mvnrepository.com/artifact/com.github.binarywang/wx-java)
[![Build Status](https://travis-ci.org/Wechat-Group/WxJava.svg?branch=develop)](https://travis-ci.org/Wechat-Group/WxJava)
[![使用IntelliJ IDEA开发维护](https://img.shields.io/badge/IntelliJ%20IDEA-提供支持-blue.svg)](https://www.jetbrains.com/?from=WxJava-weixin-java-tools)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

#### 支持包括微信支付、开放平台、公众号、企业微信/企业号、小程序等微信功能的后端开发。

<table border="0">
	<tbody>
		<tr>
			<td align="left" valign="middle">
        <a href="http://mp.weixin.qq.com/mp/homepage?__biz=MzI3MzAwMzk4OA==&hid=1&sn=f31af3bf562b116b061c9ab4edf70b61&scene=18#wechat_redirect" target="_blank">
				  <img height="120" src="https://gitee.com/binary/weixin-java-tools/raw/master/qrcodes/mp.jpg">
        </a>
			</td>
			<td align="center" valign="middle">
				<a href="https://cloud.tencent.com/redirect.php?redirect=1014&cps_key=a4c06ffe004dbcda44036daa1bf8f876&from=console" target="_blank">
					<img height="120" src="https://gitee.com/binary/weixin-java-tools/raw/master/banners/tcloud.jpg">
				</a>
			</td>
			<td align="right" valign="middle">
				<a href="https://coding.net/?utm_source=WxJava" target="_blank">
					<img height="120" src="https://gitee.com/binary/weixin-java-tools/raw/master/banners/coding.jpg">
				</a>
			</td>
			<td align="center" valign="middle">
				<a href="https://promotion.aliyun.com/ntms/act/qwbk.html?userCode=7makzf5h" target="_blank">
					<img height="120" src="https://gitee.com/binary/weixin-java-tools/raw/master/banners/aliyun.jpg">
				</a>
			</td>
		</tr>
	</tbody>
</table>

### 重要信息
1. **2018-12-23 项目更名为WxJava，并发布 [【3.3.0正式版】](https://github.com/Wechat-Group/WxJava/releases)**！
1. 新手重要提示：本项目仅是一个SDK开发工具包，未提供Web实现，建议使用maven或gradle引用本项目即可使用本SDK提供的各种功能，详情可参考 **[【Demo项目】](demo.md)** 或本项目中的部分单元测试代码；另外微信开发新手请务必阅读[【开发文档 Wiki 首页】](https://github.com/Wechat-Group/WxJava/wiki)的常见问题部分，可以少走很多弯路，节省不少时间。
1. QQ群/微信群/企业微信/钉钉企业群等，请扫描上面的二维码关注微信公众号【WxJava】后，点击相关菜单获取相关信息加入，也可以在微信中搜索 `weixin-java-tools`或 `WxJava` 关注公众号，公众号会及时通知SDK相关更新信息，并不定期分享微信Java开发相关技术知识；
1. 钉钉企业群：[请点击链接申请加入](https://h5.dingtalk.com/inviteColleague/index.html#/invite/9ed100cc4a/E1DF918E32E398D191E7FE61FE0552A6) 或者 [用手机钉钉APP扫码](https://gitee.com/binary/weixin-java-tools/raw/master/qrcodes/.jpg) 申请加入。
1. 新手提问前，请先阅读[【提问的智慧】](http://www.binarywang.com/article/smart-questions)，并确保已查阅过 [【开发文档Wiki】](https://github.com/wechat-group/WxJava/wiki)；
1. 寻求帮助时需贴代码或大长串异常信息的，请利用http://paste.ubuntu.com 

--------------------------------
### 其他说明
1. **阅读源码的同学请注意，本SDK为简化代码编译时加入了lombok支持，如果不了解lombok的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；**
1. 如有新功能需求，发现BUG，或者由于微信官方接口调整导致的代码问题，可以直接在[【Issues】](https://github.com/Wechat-Group/WxJava/issues)页提出issue，便于讨论追踪问题；
1. 如果想贡献代码，请阅读[【代码贡献指南】](contribution.md)；
1. 本SDK要求的最低JDK版本是1.7，还在使用JDK6的用户请参考[【此项目】]( https://github.com/binarywang/weixin-java-tools-for-jdk6) ，而其他更早的JDK版本则需要自己改造实现。
1. [开源中国本项目的首页](https://www.oschina.net/p/weixin-java-tools-new)，欢迎大家积极留言评分 🙂
1. SDK详细开发文档请查阅 [【开发文档Wiki】](https://github.com/wechat-group/WxJava/wiki)，部分文档可能未能及时更新，如有发现，可以及时上报或者自行修改。
1. **如果本SDK对您有所帮助，欢迎对我们的努力进行肯定，可以扫描[【支付宝付款码】](qrcodes/alipay.jpg)或者[【微信支付二维码】](qrcodes/wepay.jpg)进行打赏，或者直接前往[【托管于码云的项目首页】](http://gitee.com/binary/weixin-java-tools)，在评论区上方可以找到“捐助”按钮。非常感谢各位打赏和捐助的同学！**
1. 各个模块的Javadoc可以在线查看：[weixin-java-miniapp](http://binary.ac.cn/weixin-java-miniapp-javadoc/)、[weixin-java-pay](http://binary.ac.cn/weixin-java-pay-javadoc/)、[weixin-java-mp](http://binary.ac.cn/weixin-java-mp-javadoc/)、[weixin-java-common](http://binary.ac.cn/weixin-java-common-javadoc/)、[weixin-java-cp](http://binary.ac.cn/weixin-java-cp-javadoc/)、[weixin-java-open](http://binary.ac.cn/weixin-java-open-javadoc/)
1. 本SDK项目在以下代码托管网站同步更新:
* 码云：https://gitee.com/binary/weixin-java-tools
* GitHub：https://github.com/wechat-group/WxJava

---------------------------------
### SDK使用方式
注意：最新版本（包括测试版）为 [![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/wx-java.svg)](http://mvnrepository.com/artifact/com.github.binarywang/wx-java)，以下为最新正式版。

```xml
<dependency>
  <groupId>com.github.binarywang</groupId>
  <artifactId>（不同模块参考下文）</artifactId>
  <version>3.3.0</version>
</dependency>
```
* 各模块的`artifactId`：
  - 微信小程序：`weixin-java-miniapp`   
  - 微信支付：`weixin-java-pay`
  - 微信开放平台：`weixin-java-open`   
  - 公众号（包括订阅号和服务号）：`weixin-java-mp`    
  - 企业号/企业微信：`weixin-java-cp`

---------------------------------
### 版本说明
1. 本项目定为大约每两个月发布一次正式版，版本号格式为X.X.0（如2.1.0，2.2.0等），遇到重大问题需修复会及时提交新版本，欢迎大家随时提交Pull Request；
1. BUG修复和新特性一般会先发布成小版本作为临时测试版本（如3.1.8.B，即尾号不为0，并添加B，以区别于正式版）；
1. 目前最新版本号为 [![Maven Central](https://img.shields.io/maven-central/v/com.github.binarywang/wx-java.svg)](http://mvnrepository.com/artifact/com.github.binarywang/wx-java) ，也可以通过访问链接 [【微信支付】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-pay%22) 、[【微信小程序】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-miniapp%22) 、[【公众号】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-mp%22) 、[【企业微信】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-cp%22)、[【开放平台】](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22com.github.binarywang%22%20AND%20a%3A%22weixin-java-open%22)
分别查看所有最新的版本。 

----------------------------------
### 使用案例
1. 开源项目：https://github.com/workcheng/weiya
1. 开源项目：https://github.com/jmdhappy/xxpay-master 
1. 开源工具：https://github.com/rememberber/WePush
1. 开源项目（微信点餐系统）：http://www.sqmax.top/springboot-project/
1. 小程序：（京东）友家铺子，友家铺子店长版，京粉精选
1. 小程序：[喵星人贴吧助手(扫码关注)](http://p98ahz3tg.bkt.clouddn.com/miniappqrcode.jpg)
1. 小程序：树懒揽书+
1. 小程序：广廉快线，鹏城巴士等
1. 小程序：当燃挑战、sportlight轻灵运动
1. 小程序：360考试宝典
1. 公众号：中国电信上海网厅（sh_189）
1. 公众号：E答平台
1. 公众号：宁夏生鲜365
1. 公众号：通服货滴
1. 公众号：神龙养车
1. 公众号：沃音乐商务智能
1. 公众号：[全民约跑健身便利店](http://www.oneminsport.com/)
1. 公众号：[洽洽食品](https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFM8TwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAycDRPOXBZbVZib2UxMDAwME0wN2gAAgRIu4RbAwQAAAAA)、[洽洽合伙人](https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFP8jwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyOUpJaU5VcXBlWTAxMDAwME0wN1oAAgSau4RbAwQAAAAA)
1. 公众号和小程序：民医台（可自行搜索）
1. 高善人力资源
1. 平台：[小猪餐餐](http://www.xzcancan.com/)
1. 平台：[餐饮系统](http://canyin.daydao.com)
1. 锐捷网络：Saleslink
1. 洽洽企业号
1. HTC企业微信
1. 其他更多案例请[【访问这里】](https://github.com/Wechat-Group/weixin-java-tools/issues/729)，持续更新中。

----------------------------------
### 贡献者列表
特别感谢参与贡献的所有同学！所有贡献者列表请在[此处](https://github.com/Wechat-Group/WxJava/graphs/contributors)查看。
以下仅列出贡献次数最多的几位，欢迎大家踊跃贡献代码！
1. [chanjarster (Daniel Qian)](http://github.com/chanjarster)
1. [binarywang (Binary Wang)](http://github.com/binarywang)
1. [mgcnrx11](http://github.com/mgcnrx11)
1. [007gzs](http://github.com/007gzs)
1. [aimilin6688 (Jonk)](http://github.com/aimilin6688)
1. [kakotor](http://github.com/kakotor)
1. [kareanyi (MillerLin)](http://github.com/kareanyi)
1. [tianmu](http://github.com/tianmu)
1. [rememberber (周波)](http://github.com/rememberber)
1. [charmingoh (Charming)](http://github.com/charmingoh)

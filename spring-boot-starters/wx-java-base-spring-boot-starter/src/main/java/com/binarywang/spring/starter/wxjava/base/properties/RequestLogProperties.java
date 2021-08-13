/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.binarywang.spring.starter.wxjava.base.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 日志配置
 *
 * @author L.cm
 */
@Getter
@Setter
@ConfigurationProperties(RequestLogLevel.REQ_LOG_PROPS_PREFIX)
public class RequestLogProperties {

  /**
   * 是否开启日志
   */
  private boolean enabled = false;

  /**
   * 日志级别配置，默认：BASIC
   */
  private RequestLogLevel level = RequestLogLevel.BASIC;
}

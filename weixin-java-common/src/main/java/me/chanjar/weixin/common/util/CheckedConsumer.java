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

package me.chanjar.weixin.common.util;

import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 受检的 Consumer
 *
 * @author L.cm
 */
@FunctionalInterface
public interface CheckedConsumer<T> extends Serializable {

  /**
   * Run the Consumer
   *
   * @param t T
   * @throws Throwable UncheckedException
   */
  @Nullable
  void accept(@Nullable T t) throws Throwable;

}

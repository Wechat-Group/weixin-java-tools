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

package com.binarywang.spring.starter.wxjava.base.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 类工具类
 *
 * @author L.cm
 */
@Slf4j
@UtilityClass
public class ClassUtil extends org.springframework.util.ClassUtils {
  private final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
  private final MetadataReaderFactory METADATA_READER_FACTORY = new CachingMetadataReaderFactory();
  private final ParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

  /**
   * 获取方法参数信息
   *
   * @param constructor    构造器
   * @param parameterIndex 参数序号
   * @return {MethodParameter}
   */
  public MethodParameter getMethodParameter(Constructor<?> constructor, int parameterIndex) {
    MethodParameter methodParameter = new SynthesizingMethodParameter(constructor, parameterIndex);
    methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
    return methodParameter;
  }

  /**
   * 获取方法参数信息
   *
   * @param method         方法
   * @param parameterIndex 参数序号
   * @return {MethodParameter}
   */
  public MethodParameter getMethodParameter(Method method, int parameterIndex) {
    MethodParameter methodParameter = new SynthesizingMethodParameter(method, parameterIndex);
    methodParameter.initParameterNameDiscovery(PARAMETER_NAME_DISCOVERER);
    return methodParameter;
  }

  /**
   * 获取Annotation
   *
   * @param method         Method
   * @param annotationType 注解类
   * @param <A>            泛型标记
   * @return {Annotation}
   */
  @Nullable
  public <A extends Annotation> A getAnnotation(Method method, Class<A> annotationType) {
    Class<?> targetClass = method.getDeclaringClass();
    // The method may be on an interface, but we need attributes from the target class.
    // If the target class is null, the method will be unchanged.
    Method specificMethod = ClassUtil.getMostSpecificMethod(method, targetClass);
    // If we are dealing with method with generic parameters, find the original method.
    specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
    // 先找方法，再找方法上的类
    A annotation = AnnotatedElementUtils.findMergedAnnotation(specificMethod, annotationType);
    if (null != annotation) {
      return annotation;
    }
    // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
    return AnnotatedElementUtils.findMergedAnnotation(specificMethod.getDeclaringClass(), annotationType);
  }

  /**
   * 获取Annotation
   *
   * @param handlerMethod  HandlerMethod
   * @param annotationType 注解类
   * @param <A>            泛型标记
   * @return {Annotation}
   */
  @Nullable
  public <A extends Annotation> A getAnnotation(HandlerMethod handlerMethod, Class<A> annotationType) {
    // 先找方法，再找方法上的类
    A annotation = handlerMethod.getMethodAnnotation(annotationType);
    if (null != annotation) {
      return annotation;
    }
    // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
    Class<?> beanType = handlerMethod.getBeanType();
    return AnnotatedElementUtils.findMergedAnnotation(beanType, annotationType);
  }

  /**
   * 判断是否有注解 Annotation
   *
   * @param method         Method
   * @param annotationType 注解类
   * @param <A>            泛型标记
   * @return {boolean}
   */
  public <A extends Annotation> boolean isAnnotated(Method method, Class<A> annotationType) {
    // 先找方法，再找方法上的类
    boolean isMethodAnnotated = AnnotatedElementUtils.isAnnotated(method, annotationType);
    if (isMethodAnnotated) {
      return true;
    }
    // 获取类上面的Annotation，可能包含组合注解，故采用spring的工具类
    Class<?> targetClass = method.getDeclaringClass();
    return AnnotatedElementUtils.isAnnotated(targetClass, annotationType);
  }

  public Class<?> deduceMainApplicationClass() {
    try {
      StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
      for (StackTraceElement stackTraceElement : stackTrace) {
        if ("main".equals(stackTraceElement.getMethodName())) {
          return Class.forName(stackTraceElement.getClassName());
        }
      }
    } catch (ClassNotFoundException ex) {
      // Swallow and continue
    }
    return null;
  }

}

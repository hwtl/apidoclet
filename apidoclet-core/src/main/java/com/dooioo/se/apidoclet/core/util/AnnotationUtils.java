package com.dooioo.se.apidoclet.core.util;

import java.util.HashMap;
import java.util.Map;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationDesc.ElementValuePair;

/**
 * @author huisman
 * @version 1.0.0
 * @since 2016年1月13日 Copyright (c) 2016, BookDao All Rights Reserved.
 */
public final class AnnotationUtils {
  private AnnotationUtils() {
    super();
    throw new UnsupportedOperationException();
  }

  /**
   * 从方法的标注中查找指定名称的标注，并返回所有可用属性值，包括默认值。<br>
   *  使用HashMap存放key/value，key为标注属性名，value为AnnotationValue#getValue，key重复就覆盖<br>
   * 请注意，标注里的值（AnnotationValue#getValue()):
   *  <ul>
   *    <li> a wrapper class for a primitive type 属性为基本类型</li>
   *    <li>String   属性为字符串</li>
   *    <li>Type (representing a class literal) 属性为类型 Class</li>
   *    <li> FieldDoc (representing an enum constant) 属性为枚举常量 </li>
   *    <li>AnnotationDesc   属性为标注</li>
   *    <li> AnnotationValue[] 属性值为数组值，任何类型的数组，比如：String[]</li>
   *  </ul>
   * @author huisman
   * @param annotationDescs 待筛选的所有标注
   * @param searchedAnnotation 完全限定名称，不能是简单类名
   * @return never null ,empty if not found
   * @since 2016年1月13日
   */
  public static Map<String, Object> attributesFor(AnnotationDesc[] annotationDescs,
      String searchedAnnotation) {
    Map<String, Object> attributes = new HashMap<>();
    if (annotationDescs != null && annotationDescs.length > 0) {
      // search
      for (AnnotationDesc annotationDesc : annotationDescs) {
        if (annotationDesc.annotationType().qualifiedTypeName().equals(searchedAnnotation)) {
          // finally ,found
          ElementValuePair[] pairs = annotationDesc.elementValues();
          if (pairs != null && pairs.length > 0) {
            for (ElementValuePair ev : pairs) {
              attributes.put(ev.element().name(), ev.value().value());
            }
          }
          return attributes;
        }
      }
    }
    return attributes;
  }
  
  /**
    * 搜索单个标注所有属性 <br>
    * 使用HashMap存放key/value，key为标注属性名，value为AnnotationValue#getValue，key重复就覆盖<br>
   *  请注意，标注里的值（AnnotationValue#getValue()):
    *  <ul>
   *    <li> a wrapper class for a primitive type 属性为基本类型</li>
   *    <li>String   属性为字符串</li>
   *    <li>Type (representing a class literal) 属性为类型 Class</li>
   *    <li> FieldDoc (representing an enum constant) 属性为枚举常量 </li>
   *    <li>AnnotationDesc   属性为标注</li>
   *    <li> AnnotationValue[] 属性值为数组值，任何类型的数组，比如：String[]</li>
   *  </ul>
    * @author huisman
   */
  public static Map<String, Object> attributesFor(AnnotationDesc annotationDesc) {
    Map<String, Object> attributes = new HashMap<>();
    if (annotationDesc != null) {
          ElementValuePair[] pairs = annotationDesc.elementValues();
          if (pairs != null && pairs.length > 0) {
            for (ElementValuePair ev : pairs) {
              attributes.put(ev.element().name(), ev.value().value());
            }
          }
          return attributes;
      }
    return attributes;
  }

  /**
    *  检查特定类型的标注是否存在。
    * @author huisman
    * @param annotationDescs
    * @param searchedAnnotation
   */
  public static boolean isPresent(AnnotationDesc[] annotationDescs, String searchedAnnotation) {
    return searchFirst(annotationDescs, searchedAnnotation) !=null;
  }

  /**
    * 搜索最优先出现的标注，不存在则返回null
    * @author huisman
    * @param annotationDescs 待筛选的所有标注
    * @param searchedAnnotation 标注类全称
    * @since 2016年1月13日
   */
  public static AnnotationDesc searchFirst(AnnotationDesc[] annotationDescs, String searchedAnnotation) {
    if (annotationDescs == null || annotationDescs.length == 0) {
      return null;
    }

    for (AnnotationDesc annotationDesc : annotationDescs) {
      if (annotationDesc.annotationType().qualifiedTypeName().equals(searchedAnnotation)) {
        return annotationDesc;
      }
    }
    return null;
  }
}
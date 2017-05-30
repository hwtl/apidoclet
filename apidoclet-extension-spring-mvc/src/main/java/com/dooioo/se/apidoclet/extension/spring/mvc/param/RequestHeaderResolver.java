package com.dooioo.se.apidoclet.extension.spring.mvc.param;

import java.util.Map;

import com.dooioo.se.apidoclet.core.ApiDocletOptions;
import com.dooioo.se.apidoclet.core.spi.http.HeaderParamResolver;
import com.dooioo.se.apidoclet.core.spi.http.TypeInfoResolver;
import com.dooioo.se.apidoclet.core.util.AnnotationUtils;
import com.dooioo.se.apidoclet.core.util.StringUtils;
import com.dooioo.se.apidoclet.model.HeaderParam;
import com.dooioo.se.apidoclet.model.JavaAnnotations.AnnotationValue;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.Parameter;

/**
 * 解析http header
 */
public class RequestHeaderResolver implements
    HeaderParamResolver {

  @Override
  public boolean support(Parameter parameter, ApiDocletOptions options) {
    return AnnotationUtils.searchFirst(parameter.annotations(),
        org.springframework.web.bind.annotation.RequestHeader.class.getName()) != null;
  }

  @Override
  public HeaderParam resolve(TypeInfoResolver typeResolver,
      Parameter parameter, String paramComment, ApiDocletOptions options) {
    AnnotationDesc requestHeader =
        AnnotationUtils.searchFirst(parameter.annotations(),
            org.springframework.web.bind.annotation.RequestHeader.class
                .getName());

    Map<String, AnnotationValue> attributes =
        AnnotationUtils.attributesFor(requestHeader);

    // 属性类型为基本类型包装类型以及字符串
    AnnotationValue valAttr = attributes.get("value");
    String value = (valAttr == null ? null : (String) valAttr.getValue());

    AnnotationValue requiredAttr = attributes.get("required");
    Boolean required =
        (requiredAttr == null ? null : (Boolean) requiredAttr.getValue());

    AnnotationValue defaultAttr = attributes.get("defaultValue");
    String defaultValue =
        (defaultAttr == null ? null : (String) defaultAttr.getValue());

    HeaderParam headerParam =
        new HeaderParam(StringUtils.isNullOrEmpty(value) ? parameter.name()
            : value, required == null ? true : required,
            defaultValue == null ? null : defaultValue);
    headerParam.setComment(paramComment);
    headerParam.setType(typeResolver.resolve(parameter.type()));
    return headerParam;
  }

}
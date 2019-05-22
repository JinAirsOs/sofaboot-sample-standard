package com.alipay.sofa.common.util.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Inherited;
import javax.ws.rs.NameBinding;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * jwt登陆验证annotation
 * @author kerry
 * @date 2019-5-21 15:34:29
 */
@NameBinding
@Inherited
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface JWTAuth {
}
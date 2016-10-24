package com.linghu.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by linghu on 2016/10/21.
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface Onclick {
    int[] value();
}

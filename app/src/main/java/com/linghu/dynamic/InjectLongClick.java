/*
 *
 * Copyright (C) 2016 linghu
 *
 */
package com.linghu.dynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by linghu on 2015/5/21.
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLongClick {
	int[] value();
}
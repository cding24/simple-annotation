/*
 *
 * Copyright (C) 2016 linghu
 *
 */
package com.linghu.dynamic;

import android.app.Activity;
import android.view.View;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * created by linghu 2015/5/21
 */
public class ProcessInject {

	public static void injectView(Object obj, Object root) {
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Annotation[] annotations = field.getAnnotations();
			if (annotations != null) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof InjectView) {
						InjectView injectView = (InjectView) annotation;
						int resId = injectView.value();
						if (resId != -1) {
							try {
								View view = getViewByRoot(root, resId);
								field.set(obj, view);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							}
						}
						break;
					}
				}
			}
		}
	}

	public static void injectClick(Object obj, Object root) {
		Method[] methods = obj.getClass().getDeclaredMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			if (annotations != null) {
				for (Annotation annotation : annotations) {
					if (annotation instanceof InjectClick) {
						InjectClick inClick = (InjectClick) annotation;
						int[] value = inClick.value();
						if (value != null && value.length > 0) {
							View.OnClickListener listener = (View.OnClickListener) obj;
							try {
								for (int resId : value) {
									View view = getViewByRoot(root, resId);
									if (view == null) {
										throw new NullPointerException();
									}
									view.setOnClickListener(listener);
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							}
						}
					} else if (annotation instanceof InjectLongClick) {
						InjectClick injectLClick = (InjectClick) annotation;
						int[] value = injectLClick.value();
						if (value != null && value.length > 0) {
							View.OnLongClickListener listener = (View.OnLongClickListener) obj;
							try {
								for (int resId : value) {
									View view = getViewByRoot(root, resId);
									if (view == null) {
										throw new NullPointerException();
									}
									view.setOnLongClickListener(listener);
								}
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	public static View getViewByRoot(Object root, int resId) {
		View view = null;
		if (root instanceof Activity) {
			view = ((Activity)root).findViewById(resId);
		} else if (root instanceof View) {
			view = ((View)root).findViewById(resId);
		} else if (root instanceof ViewFinder) {
			view = ((ViewFinder)root).findViewById(resId);
		}
		return view;
	}


}
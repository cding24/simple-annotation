package com.linghu.annotation;

/**
 * Created by linghu on 16/4/22.
 */
public interface ViewInject<T> {
    void inject(T t, Object source);
}

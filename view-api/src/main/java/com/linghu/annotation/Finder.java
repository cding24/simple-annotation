package com.linghu.annotation;

import android.view.View;

import android.app.Activity;

/**
 * created by linghu on 2016/10
 */
public enum Finder {
    VIEW {
        @Override
        public View findView(Object source, int id) {
            return ((View) source).findViewById(id);
        }
    },
    ACTIVITY {
        @Override
        public View findView(Object source, int id) {
            return ((Activity) source).findViewById(id);
        }
    };

    public abstract View findView(Object source, int id);
}
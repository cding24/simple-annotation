/*
 *
 * Copyright (C) 2016 linghu
 *
 */
package com.linghu.dynamic;

import android.view.View;

/**
 * Created by linghu on 2015/5/21.
 * View的finder,包括Activity、ViewGroup等具有findViewById的类
 *
 */
public interface ViewFinder {
    View findViewById(int res);

}

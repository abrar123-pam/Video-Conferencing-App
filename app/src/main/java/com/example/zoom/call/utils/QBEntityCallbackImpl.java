package com.example.zoom.call.utils;

import android.os.Bundle;

import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;


public class QBEntityCallbackImpl<T> implements QBEntityCallback<T> {

    @Override
    public void onSuccess(T result, Bundle params) {

    }

    @Override
    public void onError(QBResponseException responseException) {

    }
}
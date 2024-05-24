// OcrResultCallback.java
package com.example.k_bob;

public interface OcrResultCallback {
    void onSuccess(String result);
    void onError(String error);
}
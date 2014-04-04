/**
 * 
 */
package com.zhuanmibao;


public class ZJni {

    public native static byte[] crypto(byte[] in,boolean encrypt);

    static {
        System.loadLibrary("zmb");
    }
}

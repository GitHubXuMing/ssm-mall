package com.ssm.mall.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MD5UtilTest {

    @Test
    public void MD5EncodeUtf8() {
        String originPassword = "tiger";
        System.err.println(MD5Util.MD5EncodeUtf8(originPassword));
    }
}
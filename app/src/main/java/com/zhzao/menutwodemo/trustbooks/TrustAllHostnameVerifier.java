package com.zhzao.menutwodemo.trustbooks;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * Created by 张乔君 on 2017/11/28.
 */

public class TrustAllHostnameVerifier  implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}

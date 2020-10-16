package com.nc.trial.uc.util;

import com.nc.trial.uc.domain.ShortLinkAware;

public class UrlUtils {
    public static void enrichWithShortLink(ShortLinkAware item, String baseUrl, int serverPort) {
        item.setShortLink(String.format("%s:%s/%s", baseUrl, serverPort, item.getPseudoHash()));
    }

    public static String generateFullBaseUrl(String baseUrl, int serverPort) {
        return String.format("%s:%s", baseUrl, serverPort);
    }
}

package com.github.eeriefoods.snsclient.shared;

import java.net.URI;

public final class Constants {

    private Constants() {}

    /**
     * Api-Url of the backend server
     */
    public static final String API_URL = "http://localhost:8080/api/v1";

    /**
     * Get a {@link URI} based off of {@code API_URL}
     * @param suffix the suffix to append to the {@code API_URL}
     * @return {@code API_URL}/{@code suffix}
     */
    // TODO Move to better suiting file
    public static URI getServerUri(String suffix) {
        return URI.create("%s/%s".formatted(API_URL, suffix));
    }

}

package dev.mccue.supertokens;

import dev.mccue.json.Json;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public final class Utils {
    private Utils() {}

    public static HttpResponse.BodyHandler<Json> jsonBodyHandler() {
        return requestInfo -> HttpResponse.BodySubscribers.mapping(
                HttpResponse.BodySubscribers.ofString(StandardCharsets.UTF_8),
                Json::readString
        );
    }
}

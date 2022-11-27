package dev.mccue.supertokens;

import dev.mccue.json.Json;
import dev.mccue.json.decode.alpha.Decoder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public final class ExampleApi {
    public record ExampleResponse(
            String description,
            double rating,
            String category,
            List<String> images
    ) {
        public static ExampleResponse fromJson(Json json) {
            return new ExampleResponse(
                    Decoder.field(json, "description", Decoder::string),
                    Decoder.field(json, "rating", Decoder::double_),
                    Decoder.field(json, "category", Decoder::string),
                    Decoder.field(json, "images", images -> Decoder.array(images, Decoder::string))
            );
        }
    }

    public static ExampleResponse makeExampleRequest() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(URI.create("https://dummyjson.com/products/1"))
                .GET()
                .build();
        try {
            var response = client.send(request, Utils.jsonBodyHandler());
            return ExampleResponse.fromJson(response.body());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

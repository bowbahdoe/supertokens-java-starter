package dev.mccue.supertokens;


import dev.mccue.json.Json;
import dev.mccue.rosie.IntoResponse;
import dev.mccue.rosie.Request;
import dev.mccue.rosie.Response;

import java.util.function.Function;
import static java.lang.System.Logger.Level.*;

public final class SupertokensHandler implements Function<Request, IntoResponse> {
    private static final System.Logger LOGGER = System.getLogger(SupertokensHandler.class.getName());

    @Override
    public IntoResponse apply(Request request) {
        LOGGER.log(INFO, "Handling request");

        var exampleApiResponse = ExampleApi.makeExampleRequest();
        LOGGER.log(INFO, "Made external call");

        return new Response(new JsonBody(
                Json.objectBuilder()
                        .put("hello", "world")
                        .put("exampleRating", exampleApiResponse.rating())
                        .put("exampleDescription", exampleApiResponse.description())
                        .put("exampleImages", exampleApiResponse.images().stream()
                                .map(Json::of)
                                .toList()
                        )
                        .build()
        ));
    }
}

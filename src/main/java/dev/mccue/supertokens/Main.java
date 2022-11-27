package dev.mccue.supertokens;

import dev.mccue.rosie.microhttp.MicrohttpAdapter;
import org.microhttp.Options;

import java.util.concurrent.Executors;

public final class Main {
    public static void main(String[] args) {
        var options = new Options()
                .withHost("0.0.0.0")
                .withPort(
                        System.getenv("PORT") == null
                                ? 4111
                                : Integer.parseInt(System.getenv("PORT"))
                );

        MicrohttpAdapter.runServer(
                new SupertokensHandler(),
                options,
                Executors.newFixedThreadPool(10)
        );
    }
}

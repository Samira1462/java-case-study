package com.solvians.showcase;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Two inputs required (threads, updates). Received:" + args.length);
        }

            int threads = Integer.parseInt(args[0]);
            int quotes = Integer.parseInt(args[1]);

            if (threads <= 0 || quotes <= 0) {
                throw new IllegalArgumentException("Threads and updates must be positive values");
            }

            CertificateUpdateGenerator generator = new CertificateUpdateGenerator(threads, quotes);
            generator.generateCSVLines().forEach(System.out::println);
            throw new RuntimeException("Expect at least number of threads and number of quotes. But got: " + args);
    }
}
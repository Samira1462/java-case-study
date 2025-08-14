package com.solvians.showcase;

import com.solvians.showcase.isin.ISINCheckDigitCalculator;
import com.solvians.showcase.isin.ISINCreator;
import com.solvians.showcase.isin.ISINBodyGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;
    private final ISINCreator isinCreator;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
        this.isinCreator = new ISINCreator(new ISINCheckDigitCalculator(), new ISINBodyGenerator());
    }

    public Stream<CertificateUpdate> generateQuotes() {
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<CertificateUpdate>> futures = new ArrayList<>();
        int quotesPerThread = quotes / threads;
        int remainingQuotes = quotes % threads;

        try {
            for (int i = 0; i < threads; i++) {
                int quotesForThisThread = quotesPerThread + (i < remainingQuotes ? 1 : 0);
                for (int j = 0; j < quotesForThisThread; j++) {
                    futures.add(executor.submit(this::generateOne));
                }
            }

            List<CertificateUpdate> updates = new ArrayList<>();
            for (Future<CertificateUpdate> future : futures) {
                updates.add(future.get());
            }

            return updates.stream();
        } catch (Exception e) {
            throw new RuntimeException("Exception in update", e);
        } finally {
            executor.shutdown();
        }
    }

    private CertificateUpdate generateOne() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long timestamp = System.currentTimeMillis();
        String isin = isinCreator.generate();
        double bidPrice = random.nextDouble(100.00, 200.01);
        int bidSize = random.nextInt(1000, 5001);
        double askPrice = random.nextDouble(100.00, 200.01);
        int askSize = random.nextInt(1000, 10001);

        return new CertificateUpdate(timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }

    public Stream<String> generateCSVLines() {
        return generateQuotes().map(CertificateUpdate::call);
    }
}
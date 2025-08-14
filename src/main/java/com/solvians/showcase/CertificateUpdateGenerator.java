package com.solvians.showcase;

import com.solvians.showcase.isin.ISINCheckDigitCalculator;
import com.solvians.showcase.isin.ISINCreator;
import com.solvians.showcase.isin.ISINBodyGenerator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        return IntStream.range(0, quotes)
                .parallel()
                .mapToObj(i -> generateOne());
    }


    private CertificateUpdate generateOne() {
        var random = ThreadLocalRandom.current();
        var timestamp = System.currentTimeMillis();
        var isin = isinCreator.generate();
        var bidPrice = random.nextDouble(100.00, 200.01);
        var bidSize = random.nextInt(1000, 5001);
        var askPrice = random.nextDouble(100.00, 200.01);
        var askSize = random.nextInt(1000, 10001);

        return new CertificateUpdate(timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }

    public List<String> generateCSVLines() {
        return generateQuotes()
                .map(update -> String.join(",",
                        String.valueOf(update.getTimestamp()),
                        update.getIsin(),
                        String.valueOf(update.getBidPrice()),
                        String.valueOf(update.getBidSize()),
                        String.valueOf(update.getAskPrice()),
                        String.valueOf(update.getAskSize())
                ))
                .collect(Collectors.toList());
    }
}

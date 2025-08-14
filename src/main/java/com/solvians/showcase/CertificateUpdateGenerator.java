package com.solvians.showcase;

import com.solvians.showcase.isin.ISINCheckDigitCalculator;
import com.solvians.showcase.isin.ISINCreator;
import com.solvians.showcase.isin.ISINBodyGenerator;

import java.util.ArrayList;
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
        // Use IntStream to control number of generated updates
        // Parallelism is controlled by stream().parallel()
        return IntStream.range(0, quotes)
                .parallel()
                .mapToObj(i -> generateOne());
    }

/*    public Stream<CertificateUpdate> generateQuotes() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // TODO: Implement me.
        List<CertificateUpdate> updateList = new ArrayList<CertificateUpdate>();
        for (int i = 0; i < threads * quotes; i++) {
            updateList.add(new CertificateUpdate());
        }
        return Stream.generate(CertificateUpdate::new).parallel().limit(quotes);
    }*/

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
/*    public Stream<CertificateUpdate> generateQuotes() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // TODO: Implement me.
        List<CertificateUpdate> updateList = new ArrayList<CertificateUpdate>();
        for (int i = 0; i < threads * quotes; i++) {
            updateList.add(new CertificateUpdate());
        }
        return Stream.generate(CertificateUpdate::new).parallel().limit(quotes);
    }*/
}

package com.solvians.showcase;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;

public class CertificateUpdate implements Callable<String> {
    private final long timestamp;
    private final String isin;
    private final double bidPrice;
    private final int bidSize;
    private final double askPrice;
    private final int askSize;

    public CertificateUpdate(long timestamp, String isin, double bidPrice, int bidSize, double askPrice, int askSize) {
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    @Override
    public String call() {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("%d,%s,%s,%d,%s,%d",
                timestamp, isin, df.format(bidPrice), bidSize, df.format(askPrice), askSize);
    }

   /* public long getTimestamp() { return timestamp; }
    public String getIsin() { return isin; }
    public double getBidPrice() { return bidPrice; }
    public int getBidSize() { return bidSize; }
    public double getAskPrice() { return askPrice; }
    public int getAskSize() { return askSize; }*/
}
package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CertificateUpdateGeneratorTest {

    @Test
    public void generateQuotes() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(2, 10);
        Stream<CertificateUpdate> quotes = generator.generateQuotes();
        assertNotNull(quotes);
        assertEquals(10, quotes.count());
    }

    @Test
    public void generateCSVLines() {
        CertificateUpdateGenerator generator = new CertificateUpdateGenerator(2, 3);
        List<String> lines = generator.generateCSVLines().toList();
        assertEquals(3, lines.size());
        for (String line : lines) {
            String[] parts = line.split(",");
            assertEquals(6, parts.length);
            assertTrue(parts[1].matches("[A-Z]{2}[A-Z0-9]{9}[0-9]"));
            assertTrue(Double.parseDouble(parts[2]) >= 100.00 && Double.parseDouble(parts[2]) <= 200.00);
            assertTrue(Integer.parseInt(parts[3]) >= 1000 && Integer.parseInt(parts[3]) <= 5000);
            assertTrue(Double.parseDouble(parts[4]) >= 100.00 && Double.parseDouble(parts[4]) <= 200.00);
            assertTrue(Integer.parseInt(parts[5]) >= 1000 && Integer.parseInt(parts[5]) <= 10000);
        }
    }
}
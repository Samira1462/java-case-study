package com.solvians.showcase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    @Test
    public void expectTwoIntArgs() {

        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> {
            App.main(new String[]{"xxx"});
        });
        assertTrue(ex1.getMessage().contains("Two inputs required"));

        RuntimeException ex2 = assertThrows(RuntimeException.class, () -> {
            App.main(new String[]{"10", "zzz"});
        });

        assertTrue(ex2.getMessage().contains("Invalid format for threads or quotes"));

        assertNotNull(ex2.getCause());
        assertInstanceOf(NumberFormatException.class, ex2.getCause());

        assertEquals("For input string: \"zzz\"", ex2.getCause().getMessage());
    }

}

package dev.conca.visitcounter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AcceptanceTest {
    @Test
    void testSolution() {

        String[] accessPerSite = {
                "900,google.com",
                "60,mail.yahoo.com",
                "10,mobile.sports.yahoo.com",
                "40,sports.yahoo.com",
                "300,yahoo.com",
                "10,stackoverflow.com",
                "20,overflow.com",
                "2,en.wikipedia.org",
                "1,m.wikipedia.org",
                "1,mobile.sports",
                "1,google.co.uk"
        };

        String[] expectedOutput = {
                "1340 com",
                "900 google.com",
                "410 yahoo.com",
                "60 mail.yahoo.com",
                "50 sports.yahoo.com",
                "20 overflow.com",
                "10 stackoverflow.com",
                "10 mobile.sports.yahoo.com",
                "3 org",
                "3 wikipedia.org",
                "2 en.wikipedia.org",
                "1 sports",
                "1 mobile.sports",
                "1 uk",
                "1 co.uk",
                "1 google.co.uk",
                "1 m.wikipedia.org"
        };

        List<String> countPerDomain = new DomainCounterCsv(accessPerSite).getSortedCount();

        assertThat(countPerDomain).containsExactly(expectedOutput);
    }
}
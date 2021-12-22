package dev.conca.visitcounter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DomainCounterTest {

    private DomainCounter domainCounter;

    @BeforeEach
    void setUp() {
        domainCounter = new DomainCounter();
    }

    @Test
    void testUnvisitedDomainCountIsZero() {
        assertThat(domainCounter.getCount("m.wikipedia.org")).isEqualTo(0);
    }

    @Test
    void testCanAddSiteCount() {
        domainCounter.increment("m.wikipedia.org", 1);
        assertThat(domainCounter.getCount("m.wikipedia.org")).isEqualTo(1);

        domainCounter.increment("m.wikipedia.org", 5);
        assertThat(domainCounter.getCount("m.wikipedia.org")).isEqualTo(6);
    }

    @Test
    void testSiteCountMustPropagateToSuperDomains() {
        domainCounter.increment("m.wikipedia.org", 1);

        assertThat(domainCounter.getCount("m.wikipedia.org")).isEqualTo(1);
        assertThat(domainCounter.getCount("wikipedia.org")).isEqualTo(1);
        assertThat(domainCounter.getCount("org")).isEqualTo(1);
    }

    @Test
    void testLeftPartsDoNotCountAsSubdomains() {
        domainCounter.increment("m.wikipedia.org", 1);

        assertThat(domainCounter.getCount("m.wikipedia")).isEqualTo(0);
    }
}

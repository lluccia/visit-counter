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
    void testNewCounterShouldBeEmpty() {
        assertThat(domainCounter.getCountPerDomain())
                .isEmpty();;
    }

    @Test
    void testDomainCountCanBeIncremented() {
        domainCounter.increment("m.wikipedia.org", 1);

        assertThat(domainCounter.getCountPerDomain())
                .containsEntry("m.wikipedia.org", 1L);

        domainCounter.increment("m.wikipedia.org", 5);
        assertThat(domainCounter.getCountPerDomain())
                .containsEntry("m.wikipedia.org", 6L);;
    }

    @Test
    void testDomainCountShouldPropagateToParentDomains() {
        domainCounter.increment("m.wikipedia.org", 1);

        assertThat(domainCounter.getCountPerDomain())
                .containsEntry("m.wikipedia.org", 1L)
                .containsEntry("wikipedia.org", 1L)
                .containsEntry("org", 1L);
    }

    @Test
    void testSubdomainsMissingParentLevelsAreNotCounted() {
        domainCounter.increment("m.wikipedia.org", 1);

        assertThat(domainCounter.getCountPerDomain())
                .doesNotContainKey("m.wikipedia")
                .doesNotContainKey("m");
    }
}

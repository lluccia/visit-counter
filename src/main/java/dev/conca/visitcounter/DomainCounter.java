package dev.conca.visitcounter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DomainCounter {
    private Map<String, Long> countPerDomain = new HashMap<>();

    public void increment(String domain, long count) {
        countPerDomain.merge(domain, count, Long::sum);

        if(domainHasParents(domain))
            increment(parentDomain(domain), count);
    }

    public Map<String, Long> getCountPerDomain() {
        return Collections.unmodifiableMap(countPerDomain);
    }

    private boolean domainHasParents(String domain) {
        return domain.contains(".");
    }

    private String parentDomain(String domain) {
        return domain.substring(domain.indexOf(".") + 1);
    }
}

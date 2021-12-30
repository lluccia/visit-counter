package dev.conca.visitcounter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DomainCounter {

    private Map<String, Long> countPerDomain = new HashMap<>();
    private Long maxCount = 0L;

    public void increment(String domain, long count) {
        Long incrementedCount = countPerDomain.merge(domain, count, Long::sum);
        maxCount = Math.max(maxCount, incrementedCount);

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

    public Long getMaxCount() {
        return maxCount;
    }
}

package dev.conca.visitcounter;

import java.util.HashMap;
import java.util.Map;

public class DomainCounter {
    private Map<String, Long> countPerDomain = new HashMap<>();

    public void increment(String domain, long count) {
        countPerDomain.merge(domain, count, Long::sum);

        if(domain.contains("."))
            increment(domain.substring(domain.indexOf(".")+1), count);
    }

    public Long getCount(String domain) {
        return countPerDomain.getOrDefault(domain, 0L);
    }
}

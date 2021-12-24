package dev.conca.visitcounter;

import java.util.List;
import java.util.stream.Collectors;

public class DomainCounterCsv {

    private final DomainCounter domainCounter;

    public DomainCounterCsv(String[] accessCountPerSites) {
        domainCounter = new DomainCounter();

        for (String accessCountPerSite : accessCountPerSites) {
            String[] split = accessCountPerSite.split(",");
            Long accessCount = Long.valueOf(split[0]);
            String site = split[1];
            domainCounter.increment(site, accessCount);
        }
    }

    public List<String> getSortedCount() {
        return domainCounter.getCountPerDomain().entrySet().stream()
                .sorted((d1, d2) -> Long.compare(d2.getValue(), d1.getValue()))
                .map(d -> String.format("%d %s", d.getValue(), d.getKey()))
                .collect(Collectors.toList());
    }

}

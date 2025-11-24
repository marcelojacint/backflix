package com.uniesp.backflix.demo.cacheConfig;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LimpezaCacheScheduler {
    private final CacheManager cacheManager;

    @Scheduled(cron = "0 0 4 * * *")
    public void limparCaches() {
        cacheManager.getCache("filmes").clear();
        cacheManager.getCache("series").clear();
        cacheManager.getCache("usuarios").clear();
    }
}

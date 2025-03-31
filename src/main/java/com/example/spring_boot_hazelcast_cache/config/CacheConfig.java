package com.example.spring_boot_hazelcast_cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizePolicy;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@Configuration
public class CacheConfig {

    @Bean
    public Config hazelcastConfig() {
        Config config = new Config();

        config.setInstanceName("hazelcast-instance")
              .addMapConfig(new MapConfig()
                    .setName("productCache")
                    .setTimeToLiveSeconds(600)
                    .setEvictionConfig(new EvictionConfig()
                        .setSize(1000)
                        .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE)
                        .setEvictionPolicy(EvictionPolicy.LRU)));

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance(hazelcastConfig());
    }

    @Bean
    public CacheManager cacheManager() {
        return new HazelcastCacheManager(hazelcastInstance());
    }
}

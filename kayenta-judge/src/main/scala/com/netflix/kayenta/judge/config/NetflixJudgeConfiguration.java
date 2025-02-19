/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.kayenta.judge.config;

import com.netflix.kayenta.metrics.MapBackedMetricsServiceRepository;
import com.netflix.kayenta.metrics.MetricSetMixerService;
import com.netflix.kayenta.metrics.MetricsServiceRepository;
import com.netflix.kayenta.security.AccountCredentialsRepository;
import com.netflix.kayenta.security.MapBackedAccountCredentialsRepository;
import com.netflix.kayenta.storage.MapBackedStorageServiceRepository;
import com.netflix.kayenta.storage.StorageServiceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
  "com.netflix.kayenta.judge"
})
public class NetflixJudgeConfiguration {

  @Bean
  @ConfigurationProperties("kayenta.netflix.judge")
  NetflixJudgeConfigurationProperties netflixJudgeConfigurationProperties() {
    return new NetflixJudgeConfigurationProperties();
  }

  @Bean
  @ConditionalOnMissingBean(AccountCredentialsRepository.class)
  AccountCredentialsRepository accountCredentialsRepository() {
        return new MapBackedAccountCredentialsRepository();
    }

  @Bean
  @ConditionalOnMissingBean(MetricsServiceRepository.class)
  MetricsServiceRepository metricsServiceRepository() {
        return new MapBackedMetricsServiceRepository();
    }

  @Bean
  @ConditionalOnMissingBean
  MetricSetMixerService metricSetMixerService() {
        return new MetricSetMixerService();
    }

  @Bean
  @ConditionalOnMissingBean(StorageServiceRepository.class)
  StorageServiceRepository storageServiceRepository() {
        return new MapBackedStorageServiceRepository();
    }
}

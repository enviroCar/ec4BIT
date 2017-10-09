/*
 * Copyright (C) 2013 - 2017 the enviroCar community
 *
 * This file is part of the enviroCar 4 BIG IoT Connector.
 *
 * The ec4BIT connector is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ec4BIT connector i is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.ec4bit.core.remote;

import org.envirocar.ec4bit.core.remote.services.TrackService;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

/**
 *
 * @author Arne de Wall <a.dewall@52north.org>
 */
@Configuration
public class RepoConfig {

    private static HttpUrl URL_ENVIROCAR_BASE = HttpUrl.parse("http://envirocar.org/api/stable/");

    @Bean
    protected HttpUrl createBaseUrl() {
        return URL_ENVIROCAR_BASE;
    }

    @Bean
    public TrackService createTrackService(Retrofit retrofit) {
        return retrofit.create(TrackService.class);
    }

    @Bean
    public MeasurementService createMeasurementService(Retrofit retrofit) {
        return retrofit.create(MeasurementService.class);
    }

    @Bean
    public Retrofit retrofit(HttpUrl baseUrl, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .build();
    }

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient client = new OkHttpClient();
        return client;
    }

}

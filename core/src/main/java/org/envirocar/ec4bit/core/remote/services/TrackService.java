/*
 * Copyright (C) 2013 - 2018 the enviroCar community
 *
 * This file is part of the enviroCar 4 BIG IoT Connector.
 *
 * The ec4BIT connector is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The ec4BIT connector is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.ec4bit.core.remote.services;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;


/**
 *
 * @author hafenkran
 */
public interface TrackService {

    @GET("tracks")
    Call<Tracks> getAsTracks();

    @GET("tracks")
    Call<Tracks> getAsTracks(@Query("limit") int limit);

    @GET("tracks")
    Call<Tracks> getAsTracks(@Query("bbox") String bbox, @Query("after") String after,
            @Query("before") String before, @Query("page") String page);

    @GET("tracks/{track}")
    Call<Track> getTrack(@Path("track") String trackID);
}

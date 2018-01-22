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
 * The ec4BIT connector is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.ec4bit.core.remote.services;

import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.model.SpeedValues;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author Arne de Wall <a.dewall@52north.org>
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public interface MeasurementService {

    @GET("measurements")
    Call<Measurements> getAsMeasurements();

    @GET("measurements")
    Call<Measurements> getAsMeasurements(@Query("limit") int limit);
    
    @GET("measurements")
    Call<Measurements> getAsMeasurements(@Query("bbox") String bbox, @Query("after") String after, 
            @Query("before") String before, @Query("page") String page);
    
    @GET("measurements/{measurement}")
    Call<Measurement> getAsMeasurement(@Path("measurement") String measurementID);

    @GET("measurements")
    Call<SpeedValues> getAsSpeedValues();

    @GET("measurements")
    Call<SpeedValues> getAsSpeedValues(@Query("limit") int limit);

    @GET("measurements")
    Call<SpeedValues> getAsSpeedValues(@Query("bbox") String bbox, @Query("after") String after,
            @Query("before") String before, @Query("page") String page);
    
}
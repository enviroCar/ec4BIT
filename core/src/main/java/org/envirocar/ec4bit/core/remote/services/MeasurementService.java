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
package org.envirocar.ec4bit.core.remote.services;

import okhttp3.ResponseBody;
import org.envirocar.ec4bit.core.model.SpeedValues;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 *
 * @author Arne de Wall <a.dewall@52north.org>
 */
public interface MeasurementService {

    @GET("measurements/")
    Call<ResponseBody> getAsRawResponse();

    @GET("measurements/")
    Call<ResponseBody> getAsRawResponse(@Query("bbox") String bbox);

    @GET("measurements/")
    Call<ResponseBody> getAsRawResponse(@Query("limit") int limit);

    @GET("measurements/")
    Call<SpeedValues> getAsSpeedValues();

    @GET("measurements/")
    Call<SpeedValues> getAsSpeedValues(@Query("limit") int limit);
}

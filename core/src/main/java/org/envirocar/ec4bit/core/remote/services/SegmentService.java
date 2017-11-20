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
import org.envirocar.ec4bit.core.model.Segment;
import org.envirocar.ec4bit.core.model.Segments;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * @author hafenkran
 */
public interface SegmentService {

    @GET("geoserver/envcar/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=envcar:osmSegments&maxFeatures=1000&outputFormat=application%2Fjson")
    Call<Segments> getAsSegments();
    
    @GET("geoserver/envcar/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=envcar:osmSegments&maxFeatures=1000&outputFormat=application%2Fjson")
    Call<Segments> getAsSegments(@Query("featureID") String featureID, @Query("bbox") String bbox, @Query("Filter") String intersects, 
            @Query("Filter") String within, @Query("Filter") String dwithin, @Query("sortBy") String sortBy, 
            @Query("Filter") String greaterThan, @Query("Filter") String lessThan, @Query("Filter") String betweenIn,
            @Query("Filter") String customWFSFilter);
    
//    @GET("geoserver/cite/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=cite:osmSegments&maxFeatures=1000&outputFormat=application%2Fjson")
//    Call<Segment> getSegment(@Query("featureID") String featureID);
    
}
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
package org.envirocar.ec4bit.core.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.envirocar.ec4bit.core.RemoteConfiguration;
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Tracks;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.remote.services.TrackService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(RemoteConfiguration.class)
public class TracksTest {

    @Autowired
    private TrackService trackService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testTracksSimple() throws IOException {
        Call<Tracks> tracks = this.trackService.getAsTracks(1);
        Tracks values = tracks.execute().body();
        Assert.assertNotNull(values);
    }

    @Test
    public void testTracksBBOX() throws IOException {
        Double latMin = 41d;
        Double latMax = 42d;
        Double longMin = 2d;
        Double longMax = 3d;
        String bboxString = longMin + "," + latMin + "," + longMax + "," + latMax;
        Call<Tracks> tracks = this.trackService
                .getAsTracks(bboxString, null, null, null);
        Tracks values = tracks.execute().body();
        Assert.assertNotNull(values);
        Track testTrack = values.getTracks().get(0);
        Call<Track> track = this.trackService
                .getTrack(testTrack.getTrackID());
        List<Measurement> measurements = track.execute().body().getMeasurements();
        Assert.assertNotNull(measurements);
        Boolean hasMeasurementsWithinBbox = false;
        for (Measurement m : measurements) {
            if (m.hasLatitude() && m.hasLongitude()) {
                Double latitude = m.getLatitude();
                Double longitude = m.getLongitude();
                if (latitude <= latMax
                        && latitude >= latMin
                        && longitude <= longMax
                        && longitude >= longMin) {
                    hasMeasurementsWithinBbox = true;
                    break;
                }
            }
        }
        Assert.assertTrue(hasMeasurementsWithinBbox);
    }

    @Test
    public void testTrackID() throws IOException {
        Call<Track> track = this.trackService.getTrack("5a4f1ee744ea85087e3a084f");
        Track value = track.execute().body();
        Assert.assertNotNull(value);
    }

    @Test
    public void testTracksStartDate() throws IOException {
        String startDate = "2018-01-13T00:00:00Z";
        DateTime dt_start = new DateTime(startDate);
        dt_start = dt_start.minusHours(1);
        Call<Tracks> tracks = this.trackService
                .getAsTracks(null, startDate, null, null);
        Tracks values = tracks.execute().body();
        Assert.assertNotNull(values);

        Track testTrack = values.getTracks().get(0);
        Call<Track> track = this.trackService
                .getTrack(testTrack.getTrackID());
        List<Measurement> measurements = track.execute().body().getMeasurements();
        Assert.assertNotNull(measurements);
        Boolean hasMeasurementsAfterStartDate = false;
        
        for (Measurement m : measurements) {
            DateTime measurementTime = m.getTime();
            long timeDiff = measurementTime.getMillis() - dt_start.getMillis();
            if (timeDiff >= 0) {
                hasMeasurementsAfterStartDate = true;
                break;
            }
        }
        Assert.assertTrue(hasMeasurementsAfterStartDate);
    }

    @Test
    public void testTracksEndDate() throws IOException {
        String endDate = "2018-01-13T00:00:00Z";
        DateTime dt_end = new DateTime(endDate);
        dt_end = dt_end.minusHours(1);
        Call<Tracks> tracks = this.trackService
                .getAsTracks(null, null, endDate, null);
        Tracks values = tracks.execute().body();
        Assert.assertNotNull(values);
        Track testTrack = values.getTracks().get(0);
        Call<Track> track = this.trackService
                .getTrack(testTrack.getTrackID());
        List<Measurement> measurements = track.execute().body().getMeasurements();
        Assert.assertNotNull(measurements);
        Boolean hasMeasurementsBeforeEndDate = false;
        for (Measurement m : measurements) {
            DateTime measurementTime = m.getTime();
            long timeDiff = dt_end.getMillis() - measurementTime.getMillis();
            if (timeDiff >= 0) {
                hasMeasurementsBeforeEndDate = true;
                break;
            }
        }
        Assert.assertTrue(hasMeasurementsBeforeEndDate);
    }

    @Test
    public void testTracksStartDateEndDate() throws IOException {
        String startDate = "2018-01-05T00:00:00Z";
        DateTime dt_start = new DateTime(startDate);
        dt_start = dt_start.minusHours(1);
        String endDate = "2018-01-27T00:00:00Z";
        DateTime dt_end = new DateTime(endDate);
        dt_end = dt_end.minusHours(1);
        Call<Tracks> tracks = this.trackService
                .getAsTracks(null, startDate, endDate, null);
        Tracks values = tracks.execute().body();
        Assert.assertNotNull(values);
        Track testTrack = values.getTracks().get(0);
        Call<Track> track = this.trackService
                .getTrack(testTrack.getTrackID());
        List<Measurement> measurements = track.execute().body().getMeasurements();
        Assert.assertNotNull(measurements);
        Boolean hasMeasurementsDuringStartEndDate = false;
        for (Measurement m : measurements) {
            DateTime measurementTime = m.getTime();
            long timeDiff = measurementTime.getMillis() - dt_start.getMillis();
            if (timeDiff >= 0) {
                hasMeasurementsDuringStartEndDate = true;
                break;
            }
            timeDiff = dt_end.getMillis() - measurementTime.getMillis();
            if (timeDiff >= 0) {
                hasMeasurementsDuringStartEndDate = true;
                break;
            }
        }
        Assert.assertTrue(hasMeasurementsDuringStartEndDate);
    }
}

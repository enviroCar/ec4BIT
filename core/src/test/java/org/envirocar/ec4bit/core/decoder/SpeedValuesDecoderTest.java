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

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.envirocar.ec4bit.core.RemoteConfiguration;
import org.envirocar.ec4bit.core.model.SpeedValues;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;
import retrofit2.Response;

/**
 *
 * @author dewall
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(RemoteConfiguration.class)
public class SpeedValuesDecoderTest {

    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSpeedValuesDecoding() throws IOException {
        Call<ResponseBody> measurements = this.measurementService.getAsRawResponse(1);
        String measurementsString = measurements.execute().body().string();

        SpeedValues speedValues = this.objectMapper.readValue(measurementsString, SpeedValues.class);
        System.out.println(speedValues.toString());

        Assert.assertNotNull(speedValues);
    }

    @Test
    public void testSpeedValueResponse() throws IOException {
        Response<SpeedValues> execute = this.measurementService.getAsSpeedValues(1).execute();
        SpeedValues result = execute.body();
        Assert.assertNotNull(result);
    }

}

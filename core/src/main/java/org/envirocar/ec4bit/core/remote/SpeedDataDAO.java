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

import java.util.List;
import okhttp3.ResponseBody;
import org.envirocar.ec4bit.core.model.SpeedValue;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;

/**
 *
 * @author dewall
 */
@Component
public class SpeedDataDAO implements AbstractDAO<SpeedValue> {

//    private Function<double[], String> bboxToString = new Function<double[], String>() {
//
//            }
    @Autowired
    private MeasurementService measurementService;

    public List<SpeedValue> getEntities(double[] bbox) {
        String bboxString = String.join(",", String.valueOf(bbox[0]), String.valueOf(bbox[1]),
                String.valueOf(bbox[2]), String.valueOf(bbox[3]));

        Call<ResponseBody> measurements = measurementService.getMeasurements();
        return null;
    }

}

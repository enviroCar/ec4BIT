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

import java.io.IOException;
import okhttp3.ResponseBody;
import org.envirocar.ec4bit.core.filter.MeasurementFilter;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class RawMeasurementsDAO {

    private static final Logger LOG = LoggerFactory.getLogger(RawMeasurementsDAO.class);

    @Autowired
    private MeasurementService measurementService;

//    @Override
    public String get(MeasurementFilter filter) {
        String bboxParam = null;
        String timeParam = null;
        String pageParam = null;

        if (filter.hasSpatialFilter()) {
            SpatialFilter bbox = filter.getSpatialFilter();
            bboxParam = bbox.string();
        }
        if (filter.hasTemporalFilter()) {
            TemporalFilter temp = filter.getTemporalFilter();
            timeParam = temp.string();
        }
        if (filter.hasPaginationFilter()) {
            PaginationFilter temp = filter.getPaginationFilter();
            pageParam = temp.string();
        }

        Call<ResponseBody> asMeasurements = measurementService
                .getAsRawResponse(bboxParam, timeParam, pageParam);

        try {
            ResponseBody body = asMeasurements.execute().body();
            return body.string();
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }

}

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
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.SpeedValueFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.model.SpeedValues;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;

/**
 *
 * @author dewall
 */
@Component
public class SpeedValuesDAO implements AbstractDAO<SpeedValues, SpeedValueFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedValuesDAO.class);

    @Autowired
    private MeasurementService measurementService;

    @Override
    public SpeedValues get(SpeedValueFilter filter) {
        String bboxParam = null;
        String timeParam = null;

        if (filter.hasSpatialFilter()) {
            SpatialFilter bbox = filter.getSpatialFilter();
            bboxParam = bbox.string();
        }
        if (filter.hasTemporalFilter()) {
            TemporalFilter temp = filter.getTemporalFilter();
            timeParam = null; // TODO
        }

        Call<SpeedValues> asSpeedValues = measurementService.getAsSpeedValues(bboxParam, timeParam, filter.getPage());
        try {
            SpeedValues body = asSpeedValues.execute().body();
            return body;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }

}

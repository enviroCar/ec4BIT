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
package org.envirocar.ec4bit.connector.producer;

import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.exception.KeyNotFoundException;
import org.envirocar.ec4bit.connector.exception.RequestProcessingException;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.SpeedValueFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.model.SpeedValues;
import org.envirocar.ec4bit.core.remote.SpeedValuesDAO;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import org.eclipse.bigiot.lib.offering.OfferingDescription;
import org.eclipse.bigiot.lib.serverwrapper.BigIotHttpResponse;

/**
 *
 * @author dewall
 */
@Component
public class SpeedRequestHandler extends AbstractRequestHandler<SpeedValues> {

    private static final Logger LOG = LoggerFactory.getLogger(SpeedRequestHandler.class);

    @Autowired
    private SpeedValuesDAO speedValuesDao;
    @Autowired
    private MeasurementService measurementService;

    /**
     * Constructor.
     */
    public SpeedRequestHandler() {
        super(SpeedValues.class);
    }

    @Override
    public SpeedValues processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {

            BigIotHttpResponse errorResponse = BigIotHttpResponse.error().withBody("{\"status\":\"error\"}")
                    .withStatus(422).asJsonType();
            SpatialFilter spatialFilter = null;
            TemporalFilter temporalFilter = null;
            PaginationFilter paginationFilter = null;

            if (input.containsKey(BBOX)) {
                spatialFilter = getSpatialFilterParams(input);
            }
            if (input.containsKey(START_DATE) || input.containsKey(END_DATE)) {
                temporalFilter = getTemporalFilterParams(input);
            }
            if (input.containsKey(PAGE)) {
                paginationFilter = getPaginationFilterParams(input);
            }

            SpeedValueFilter filter = new SpeedValueFilter(spatialFilter, temporalFilter, paginationFilter);
            return speedValuesDao.get(filter);
        } catch (KeyNotFoundException ex) {
            throw new RequestProcessingException(ex.getMessage(), 500);
        }
    }
}

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

import org.eclipse.bigiot.lib.offering.OfferingDescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.exception.KeyNotFoundException;
import org.envirocar.ec4bit.connector.exception.RequestProcessingException;
import org.envirocar.ec4bit.core.filter.MeasurementFilter;
import org.envirocar.ec4bit.core.filter.MeasurementIDFilter;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.PhenomenonFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.remote.MeasurementsDAO;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class MeasurementRequestHandler extends AbstractRequestHandler<Measurements> {

    private static final Logger LOG = LoggerFactory.getLogger(MeasurementRequestHandler.class);

    @Autowired
    private MeasurementsDAO measurementsDao;
    @Autowired
    private MeasurementService measurementService;

    /**
     * Constructor.
     */
    public MeasurementRequestHandler() {
        super(Measurements.class);
    }

    @Override
    public Measurements processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {
            SpatialFilter spatialFilter = null;
            TemporalFilter temporalFilter = null;
            PaginationFilter paginationFilter = null;
            PhenomenonFilter phenomenonFilter = null;
            MeasurementIDFilter measurementIDFilter = null;

            if (input.containsKey(BBOX)) {
                spatialFilter = getSpatialFilterParams(input);
            }
            if (input.containsKey(START_DATE) || input.containsKey(END_DATE)) {
                temporalFilter = getTemporalFilterParams(input);
            }
            if (input.containsKey(PAGE)) {
                paginationFilter = getPaginationFilterParams(input);
            }
            if (input.containsKey(PHENOMENONS)) {
                phenomenonFilter = getPhenomenonFilterParams(input);
            }
            if (input.containsKey(MEASUREMENTID)) {
                measurementIDFilter = getMeasurementIDFilter(input);
            }

            MeasurementFilter filter = new MeasurementFilter(spatialFilter, temporalFilter, paginationFilter, phenomenonFilter, measurementIDFilter);
            return measurementsDao.get(filter);
        } catch (KeyNotFoundException ex) {
            throw new RequestProcessingException(ex.getMessage(), 500);
        }
    }
}
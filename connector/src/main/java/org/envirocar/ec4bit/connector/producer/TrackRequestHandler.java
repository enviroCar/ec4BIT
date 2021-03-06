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

import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.exception.KeyNotFoundException;
import org.envirocar.ec4bit.connector.exception.RequestProcessingException;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.filter.TrackFilter;
import org.envirocar.ec4bit.core.filter.TrackIDFilter;
import org.envirocar.ec4bit.core.model.Tracks;
import org.envirocar.ec4bit.core.remote.TracksDAO;
import org.envirocar.ec4bit.core.remote.services.TrackService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class TrackRequestHandler extends AbstractRequestHandler<Tracks> {

    private static final Logger LOG = LoggerFactory.getLogger(TrackRequestHandler.class);

    @Autowired
    private TracksDAO tracksDAO;
    @Autowired
    private TrackService trackService;

    /**
     * Constructor.
     */
    public TrackRequestHandler() {
        super(Tracks.class);
    }

    @Override
    public Tracks processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {
            SpatialFilter spatialFilter = null;
            TemporalFilter temporalFilter = null;
            PaginationFilter paginationFilter = null;
            TrackIDFilter trackIDFilter = null;

            if (input.containsKey(BBOX)) {
                spatialFilter = getSpatialFilterParams(input);
            }
            if (input.containsKey(START_DATE) || input.containsKey(END_DATE)) {
                temporalFilter = getTemporalFilterParams(input);
            }
            if (input.containsKey(PAGE)) {
                paginationFilter = getPaginationFilterParams(input);
            }
            if (input.containsKey(TRACKID)) {
                trackIDFilter = getTrackIDFilter(input);
            }

            TrackFilter filter = new TrackFilter(spatialFilter, temporalFilter, paginationFilter, trackIDFilter);
            return tracksDAO.get(filter);
        } catch (KeyNotFoundException ex) {
            throw new RequestProcessingException(ex.getMessage(), 500);
        }
    }
}

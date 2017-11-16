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
package org.envirocar.ec4bit.connector.producer;

import java.util.Map;
import okhttp3.ResponseBody;
import org.eclipse.bigiot.lib.offering.OfferingDescription;
import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.exception.KeyNotFoundException;
import org.envirocar.ec4bit.connector.exception.RequestProcessingException;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;
import org.envirocar.ec4bit.core.remote.TracksDAO;
import org.envirocar.ec4bit.core.remote.services.TrackService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class SingleTrackRequestHandler extends AbstractRequestHandler<Tracks> {

    private static final Logger LOG = LoggerFactory.getLogger(SingleTrackRequestHandler.class);

    @Autowired
    private TracksDAO tracksDAO;
    @Autowired
    private TrackService trackService;

    /**
     * Constructor.
     */
    public SingleTrackRequestHandler() {
        super(Tracks.class);
    }

    @Override
    public Tracks processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {
            String trackId = "";

            if (input.containsKey(SINGLE_TRACK)) {
                trackId = getTrackID(input);
            }

            return tracksDAO.get(trackId);
        } catch (KeyNotFoundException ex) {
            throw new RequestProcessingException(ex.getMessage(), 500);
        }
    }
}

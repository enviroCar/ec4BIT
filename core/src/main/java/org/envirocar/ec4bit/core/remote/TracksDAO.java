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
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.filter.TrackFilter;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;
import org.envirocar.ec4bit.core.remote.services.TrackService;
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
public class TracksDAO implements AbstractDAO<Tracks, TrackFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(TracksDAO.class);

    @Autowired
    private TrackService trackService;

    @Override
    public Tracks get(TrackFilter filter) {
        String bboxParam = null;
        String timeBeforeParam = null;
        String timeAfterParam = null;
        String pageParam = null;

        if (filter.hasSpatialFilter()) {
            SpatialFilter bbox = filter.getSpatialFilter();
            bboxParam = bbox.string();
        }
        if (filter.hasTemporalFilter()) {
            TemporalFilter temp = filter.getTemporalFilter();
            timeBeforeParam = temp.stringBefore();
            timeAfterParam = temp.stringAfter();
        }
        if (filter.hasPaginationFilter()) {
            PaginationFilter temp = filter.getPaginationFilter();
            pageParam = temp.string();
        }

        Call<Tracks> asTracks = trackService
                .getAsTracks(bboxParam, timeAfterParam, timeBeforeParam,  pageParam);
        try {
            Tracks body = asTracks.execute().body();
            return body;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }
    
//    public Track get() {
//
//        Call<Track> asTrack = trackService
//                .getTrack(bboxParam, timeAfterParam, timeBeforeParam,  pageParam);
//        try {
//            Tracks body = asTracks.execute().body();
//            return body;
//        } catch (IOException ex) {
//            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
//        }
//
//        return null;
//    }

}
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
import org.envirocar.ec4bit.core.filter.DWithinFilter;
import org.envirocar.ec4bit.core.filter.FeatureIDFilter;
import org.envirocar.ec4bit.core.filter.GreaterThanFilter;
import org.envirocar.ec4bit.core.filter.IntersectsFilter;
import org.envirocar.ec4bit.core.filter.SegmentFilter;
import org.envirocar.ec4bit.core.filter.SortingFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.WithinFilter;
import org.envirocar.ec4bit.core.model.Segments;
import org.envirocar.ec4bit.core.model.Segment;
import org.envirocar.ec4bit.core.remote.services.SegmentService;
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
public class SegmentsDAO implements AbstractDAO<Segments, SegmentFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(SegmentsDAO.class);

    @Autowired
    private SegmentService segmentService;

    @Override
    public Segments get(SegmentFilter filter) {
        String bboxParam = null;
        String sortByParam = null;
        String featureIDParam = null;
        String intersectsParam = null;
        String withinParam = null;
        String dwithinParam = null;
        String greaterThanParam = null;

        if (filter.hasSpatialFilter()) {
            SpatialFilter bbox = filter.getSpatialFilter();
            bboxParam = bbox.string();
        }
        if (filter.hasIntersectsFilter()) {
            IntersectsFilter intersect = filter.getIntersectsFilter();
            intersectsParam = intersect.string();
        }
        if (filter.hasWithinFilter()) {
            WithinFilter within = filter.getWithinFilter();
            withinParam = within.string();
        }
        if (filter.hasDWithinFilter()) {
            DWithinFilter dwithin = filter.getDWithinFilter();
            dwithinParam = dwithin.string();
        }
        if (filter.hasSortingFilter()) {
            SortingFilter sortBy = filter.getSortingFilter();
            sortByParam = sortBy.string();
        }
        if (filter.hasFeatureIDFilter()) {
            FeatureIDFilter feature = filter.getFeatureIDFilter();
            featureIDParam = feature.string();
        }
        if (filter.hasGreaterThanFilter()) {
            GreaterThanFilter greaterThan = filter.getGreaterThanFilter();
            greaterThanParam = greaterThan.string();
        }

        Call<Segments> asSegments = segmentService
                .getAsSegments(featureIDParam, bboxParam, intersectsParam, withinParam, dwithinParam, sortByParam, greaterThanParam);
        try {
            Segments body = asSegments.execute().body();
            return body;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }

}
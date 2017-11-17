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
import org.envirocar.ec4bit.core.filter.DWithinFilter;
import org.envirocar.ec4bit.core.filter.FeatureIDFilter;
import org.envirocar.ec4bit.core.filter.IntersectsFilter;
import org.envirocar.ec4bit.core.filter.SegmentFilter;
import org.envirocar.ec4bit.core.filter.SortingFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.WithinFilter;
import org.envirocar.ec4bit.core.model.Segments;
import org.envirocar.ec4bit.core.remote.SegmentsDAO;
import org.envirocar.ec4bit.core.remote.services.SegmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class SegmentRequestHandler extends AbstractRequestHandler<Segments> {

    private static final Logger LOG = LoggerFactory.getLogger(SegmentRequestHandler.class);

    @Autowired
    private SegmentsDAO segmentDAO;
    @Autowired
    private SegmentService segmentService;

    /**
     * Constructor.
     */
    public SegmentRequestHandler() {
        super(Segments.class);
    }

    @Override
    public Segments processRequest(OfferingDescription od, Map<String, Object> input) throws RequestProcessingException {
        try {
            FeatureIDFilter featureIDFilter = null;
            SpatialFilter spatialFilter = null;
            SortingFilter sortingFilter = null;
            IntersectsFilter intersectFilter = null;
            DWithinFilter dwithinFilter = null;
            WithinFilter withinFilter = null;
            
            if (input.containsKey(FEATUREID)) {
                featureIDFilter = getFeatureIDFilter(input);
            }
            
            if (input.containsKey(SORT_BY)) {
                sortingFilter = getSortingFilterParams(input);
            }

            if (input.containsKey(BBOX)) {
                spatialFilter = getSpatialFilterParams(input);
            }
            
            if (input.containsKey(INTERSECT)) {
                intersectFilter = getIntersectsFilter(input);
            }
            
            if (input.containsKey(WITHIN)) {
                withinFilter = getWithinFilter(input);
            }
            
            if (input.containsKey(DWITHIN)) {
                dwithinFilter = getDWithinFilter(input);
            }

            SegmentFilter filter = new SegmentFilter(featureIDFilter, sortingFilter, spatialFilter, intersectFilter, withinFilter, dwithinFilter);
            return segmentDAO.get(filter);
        } catch (KeyNotFoundException ex) {
            throw new RequestProcessingException(ex.getMessage(), 500);
        }
    }
}
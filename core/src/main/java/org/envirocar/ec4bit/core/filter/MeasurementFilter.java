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
package org.envirocar.ec4bit.core.filter;

/**
 *
 * @author dewall
 */
public class MeasurementFilter {

    private final SpatialFilter spatialFilter;
    private final TemporalFilter temporalFilter;
    private final Integer page;

    /**
     * Constructor.
     *
     * @param spatialFilter
     */
    public MeasurementFilter(SpatialFilter spatialFilter) {
        this(spatialFilter, null, null);
    }

    /**
     * Constructor.
     *
     * @param temporalFilter
     */
    public MeasurementFilter(TemporalFilter temporalFilter) {
        this(null, temporalFilter, null);
    }

    /**
     * Constructor.
     *
     * @param page
     */
    public MeasurementFilter(Integer page) {
        this(null, null, page);
    }

    /**
     * Constructor.
     *
     * @param spatialFilter
     * @param temporalFilter
     * @param page
     */
    public MeasurementFilter(SpatialFilter spatialFilter, TemporalFilter temporalFilter, Integer page) {
        this.spatialFilter = spatialFilter;
        this.temporalFilter = temporalFilter;
        this.page = page;
    }

    public SpatialFilter getSpatialFilter() {
        return spatialFilter;
    }

    public boolean hasSpatialFilter() {
        return this.spatialFilter != null;
    }

    public TemporalFilter getTemporalFilter() {
        return temporalFilter;
    }

    public boolean hasTemporalFilter() {
        return this.temporalFilter != null;
    }

    public Integer getPage() {
        return page;
    }

    public boolean hasPage() {
        return page != null;
    }

}

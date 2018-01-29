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
package org.envirocar.ec4bit.core.filter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class TrackFilter {

    private final SpatialFilter spatialFilter;
    private final TemporalFilter temporalFilter;
    private final PaginationFilter paginationFilter;
    private final TrackIDFilter trackIDFilter;

    /**
     * Constructor.
     *
     * @param spatialFilter
     */
    public TrackFilter(SpatialFilter spatialFilter) {
        this(spatialFilter, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param temporalFilter
     */
    public TrackFilter(TemporalFilter temporalFilter) {
        this(null, temporalFilter, null, null);
    }

    /**
     * Constructor.
     *
     * @param paginationFilter
     */
    public TrackFilter(PaginationFilter paginationFilter) {
        this(null, null, paginationFilter, null);
    }
    
    /**
     * Constructor.
     *
     * @param trackIDFilter
     */
    public TrackFilter(TrackIDFilter trackIDFilter) {
        this(null, null, null, trackIDFilter);
    }

    /**
     * Constructor.
     *
     * @param spatialFilter
     * @param temporalFilter
     * @param paginationFilter
     * @param trackIDFilter
     */
    public TrackFilter(
            SpatialFilter spatialFilter, 
            TemporalFilter temporalFilter, 
            PaginationFilter paginationFilter, 
            TrackIDFilter trackIDFilter) {
        this.spatialFilter = spatialFilter;
        this.temporalFilter = temporalFilter;
        this.paginationFilter = paginationFilter;
        this.trackIDFilter = trackIDFilter;
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

    public PaginationFilter getPaginationFilter() {
        return paginationFilter;
    }

    public boolean hasPaginationFilter() {
        return this.paginationFilter != null;
    }

    public TrackIDFilter getTrackIDFilter() {
        return trackIDFilter;
    }

    public boolean hasTrackIDFilter() {
        return this.trackIDFilter != null;
    }
    
}
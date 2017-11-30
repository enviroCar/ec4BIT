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
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class MeasurementFilter {

    private final PhenomenonFilter phenomenonFilter;
    private final SpatialFilter spatialFilter;
    private final TemporalFilter temporalFilter;
    private final PaginationFilter paginationFilter;
    private final MeasurementIDFilter measurementIDFilter;
    /**
     * Constructor.
     *
     * @param spatialFilter
     */
    public MeasurementFilter(SpatialFilter spatialFilter) {
        this(spatialFilter, null, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param temporalFilter
     */
    public MeasurementFilter(TemporalFilter temporalFilter) {
        this(null, temporalFilter, null, null, null);
    }

    /**
     * Constructor.
     *
     * @param paginationFilter
     */
    public MeasurementFilter(PaginationFilter paginationFilter) {
        this(null, null, paginationFilter, null, null);
    }
    
    /**
     * Constructor.
     *
     * @param phenomenonFilter
     */
    public MeasurementFilter(PhenomenonFilter phenomenonFilter) {
        this(null, null, null, phenomenonFilter, null);
    }
    
    /**
     * Constructor.
     *
     * @param measurementIDFilter
     */
    public MeasurementFilter(MeasurementIDFilter measurementIDFilter) {
        this(null, null, null, null, measurementIDFilter);
    }

    /**
     * Constructor.
     *
     * @param spatialFilter
     * @param temporalFilter
     * @param paginationFilter
     * @param phenomenonFilter
     * @param measurementIDFilter
     */
    public MeasurementFilter(SpatialFilter spatialFilter, TemporalFilter temporalFilter, 
            PaginationFilter paginationFilter, PhenomenonFilter phenomenonFilter, MeasurementIDFilter measurementIDFilter) {
        this.spatialFilter = spatialFilter;
        this.temporalFilter = temporalFilter;
        this.paginationFilter = paginationFilter;
        this.phenomenonFilter = phenomenonFilter;
        this.measurementIDFilter = measurementIDFilter;
    }

    public SpatialFilter getSpatialFilter() {
        return spatialFilter;
    }

    public boolean hasSpatialFilter() {
        return this.spatialFilter != null;
    }

    public PhenomenonFilter getPhenomenonFilter() {
        return phenomenonFilter;
    }

    public boolean hasPhenomenonFilter() {
        return this.phenomenonFilter != null;
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

    public MeasurementIDFilter getMeasurementIDFilter() {
        return measurementIDFilter;
    }
    
    public boolean hasMeasurementIDFilter() {
        return this.measurementIDFilter != null;
    }

}
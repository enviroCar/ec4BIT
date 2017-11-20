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
public class SegmentFilter {

    private final SpatialFilter spatialFilter;
    private final SortingFilter sortingFilter;
    private final FeatureIDFilter featureFilter;
    private final IntersectsFilter intersectFilter;
    private final WithinFilter withinFilter;
    private final DWithinFilter dwithinFilter;
    private final GreaterThanFilter greaterThanFilter;
    private final LessThanFilter lessThanFilter;
    private final BetweenFilter betweenFilter;
    private final CustomWFSFilter customWFSFilter;

    /**
     * Constructor.
     *
     * @param spatialFilter
     * @param sortingFilter
     * @param featureFilter
     * @param intersectFilter
     * @param withinFilter
     * @param dwithinFilter
     * @param greaterThanFilter
     * @param lessThanFilter
     * @param betweenFilter
     * @param customWFSFilter;
     */
    public SegmentFilter(FeatureIDFilter featureFilter, SortingFilter sortingFilter, SpatialFilter spatialFilter, IntersectsFilter intersectFilter, WithinFilter withinFilter, DWithinFilter dwithinFilter, 
            GreaterThanFilter greaterThanFilter, LessThanFilter lessThanFilter, BetweenFilter betweenFilter, CustomWFSFilter customWFSFilter) {
        this.spatialFilter = spatialFilter;
        this.sortingFilter = sortingFilter;
        this.featureFilter = featureFilter;
        this.intersectFilter = intersectFilter;
        this.withinFilter = withinFilter;
        this.dwithinFilter = dwithinFilter;
        this.greaterThanFilter = greaterThanFilter;
        this.lessThanFilter = lessThanFilter;
        this.betweenFilter = betweenFilter;
        this.customWFSFilter = customWFSFilter;
    }

    public SpatialFilter getSpatialFilter() {
        return spatialFilter;
    }

    public boolean hasSpatialFilter() {
        return this.spatialFilter != null;
    }

    public SortingFilter getSortingFilter() {
        return this.sortingFilter;
    }

    public boolean hasSortingFilter() {
        return (this.sortingFilter != null);
    }

    public FeatureIDFilter getFeatureIDFilter() {
        return this.featureFilter;
    }

    public boolean hasFeatureIDFilter() {
        return (this.featureFilter != null);
    }

    public IntersectsFilter getIntersectsFilter() {
        return this.intersectFilter;
    }

    public boolean hasIntersectsFilter() {
        return (this.intersectFilter != null);
    }

    public WithinFilter getWithinFilter() {
        return this.withinFilter;
    }

    public boolean hasWithinFilter() {
        return (this.withinFilter != null);
    }

    public DWithinFilter getDWithinFilter() {
        return this.dwithinFilter;
    }

    public boolean hasDWithinFilter() {
        return (this.dwithinFilter != null);
    }

    public GreaterThanFilter getGreaterThanFilter() {
        return this.greaterThanFilter;
    }

    public boolean hasGreaterThanFilter() {
        return (this.greaterThanFilter != null);
    }

    public LessThanFilter getLessThanFilter() {
        return this.lessThanFilter;
    }

    public boolean hasLessThanFilter() {
        return (this.lessThanFilter != null);
    }

    public BetweenFilter getBetweenFilter() {
        return this.betweenFilter;
    }

    public boolean hasBetweenFilter() {
        return (this.betweenFilter != null);
    }

    public CustomWFSFilter getCustomWFSFilter() {
        return customWFSFilter;
    }

    public boolean hasCustomWFSFilter() {
        return (this.customWFSFilter != null);
    }
}
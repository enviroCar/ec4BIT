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

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dewall
 */
public class WithinFilter {

    private static final Logger LOG = LoggerFactory.getLogger(WithinFilter.class);

    private final String within;
    
    /**
     * Constructor.
     *
     * @param within
     */
    public WithinFilter(String within) {
        this.within = within;
    }

    public String getWithin() {
        return within;
    }

    @Override
    public int hashCode() {
        int hash = 11;
        hash = 61 * hash + Objects.hashCode(this.within);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WithinFilter other = (WithinFilter) obj;
        if (!Objects.equals(this.within, other.within)) {
            return false;
        }
        return true;
    }

    public String string() {
        String withinGeom = "";
        if (within != null) {
            withinGeom = "<Filter><Within><PropertyName>geom</PropertyName><Polygon><outerBoundaryIs><LinearRing><coordinates>" + within + "</coordinates></LinearRing></outerBoundaryIs></Polygon></Within></Filter>";
        } 
        return withinGeom;
    }

    @Override
    public String toString() {
        return "WithinFilter{"+"Polygon=" + within + "}";
    }
    
}
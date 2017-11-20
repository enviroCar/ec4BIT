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
public class DWithinFilter {

    private static final Logger LOG = LoggerFactory.getLogger(DWithinFilter.class);

    private final String dwithin;
    private final Integer meters;
    
    /**
     * Constructor.
     *
     * @param dwithin
     * @param meters
     */
    public DWithinFilter(String dwithin, int meters) {
        this.dwithin = dwithin;
        this.meters = meters;
    }

    public String getDWithin() {
        return dwithin;
    }

    public Integer getMeters() {
        return meters;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dwithin);
        hash = 53 * hash + Objects.hashCode(this.meters);
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
        final DWithinFilter other = (DWithinFilter) obj;
        if (!Objects.equals(this.dwithin, other.dwithin)) {
            return false;
        }
        if (!Objects.equals(this.meters, other.meters)) {
            return false;
        }
        return true;
    }

    public String string() {
        String dwith = "";
        if (dwithin != null && meters != null) {
            dwith = "<Filter><DWithin><PropertyName>the_geom</PropertyName><Point><coordinates>" + dwithin + "</coordinates></Point><Distance units='m'>" + String.valueOf(meters) + "</Distance></DWithin></Filter>";
        } 
        return dwith;
    }

    @Override
    public String toString() {
        return "DWithinFilter{"+"Point=" + dwithin + ", Distance=" + meters + "}";
    }
    
}
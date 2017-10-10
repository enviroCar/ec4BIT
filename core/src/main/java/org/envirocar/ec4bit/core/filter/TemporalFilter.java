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
import org.joda.time.DateTime;

/**
 *
 * @author dewall
 */
public class TemporalFilter {

    private final DateTime begin;
    private final DateTime end;

    /**
     * Constructor.
     *
     * @param begin
     * @param end
     */
    public TemporalFilter(DateTime begin, DateTime end) {
        this.begin = begin;
        this.end = end;
    }

    public DateTime getBegin() {
        return begin;
    }

    public DateTime getEnd() {
        return end;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.begin);
        hash = 53 * hash + Objects.hashCode(this.end);
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
        final TemporalFilter other = (TemporalFilter) obj;
        if (!Objects.equals(this.begin, other.begin)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }

}

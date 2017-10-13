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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dewall
 */
public class TemporalFilter {

    private static final DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z");

    private static final Logger LOG = LoggerFactory.getLogger(TemporalFilter.class);

    private final DateTime start;
    private final DateTime end;
    
    /**
     * Constructor.
     *
     * @param startDate
     * @param endDate
     */
    public TemporalFilter(DateTime startDate, DateTime endDate) {
        this.start = startDate;
        this.end = endDate;
    }

    public DateTime getStart() {
        return start;
    }

    public DateTime getEnd() {
        return end;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.start);
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
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }

    public String string() {
        return String.join(",", String.valueOf(start.toString(TEMPORAL_TIME_PATTERN)),
                            String.valueOf(end.toString(TEMPORAL_TIME_PATTERN)));
    }

    @Override
    public String toString() {
        return "TemporalFilter{"+"start="+start.toString(TEMPORAL_TIME_PATTERN)+", end=" + end.toString(TEMPORAL_TIME_PATTERN) + "}";
    }
    
}
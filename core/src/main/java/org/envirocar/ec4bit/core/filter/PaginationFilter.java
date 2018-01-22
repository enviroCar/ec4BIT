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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 *
 * @author dewall
 */
public class PaginationFilter {
    
    private static final Logger LOG = LoggerFactory.getLogger(PaginationFilter.class);
    
    private final int page;
    
    /**
     * Constructor
     * 
     * @param page
     */
    public PaginationFilter(int page) {
        this.page = page;
    }
    
    public int getPage() {
        return this.page;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.page);
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
        final PaginationFilter other = (PaginationFilter) obj;
        if (!Objects.equals(this.page, other.page)) {
            return false;
        }
        return true;
    }

    public String string() {
        return String.valueOf(page);
    }

    @Override
    public String toString() {
        return "PaginationFilter{" + "page=" + page + "}";
    }
}
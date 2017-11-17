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
public class GreaterThanFilter {

    private static final Logger LOG = LoggerFactory.getLogger(GreaterThanFilter.class);

    private final String greaterThan;
    private final String attribute;
    
    /**
     * Constructor.
     *
     * @param greaterThan
     * @param attribute
     */
    public GreaterThanFilter(String attribute, String greaterThan) {
        this.greaterThan = greaterThan;
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public String getGreaterThan() {
        return greaterThan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.greaterThan);
        hash = 59 * hash + Objects.hashCode(this.attribute);
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
        final GreaterThanFilter other = (GreaterThanFilter) obj;
        if (!Objects.equals(this.greaterThan, other.greaterThan)) {
            return false;
        }
        if (!Objects.equals(this.attribute, other.attribute)) {
            return false;
        }
        return true;
    }

    public String string() {
        String isGreaterThan = "";
        if (greaterThan != null) {
            isGreaterThan = "<Filter><PropertyIsGreaterThan><PropertyName>" + attribute + "</PropertyName><Literal>" + greaterThan + "</Literal></PropertyIsGreaterThan></Filter>";
        } 
        return isGreaterThan;
    }

    @Override
    public String toString() {
        return "PropertyIsGreaterThanFilter{"+"Attribute=" + attribute + ", GreaterThan=" + greaterThan + "}";
    }
    
}
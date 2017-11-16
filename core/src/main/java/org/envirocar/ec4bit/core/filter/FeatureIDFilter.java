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
public class FeatureIDFilter {

    private static final Logger LOG = LoggerFactory.getLogger(FeatureIDFilter.class);

    private final String featureID;
    
    /**
     * Constructor.
     *
     * @param attribute
     */
    public FeatureIDFilter(String featureID) {
        this.featureID = featureID;
    }

    public String getFeatureID() {
        return featureID;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 51 * hash + Objects.hashCode(this.featureID);
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
        final FeatureIDFilter other = (FeatureIDFilter) obj;
        if (!Objects.equals(this.featureID, other.featureID)) {
            return false;
        }
        return true;
    }

    public String string() {
        String featureIDParam = "";
        if (featureID != null) {
            featureIDParam = featureID;
        } 
        return featureIDParam;
    }

    @Override
    public String toString() {
        return "FeatureIDFilter{"+"featureID=" + featureID + "}";
    }
    
}
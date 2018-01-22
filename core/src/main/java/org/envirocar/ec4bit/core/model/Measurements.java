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
 * The ec4BIT connector is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with the enviroCar app. If not, see http://www.gnu.org/licenses/.
 */
package org.envirocar.ec4bit.core.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author dewall
 */
public class Measurements implements BaseEntity {

    private List<Measurement> measurements;
    
    private Set<String> phenomDefinitions;

    public Measurements() {
        this.phenomDefinitions = new HashSet<String>();
        this.measurements = new ArrayList<>();
    }

    public void addMeasurement(Measurement measurement) {
        this.measurements.add(measurement);
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurement) {
        this.measurements = measurement;
    }
    
    public void addPhenomDefinition(String phenomDef) {
        this.phenomDefinitions.add(phenomDef);
    }
    
    public boolean containsPhenomDefinition(String phenomDef) {
        return this.phenomDefinitions.contains(phenomDef);
    }

}
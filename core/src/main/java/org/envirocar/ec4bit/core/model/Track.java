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
package org.envirocar.ec4bit.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class Track {
    
    private String trackID;
    private String sensor;
    private Double length;
    
    private List<Measurement> measurements;
    
    public Track() {
        this(null, null, null);
    }
    
    public Track(String trackID, String sensor, Double length) {
        this.trackID = trackID;
        this.sensor = sensor;
        this.length = length;
        this.measurements = new ArrayList<>();
    }
    
    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }
    
    public String getTrackID() {
        return this.trackID;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getSensor() {
        return sensor;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getLength() {
        return length;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }
    
}

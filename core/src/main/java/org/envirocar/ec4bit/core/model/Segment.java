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
public class Segment {
    
    private String segmentID;
    private Integer osmID;
    private List<CoordinatePair> points;
    private Double avgSpeed;
    private Double numSpeed;
    
    public Segment() {
        this(null, null, null, null, null);
    }

    public Segment(String segmentID, Integer osmID, List<CoordinatePair> points, Double avgSpeed, Double numSpeed) {
        this.segmentID = segmentID;
        this.osmID = osmID;
        this.points = points;
        this.avgSpeed = avgSpeed;
        this.numSpeed = numSpeed;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }

    public int getOsmID() {
        return osmID;
    }

    public void setOsmID(int osmID) {
        this.osmID = osmID;
    }

    public List<CoordinatePair> getPoints() {
        return points;
    }

    public void setPoints(List<CoordinatePair> points) {
        this.points = points;
    }

    public Double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Double getNumSpeed() {
        return numSpeed;
    }

    public void setNumSpeed(Double numSpeed) {
        this.numSpeed = numSpeed;
    }
    
}

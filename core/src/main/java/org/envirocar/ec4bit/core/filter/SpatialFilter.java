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
package org.envirocar.ec4bit.core.filter;

/**
 *
 * @author dewall
 */
public class SpatialFilter {

    private final double xmin;
    private final double ymin;
    private final double xmax;
    private final double ymax;

    /**
     * Constructor.
     *
     * @param xmin
     * @param ymin
     * @param xmax
     * @param ymax
     */
    public SpatialFilter(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public double getXmin() {
        return xmin;
    }

    public double getYmin() {
        return ymin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmax() {
        return ymax;
    }

    public String string() {
        return String.join(",", String.valueOf(xmin), String.valueOf(ymin), 
                String.valueOf(xmax), String.valueOf(ymax));
    }

    @Override
    public String toString() {
        return "SpatialFilter{" + "xmin=" + xmin + ", ymin=" + ymin + ", xmax=" + xmax + ", ymax=" + ymax + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.xmin) ^ (Double.doubleToLongBits(this.xmin) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.ymin) ^ (Double.doubleToLongBits(this.ymin) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.xmax) ^ (Double.doubleToLongBits(this.xmax) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.ymax) ^ (Double.doubleToLongBits(this.ymax) >>> 32));
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
        final SpatialFilter other = (SpatialFilter) obj;
        if (Double.doubleToLongBits(this.xmin) != Double.doubleToLongBits(other.xmin)) {
            return false;
        }
        if (Double.doubleToLongBits(this.ymin) != Double.doubleToLongBits(other.ymin)) {
            return false;
        }
        if (Double.doubleToLongBits(this.xmax) != Double.doubleToLongBits(other.xmax)) {
            return false;
        }
        if (Double.doubleToLongBits(this.ymax) != Double.doubleToLongBits(other.ymax)) {
            return false;
        }
        return true;
    }

}
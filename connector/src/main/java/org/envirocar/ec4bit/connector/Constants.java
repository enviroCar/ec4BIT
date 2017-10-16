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
package org.envirocar.ec4bit.connector;

/**
 *
 * @author hafenkran
 */
interface Constants {

    static final String SCHEMA_LONGITUDE = "http://schema.org/longitude";
    static final String SCHEMA_LATITUDE = "http://schema.org/latitude";

    static final String PAGE = "page";
    static final String PAGE_NUMBER = "pageNumber";
    
    static final String SCHEMA_PAGE = "bigiot:Pagination";
    static final String SCHEMA_PAGE_NUMBER= "bigiot:pageNumber";
    
    static final String DURING = "during";
    static final String DURING_START = "startDate";
    static final String DURING_END = "endDate";
    
    static final String SCHEMA_DURING_START= "bigiot:startDate";
    static final String SCHEMA_DURING_END = "bigiot:endDate";

    static final String BBOX = "bbox";
    static final String BBOX_XMIN = "xMin";
    static final String BBOX_YMIN = "yMin";
    static final String BBOX_XMAX = "xMax";
    static final String BBOX_YMAX = "yMax";

    static final String SCHEMA_BBOX = "bigiot:BoundingBox";
    static final String SCHEMA_BBOX_XMIN = "bigiot:xMin";
    static final String SCHEMA_BBOX_YMIN = "bigiot:yMin";
    static final String SCHEMA_BBOX_XMAX = "bigiot:xMax";
    static final String SCHEMA_BBOX_YMAX = "bigiot:yMax";
}

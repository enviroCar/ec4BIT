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

    // BoundingBoxFilter
    static final String SCHEMA_BBOX = "http://schema.org/box";

    // TemporalFilter
    static final String SCHEMA_DURING_START = "http://schema.org/startDate";
    static final String SCHEMA_DURING_END = "http://schema.org/endDate";
    static final String AFTER = "after";
    static final String BEFORE = "before";
    static final String TIME_AFTER = "startDate";
    static final String TIME_BEFORE = "endDate";

    static final String PAGE = "page";
    static final String PAGE_NUMBER = "pageNumber";
    static final String SCHEMA_PAGINATION_PAGENUMBER = "SCHEMA_PAGINATION_PAGENUMBER";

    static final String BBOX = "box";
    static final String BBOX_XMIN = "xMin";
    static final String BBOX_YMIN = "yMin";
    static final String BBOX_XMAX = "xMax";
    static final String BBOX_YMAX = "yMax";

    // PaginationFilter
    static final String SCHEMA_PAGE_NUMBER = "bigiot:pageNumber";

    // measurement components and phenomenons
    static final String SCHEMA_LONGITUDE = "http://schema.org/longitude";
    static final String SCHEMA_LATITUDE = "http://schema.org/latitude";
    static final String SCHEMA_ID = "http://schema.org/identifier";
    static final String SCHEMA_TIMESTAMP = "http://schema.org/DateTime";
    static final String SCHEMA_SENSOR = "http://schema.org/identifier";
    static final String SCHEMA_TRACK = "http://schema.org/identifier";
    static final String SCHEMA_LENGTH = "SCHEMA_LENGTH";
    static final String SCHEMA_SPEED = "http://auto.schema.org/speed"; // http://schema.org/QuantitativeValue für alle number phenomenons?
    static final String SCHEMA_CO2 = "http://auto.schema.org/emissionsCO2";
    static final String SCHEMA_CONSUMPTION = "http://schema.org/fuelConsumption";
    static final String SCHEMA_MAF = "SCHEMA_MAF";
    static final String SCHEMA_GPS_SPEED = "http://auto.schema.org/speed";
    static final String SCHEMA_GPS_ALTITUDE = "http://schema.org/elevation";
    static final String SCHEMA_INTAKE_TEMPERATURE = "SCHEMA_INTAKE_TEMPERATURE";
    static final String SCHEMA_INTAKE_PRESSURE = "SCHEMA_INTAKE_PRESSURE";
    static final String SCHEMA_RPM = "SCHEMA_RPM";
    static final String SCHEMA_ENGINE_LOAD = "SCHEMA_ENGINE_LOAD";
    static final String SCHEMA_FUEL_SYSTEM_LOOP = "SCHEMA_FUEL_SYSTEM_LOOP";
    static final String SCHEMA_FUEL_SYSTEM_STATUS_CODE = "SCHEMA_FUEL_SYSTEM_STATUS_CODE";
    static final String SCHEMA_GPS_ACCURACY = "SCHEMA_GPS_ACCURACY";
    static final String SCHEMA_GPS_BEARING = "SCHEMA_GPS_BEARING";
    static final String SCHEMA_LONG_TERM_FUEL_TRIM_1 = "SCHEMA_LONG_TERM_FUEL_TRIM_1";
    static final String SCHEMA_SHORT_TERM_FUEL_TRIM_1 = "SCHEMA_SHORT_TERM_FUEL_TRIM_1";
    static final String SCHEMA_THROTTLE_POSITION = "SCHEMA_THROTTLE_POSITION";
    static final String SCHEMA_GPS_HDOP = "SCHEMA_GPS_HDOP";
    static final String SCHEMA_GPS_VDOP = "SCHEMA_GPS_VDOP";
    static final String SCHEMA_GPS_PDOP = "SCHEMA_GPS_PDOP";
    static final String SCHEMA_CALCULATED_MAF = "SCHEMA_CALCULATED_MAF";
    static final String SCHEMA_O2_LAMBDA_CURRENT = "SCHEMA_O2_LAMBDA_CURRENT";
    static final String SCHEMA_O2_LAMBDA_CURRENT_ER = "SCHEMA_O2_LAMBDA_CURRENT_ER";
    static final String SCHEMA_O2_LAMBDA_VOLTAGE = "SCHEMA_O2_LAMBDA_VOLTAGE";
    static final String SCHEMA_O2_LAMBDA_VOLTAGE_ER = "SCHEMA_O2_LAMBDA_VOLTAGE_ER";

}

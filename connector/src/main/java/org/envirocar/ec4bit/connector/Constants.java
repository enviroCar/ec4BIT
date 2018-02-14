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
package org.envirocar.ec4bit.connector;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
interface Constants {


    // TemporalFilter
    static final String SCHEMA_START_DATE = "http://schema.big-iot.org/mobility/startDate";
    static final String SCHEMA_END_DATE = "http://schema.big-iot.org/mobility/endDate";
    static final String AFTER = "after";
    static final String BEFORE = "before";
    static final String START_DATE = "startDate";
    static final String END_DATE = "endDate";

    // PaginationFilter
    static final String PAGE = "page";
    static final String PAGE_NUMBER = "pageNumber";
    static final String SCHEMA_PAGINATION_PAGENUMBER = "http://schema.big-iot.org/common/pageNumber";

    // BoundingBoxFilter
    static final String SCHEMA_BBOX = "http://schema.org/box";
    static final String BBOX = "bbox";
    static final String BBOX_XMIN = "xMin";
    static final String BBOX_YMIN = "yMin";
    static final String BBOX_XMAX = "xMax";
    static final String BBOX_YMAX = "yMax";
    
    // PhenomenonFilter
    static final String SCHEMA_PHENOMENONS = "http://schema.big-iot.org/mobility/carParameter";
    static final String PHENOMENONS = "phenomenons";
    
    // track components
    static final String SCHEMA_TRACK = "http://schema.big-iot.org/mobility/Track";
    static final String SINGLE_TRACK = "track";
    static final String TRACKID = "trackID";
    static final String SCHEMA_MEASUREMENT_ID = "http://schema.big-iot.org/common/measurementID";
    static final String SCHEMA_MEASUREMENT_URL = "http://schema.big-iot.org/common/measurementURL";
    static final String MEASUREMENTID = "measurementID";
    static final String SCHEMA_TRACK_ID = "http://schema.big-iot.org/mobility/trackID";
    static final String SCHEMA_TRACK_URL = "http://schema.big-iot.org/mobility/trackURL";
    static final String SCHEMA_TRACK_LENGTH = "http://schema.big-iot.org/mobility/trackLength";

    // measurement components and phenomenons
    static final String SCHEMA_LONGITUDE = "http://schema.org/longitude";
    static final String SCHEMA_LATITUDE = "http://schema.org/latitude";
    static final String SCHEMA_TIMESTAMP = "http://schema.org/DateTime";
    static final String SCHEMA_SENSOR_ID = "http://schema.big-iot.org/common/sensorID";
    static final String SCHEMA_SENSOR_URL = "http://schema.big-iot.org/common/sensorURL";
    static final String SCHEMA_SPEED = "http://schema.big-iot.org/mobility/measuredSpeed";
    static final String SCHEMA_CO2 = "http://schema.big-iot.org/mobility/emissionsCO2";
    static final String SCHEMA_CONSUMPTION = "http://schema.big-iot.org/mobility/fuelConsumption";
    static final String SCHEMA_MAF = "http://schema.big-iot.org/mobility/massAirFlow";
    static final String SCHEMA_GPS_SPEED = "http://schema.big-iot.org/mobility/gpsSpeed";
    static final String SCHEMA_GPS_ALTITUDE = "http://schema.big-iot.org/mobility/gpsAltitude";
    static final String SCHEMA_INTAKE_TEMPERATURE = "http://schema.big-iot.org/mobility/intakeAirTemperature";
    static final String SCHEMA_INTAKE_PRESSURE = "http://schema.big-iot.org/mobility/intakeManifoldAbsolutePressure";
    static final String SCHEMA_RPM = "http://schema.big-iot.org/mobility/engineRevolutionsPerMinute";
    static final String SCHEMA_ENGINE_LOAD = "http://schema.big-iot.org/mobility/calculatedEngineLoad";
    static final String SCHEMA_FUEL_SYSTEM_STATUS_CODE = "http://schema.big-iot.org/mobility/fuelSystemStatusCode";
    static final String SCHEMA_GPS_ACCURACY = "http://schema.big-iot.org/mobility/gpsAccuracy";
    static final String SCHEMA_GPS_BEARING = "http://schema.big-iot.org/mobility/gpsBearing";
    static final String SCHEMA_LONG_TERM_FUEL_TRIM_1 = "http://schema.big-iot.org/mobility/longTermFuelTrim1";
    static final String SCHEMA_SHORT_TERM_FUEL_TRIM_1 = "http://schema.big-iot.org/mobility/shortTermFuelTrim1";
    static final String SCHEMA_THROTTLE_POSITION = "http://schema.big-iot.org/mobility/throttlePosition";
    static final String SCHEMA_GPS_HDOP = "http://schema.big-iot.org/mobility/gpsHDOP";
    static final String SCHEMA_GPS_VDOP = "http://schema.big-iot.org/mobility/gpsVDOP";
    static final String SCHEMA_GPS_PDOP = "http://schema.big-iot.org/mobility/gpsPDOP";
    static final String SCHEMA_CALCULATED_MAF = "http://schema.big-iot.org/mobility/calculatedMassAirFlow";
    static final String SCHEMA_O2_LAMBDA_CURRENT = "http://schema.big-iot.org/mobility/o2LambdaCurrent";
    static final String SCHEMA_O2_LAMBDA_CURRENT_ER = "http://schema.big-iot.org/mobility/o2LambdaCurrentER";
    static final String SCHEMA_O2_LAMBDA_VOLTAGE = "http://schema.big-iot.org/mobility/o2LambdaVoltage";
    static final String SCHEMA_O2_LAMBDA_VOLTAGE_ER = "http://schema.big-iot.org/mobility/o2LambdaVoltageER";

}

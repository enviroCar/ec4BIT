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
package org.envirocar.ec4bit.connector.producer;


import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.BigIotTypes.PricingModel;
import org.eclipse.bigiot.lib.model.BoundingBox;
import org.eclipse.bigiot.lib.model.Location;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.EC4BITProducer;
import org.joda.time.DateTime;

import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class TrackProducer extends EC4BITProducer {

    private static final String SCHEMA_BIGIOT_TRACK_CATEGORY = "urn:proposed:Traffic_Track";

    @Value("${bigiot.applications.tracks.local_id}")
    private String localId;
    @Value("${bigiot.applications.tracks.name}")
    private String name;
    @Value("${bigiot.applications.tracks.route}")
    private String route;
    @Value("${bigiot.applications.tracks.expireDate}")
    private String expires;

    @Autowired
    private TrackRequestHandler requestHandler;

    @Override
    protected RegistrableOfferingDescription getOfferingDescription() {
        DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime now = new DateTime();
        DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(expires);
        long millis = expireDate.getMillis() - now.getMillis();
        return provider.createOfferingDescription(localId)
                .withName(name)
                .withCategory(SCHEMA_BIGIOT_TRACK_CATEGORY)
                .withTimePeriod(new DateTime(2013, 1, 1, 0, 0, 0), new DateTime(2018,2,27,0,0,0))
                .inRegion(BoundingBox.create(Location.create(-70, -180), Location.create(90, 180)))
                // track filter options:
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGINATION_PAGENUMBER), ValueType.NUMBER)
                .addInputData("trackID", new RDFType(SCHEMA_TRACK_ID), ValueType.TEXT)
                // track components:
                .addOutputData("trackID", new RDFType(SCHEMA_TRACK_ID), ValueType.TEXT)
                .addOutputData("trackRef", new RDFType(SCHEMA_TRACK_URL), ValueType.TEXT)
                .addOutputData("sensorID", new RDFType(SCHEMA_SENSOR_ID), ValueType.TEXT)
                .addOutputData("sensorRef", new RDFType(SCHEMA_SENSOR_URL), ValueType.TEXT)
                .addOutputData("length", new RDFType(SCHEMA_TRACK_LENGTH), ValueType.NUMBER)
                //     track measurements:
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.TEXT)
                .addOutputData("co2", new RDFType(SCHEMA_CO2), ValueType.TEXT)
                .addOutputData("consumption", new RDFType(SCHEMA_CONSUMPTION), ValueType.TEXT)
                .addOutputData("maf", new RDFType(SCHEMA_MAF), ValueType.TEXT)
                .addOutputData("GPS speed", new RDFType(SCHEMA_GPS_SPEED), ValueType.TEXT)
                .addOutputData("GPS altitude", new RDFType(SCHEMA_GPS_ALTITUDE), ValueType.TEXT)
                .addOutputData("Intake Temperature", new RDFType(SCHEMA_INTAKE_TEMPERATURE), ValueType.TEXT)
                .addOutputData("Intake Pressure", new RDFType(SCHEMA_INTAKE_PRESSURE), ValueType.TEXT)
                .addOutputData("rpm", new RDFType(SCHEMA_RPM), ValueType.TEXT)
                .addOutputData("engine load", new RDFType(SCHEMA_ENGINE_LOAD), ValueType.TEXT)
                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
                .addOutputData("GPS bearing", new RDFType(SCHEMA_GPS_BEARING), ValueType.TEXT)
                .addOutputData("long term fuel Trim 1", new RDFType(SCHEMA_LONG_TERM_FUEL_TRIM_1), ValueType.NUMBER)
                .addOutputData("short term fuel Trim 1", new RDFType(SCHEMA_SHORT_TERM_FUEL_TRIM_1), ValueType.NUMBER)
                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.TEXT)
                .addOutputData("throttle position", new RDFType(SCHEMA_THROTTLE_POSITION), ValueType.TEXT)
                .addOutputData("GPS HDOP", new RDFType(SCHEMA_GPS_HDOP), ValueType.TEXT)
                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
                .addOutputData("GPS PDOP", new RDFType(SCHEMA_GPS_PDOP), ValueType.TEXT)
                .addOutputData("calculated maf", new RDFType(SCHEMA_CALCULATED_MAF), ValueType.TEXT)
                .addOutputData("o2 lambda current", new RDFType(SCHEMA_O2_LAMBDA_CURRENT), ValueType.TEXT)
                .addOutputData("o2 lambda current ER", new RDFType(SCHEMA_O2_LAMBDA_CURRENT_ER), ValueType.TEXT)
                .addOutputData("o2 lambda voltage", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE), ValueType.TEXT)
                .addOutputData("o2 lambda voltage ER", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE_ER), ValueType.TEXT)
                .withExpirationInterval(new Duration(millis))
                .withPricingModel(PricingModel.FREE)
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                .withRoute(route)
                .withAccessRequestHandler(getRequestHandler());
    }

    @Override
    protected AbstractRequestHandler getRequestHandler() {
        return requestHandler;
    }

}

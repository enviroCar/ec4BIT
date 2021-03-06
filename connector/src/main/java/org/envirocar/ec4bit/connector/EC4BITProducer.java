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

import org.eclipse.bigiot.lib.Provider;
import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.BoundingBox;
import org.eclipse.bigiot.lib.model.Location;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescriptionChain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public abstract class EC4BITProducer implements InitializingBean, DisposableBean, Constants {

    @Value("${bigiot.applications.tracks.route}")
    private String route_tracks;
    @Value("${bigiot.applications.tracks.expireDate}")
    private String tracks_expireDate;
    @Value("${bigiot.applications.measurements.route}")
    private String route_measurements;
    @Value("${bigiot.applications.measurements.expireDate}")
    private String measurements_expireDate;
    @Value("${bigiot.applications.speedvalues.route}")
    private String route_speed_measurements;
    @Value("${bigiot.applications.speedvalues.expireDate}")
    private String speed_expireDate;
    private static final String SCHEMA_BIGIOT_SPEEDVALUE_CATEGORY = "urn:proposed:Traffic_Speed";
    private static final String SCHEMA_BIGIOT_MEASUREMENT_CATEGORY = "urn:proposed:Traffic_Measurement";
    private static final String SCHEMA_BIGIOT_TRACK_CATEGORY = "urn:proposed:Traffic_Track";

    @Autowired
    protected Provider provider;
    protected RegistrableOfferingDescription offeringDescription;

    protected abstract RegistrableOfferingDescription getOfferingDescription();

    protected abstract AbstractRequestHandler getRequestHandler();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.offeringDescription = getOfferingDescription();
        this.provider.register(offeringDescription);
    }

    @Override
    public void destroy() throws Exception {
        this.offeringDescription.deregister();
        this.provider.deregister(offeringDescription.getOfferingId());
        this.provider.terminate();
    }

    // Tracks Offering Description for Endpoint:  /tracks
    protected RegistrableOfferingDescriptionChain addTracksOfferingDescription(RegistrableOfferingDescriptionChain offering) {
        DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime now = new DateTime();
        DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(tracks_expireDate);
        long millis = expireDate.getMillis() - now.getMillis();
        return offering
                .withTimePeriod(new DateTime(2013, 1, 1, 0, 0, 0), new DateTime(2018,2,27,0,0,0))
                .inRegion(BoundingBox.create(Location.create(-70, -180), Location.create(90, 180)))
                .withCategory(SCHEMA_BIGIOT_TRACK_CATEGORY)
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGINATION_PAGENUMBER), ValueType.NUMBER)
                .addInputData("trackID", new RDFType(SCHEMA_TRACK_ID), ValueType.TEXT)
                //     track components:
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
                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.TEXT)
                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
                .addOutputData("GPS bearing", new RDFType(SCHEMA_GPS_BEARING), ValueType.TEXT)
                .addOutputData("long term fuel Trim 1", new RDFType(SCHEMA_LONG_TERM_FUEL_TRIM_1), ValueType.NUMBER)
                .addOutputData("short term fuel Trim 1", new RDFType(SCHEMA_SHORT_TERM_FUEL_TRIM_1), ValueType.NUMBER)
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
                .withPricingModel(BigIotTypes.PricingModel.FREE)
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                .withRoute(route_tracks)
                .withAccessRequestHandler(getRequestHandler());
    }

    // Measurements Offering Description for Endpoint:  /measurements
    protected RegistrableOfferingDescriptionChain addMeasurementsOfferingDescription(RegistrableOfferingDescriptionChain offering) {
        DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime now = new DateTime();
        DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(measurements_expireDate);
        long millis = expireDate.getMillis() - now.getMillis();
        return offering
                .withTimePeriod(new DateTime(2013, 1, 1, 0, 0, 0), new DateTime(2018,2,27,0,0,0))
                .inRegion(BoundingBox.create(Location.create(-70, -180), Location.create(90, 180)))
                .withCategory(SCHEMA_BIGIOT_MEASUREMENT_CATEGORY)
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGINATION_PAGENUMBER), ValueType.NUMBER)
                .addInputData("phenomenons", new RDFType(SCHEMA_PHENOMENONS), ValueType.TEXT)
                .addInputData("measurementID", new RDFType(SCHEMA_MEASUREMENT_ID), ValueType.TEXT)
                // measurements components:
                .addOutputData("measurementID", new RDFType(SCHEMA_MEASUREMENT_ID), ValueType.TEXT)
                .addOutputData("measurementRef", new RDFType(SCHEMA_MEASUREMENT_URL), ValueType.TEXT)
                .addOutputData("sensorID", new RDFType(SCHEMA_SENSOR_ID), ValueType.TEXT)
                .addOutputData("sensorRef", new RDFType(SCHEMA_SENSOR_URL), ValueType.TEXT)
                .addOutputData("trackID", new RDFType(SCHEMA_TRACK_ID), ValueType.TEXT)
                .addOutputData("trackRef", new RDFType(SCHEMA_TRACK_URL), ValueType.TEXT)
                // measurements phenomenons:
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
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
                .withPricingModel(BigIotTypes.PricingModel.FREE)
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                .withRoute(route_measurements)
                .withAccessRequestHandler(getRequestHandler());
    }

    // Speed Values Offering Description for Endpoint:  /speedvalues
    protected RegistrableOfferingDescriptionChain addSpeedValuesOfferingDescription(RegistrableOfferingDescriptionChain offering) {
        DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime now = new DateTime();
        DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(speed_expireDate);
        long millis = expireDate.getMillis() - now.getMillis();
        return offering
                .withTimePeriod(new DateTime(2013, 1, 1, 0, 0, 0), new DateTime(2018,2,27,0,0,0))
                .inRegion(BoundingBox.create(Location.create(-70, -180), Location.create(90, 180)))
                .withCategory(SCHEMA_BIGIOT_SPEEDVALUE_CATEGORY)
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGINATION_PAGENUMBER), ValueType.NUMBER)
                // track components:

                // track measurements:
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .withExpirationInterval(new Duration(millis))
                .withPricingModel(BigIotTypes.PricingModel.FREE)
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                .withRoute(route_speed_measurements)
                .withAccessRequestHandler(getRequestHandler());
    }

}

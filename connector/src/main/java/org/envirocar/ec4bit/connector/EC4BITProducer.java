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

import org.eclipse.bigiot.lib.Provider;
import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescriptionChain;
import org.eclipse.bigiot.lib.query.elements.Region;
import org.eclipse.bigiot.lib.swagger.SwaggerGenerator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author dewall
 */
public abstract class EC4BITProducer implements InitializingBean, DisposableBean, Constants {

    @Value("${ec4bit.contact}")
    private String contact;
//    @Value("${bigiot.applications.track_data.route}")
//    private String route_tracks;
//    @Value("${bigiot.applications.single_track_data.route}")
//    private String route_track;
//    @Value("${bigiot.applications.driving_data.route}")
//    private String route_measurements;
//    @Value("${bigiot.applications.speed_data.route}")
//    private String route_speed_measurements;
    @Value("${bigiot.applications.segments_data.route}")
    private String route_segments;

    @Autowired
    protected Provider provider;
    protected RegistrableOfferingDescription offeringDescription;

    protected abstract RegistrableOfferingDescription getOfferingDescription();

    protected abstract AbstractRequestHandler getRequestHandler();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.offeringDescription = getOfferingDescription();
        this.provider.register(offeringDescription);
//        this.enableSwagger(offeringDescription);
    }

    @Override
    public void destroy() throws Exception {
        this.offeringDescription.deregister();
        this.offeringDescription.terminate();
    }

    protected void enableSwagger(RegistrableOfferingDescription description) {
        SwaggerGenerator.deploySwaggerFileFrom(offeringDescription, contact);
    }

    // Geojson OSM Segments Offering Description for Endpoint:  /segments
    protected RegistrableOfferingDescriptionChain addSegmentOfferingDescription(RegistrableOfferingDescriptionChain offering) {
        return offering
                .addInputData("sortBy", new RDFType(SCHEMA_SORT_BY), ValueType.TEXT)
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("featureID", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addInputData("intersects", new RDFType(SCHEMA_INTERSECT), ValueType.TEXT)
                .addInputData("dwithin", new RDFType(SCHEMA_DWITHIN), ValueType.TEXT)
                //     segment components:
                .addOutputData("OSMID", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addOutputData("geometry", new RDFType(SCHEMA_GEOMETRY), IOData.createMembers()
                        .addOutputData("LineString", new RDFType(SCHEMA_COORDINATES), ValueType.TEXT))
                //     segment measurements:
                .addOutputData("AvgSpeed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
                .addOutputData("NumSpeed", new RDFType(SCHEMA_AMOUNT), ValueType.NUMBER)
                
                .inRegion(Region.city("Muenster"))
                .withPricingModel(BigIotTypes.PricingModel.FREE)
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
                .withRoute(route_segments)
                .withAccessRequestHandler(getRequestHandler());
    }
//
//    // Tracks Offering Description for Endpoint:  /tracks
//    protected RegistrableOfferingDescriptionChain addTracksOfferingDescription(RegistrableOfferingDescriptionChain offering) {
//        return offering
//                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
//                .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
//                .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME)
//                .addInputData("page", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
//                //     track components:
//                .addOutputData("id", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("sensor", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
//                .addOutputData("length", new RDFType(SCHEMA_LENGTH), ValueType.NUMBER)
//                //     track measurements:
//                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.TEXT)
//                .addOutputData("co2", new RDFType(SCHEMA_CO2), ValueType.TEXT)
//                .addOutputData("consumption", new RDFType(SCHEMA_CONSUMPTION), ValueType.TEXT)
//                .addOutputData("maf", new RDFType(SCHEMA_MAF), ValueType.TEXT)
//                .addOutputData("GPS speed", new RDFType(SCHEMA_GPS_SPEED), ValueType.TEXT)
//                .addOutputData("GPS altitude", new RDFType(SCHEMA_GPS_ALTITUDE), ValueType.TEXT)
//                .addOutputData("Intake Temperature", new RDFType(SCHEMA_INTAKE_TEMPERATURE), ValueType.TEXT)
//                .addOutputData("Intake Pressure", new RDFType(SCHEMA_INTAKE_PRESSURE), ValueType.TEXT)
//                .addOutputData("rpm", new RDFType(SCHEMA_RPM), ValueType.TEXT)
//                .addOutputData("engine load", new RDFType(SCHEMA_ENGINE_LOAD), ValueType.TEXT)
//                .addOutputData("fuel system loop", new RDFType(SCHEMA_FUEL_SYSTEM_LOOP), ValueType.NUMBER)
//                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.NUMBER)
//                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
//                .addOutputData("GPS bearing", new RDFType(SCHEMA_GPS_BEARING), ValueType.TEXT)
//                .addOutputData("long term fuel Trim 1", new RDFType(SCHEMA_LONG_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("short term fuel Trim 1", new RDFType(SCHEMA_SHORT_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("throttle position", new RDFType(SCHEMA_THROTTLE_POSITION), ValueType.TEXT)
//                .addOutputData("GPS HDOP", new RDFType(SCHEMA_GPS_HDOP), ValueType.TEXT)
//                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
//                .addOutputData("GPS PDOP", new RDFType(SCHEMA_GPS_PDOP), ValueType.TEXT)
//                .addOutputData("calculated maf", new RDFType(SCHEMA_CALCULATED_MAF), ValueType.TEXT)
//                .addOutputData("o2 lambda current", new RDFType(SCHEMA_O2_LAMBDA_CURRENT), ValueType.TEXT)
//                .addOutputData("o2 lambda current ER", new RDFType(SCHEMA_O2_LAMBDA_CURRENT_ER), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage ER", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE_ER), ValueType.TEXT)
//                .inRegion(Region.city("Muenster"))
//                .withPricingModel(BigIotTypes.PricingModel.FREE)
//                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
//                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
//                .withRoute(route_tracks)
//                .withAccessRequestHandler(getRequestHandler());
//    }
//
//    // Track Offering Description for Endpoint:  /track
//    protected RegistrableOfferingDescriptionChain addTrackOfferingDescription(RegistrableOfferingDescriptionChain offering) {
//        return offering
//                .addInputData("track", new RDFType(SCHEMA_SINGLE_TRACK_ID), ValueType.TEXT)
//                // track components:
//                .addOutputData("trackID", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("trackRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
//                .addOutputData("sensorID", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("sensorRef", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
//                .addOutputData("length", new RDFType(SCHEMA_LENGTH), ValueType.NUMBER)
//                // measurement components:
//                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
//                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
//                .addOutputData("measurementID", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("measurementRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
//                .addOutputData("time", new RDFType(SCHEMA_TIMESTAMP), ValueType.TEXT)
//                .addOutputData("sensorID", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("sensorRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
//                .addOutputData("trackID", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("trackRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
//                // measurement phenomenons:
//                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
//                .addOutputData("co2", new RDFType(SCHEMA_CO2), ValueType.TEXT)
//                .addOutputData("consumption", new RDFType(SCHEMA_CONSUMPTION), ValueType.TEXT)
//                .addOutputData("maf", new RDFType(SCHEMA_MAF), ValueType.TEXT)
//                .addOutputData("GPS speed", new RDFType(SCHEMA_GPS_SPEED), ValueType.TEXT)
//                .addOutputData("GPS altitude", new RDFType(SCHEMA_GPS_ALTITUDE), ValueType.TEXT)
//                .addOutputData("Intake Temperature", new RDFType(SCHEMA_INTAKE_TEMPERATURE), ValueType.TEXT)
//                .addOutputData("Intake Pressure", new RDFType(SCHEMA_INTAKE_PRESSURE), ValueType.TEXT)
//                .addOutputData("rpm", new RDFType(SCHEMA_RPM), ValueType.TEXT)
//                .addOutputData("engine load", new RDFType(SCHEMA_ENGINE_LOAD), ValueType.TEXT)
//                .addOutputData("fuel system loop", new RDFType(SCHEMA_FUEL_SYSTEM_LOOP), ValueType.NUMBER)
//                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.NUMBER)
//                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
//                .addOutputData("GPS bearing", new RDFType(SCHEMA_GPS_BEARING), ValueType.TEXT)
//                .addOutputData("long term fuel Trim 1", new RDFType(SCHEMA_LONG_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("short term fuel Trim 1", new RDFType(SCHEMA_SHORT_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("throttle position", new RDFType(SCHEMA_THROTTLE_POSITION), ValueType.TEXT)
//                .addOutputData("GPS HDOP", new RDFType(SCHEMA_GPS_HDOP), ValueType.TEXT)
//                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
//                .addOutputData("GPS PDOP", new RDFType(SCHEMA_GPS_PDOP), ValueType.TEXT)
//                .addOutputData("calculated maf", new RDFType(SCHEMA_CALCULATED_MAF), ValueType.TEXT)
//                .addOutputData("o2 lambda current", new RDFType(SCHEMA_O2_LAMBDA_CURRENT), ValueType.TEXT)
//                .addOutputData("o2 lambda current ER", new RDFType(SCHEMA_O2_LAMBDA_CURRENT_ER), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage ER", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE_ER), ValueType.TEXT)
//                .inRegion(Region.city("Muenster"))
//                .withPricingModel(BigIotTypes.PricingModel.FREE)
//                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
//                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
//                .withRoute(route_track)
//                .withAccessRequestHandler(getRequestHandler());
//    }
//
//    // Measurements Offering Description for Endpoint:  /measurements
//    protected RegistrableOfferingDescriptionChain addMeasurementsOfferingDescription(RegistrableOfferingDescriptionChain offering) {
//        return offering
//                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
//                .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
//                .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME)
//                .addInputData("page", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
//                .addInputData("phenomenons", new RDFType(SCHEMA_PHENOMENONS), ValueType.TEXT)
//                // track components:
//                .addOutputData("id", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("sensor", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
//                .addOutputData("length", new RDFType(SCHEMA_LENGTH), ValueType.NUMBER)
//                // track measurements:
//                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
//                .addOutputData("co2", new RDFType(SCHEMA_CO2), ValueType.TEXT)
//                .addOutputData("consumption", new RDFType(SCHEMA_CONSUMPTION), ValueType.TEXT)
//                .addOutputData("maf", new RDFType(SCHEMA_MAF), ValueType.TEXT)
//                .addOutputData("GPS speed", new RDFType(SCHEMA_GPS_SPEED), ValueType.TEXT)
//                .addOutputData("GPS altitude", new RDFType(SCHEMA_GPS_ALTITUDE), ValueType.TEXT)
//                .addOutputData("Intake Temperature", new RDFType(SCHEMA_INTAKE_TEMPERATURE), ValueType.TEXT)
//                .addOutputData("Intake Pressure", new RDFType(SCHEMA_INTAKE_PRESSURE), ValueType.TEXT)
//                .addOutputData("rpm", new RDFType(SCHEMA_RPM), ValueType.TEXT)
//                .addOutputData("engine load", new RDFType(SCHEMA_ENGINE_LOAD), ValueType.TEXT)
//                .addOutputData("fuel system loop", new RDFType(SCHEMA_FUEL_SYSTEM_LOOP), ValueType.NUMBER)
//                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.NUMBER)
//                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
//                .addOutputData("GPS bearing", new RDFType(SCHEMA_GPS_BEARING), ValueType.TEXT)
//                .addOutputData("long term fuel Trim 1", new RDFType(SCHEMA_LONG_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("short term fuel Trim 1", new RDFType(SCHEMA_SHORT_TERM_FUEL_TRIM_1), ValueType.NUMBER)
//                .addOutputData("throttle position", new RDFType(SCHEMA_THROTTLE_POSITION), ValueType.TEXT)
//                .addOutputData("GPS HDOP", new RDFType(SCHEMA_GPS_HDOP), ValueType.TEXT)
//                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
//                .addOutputData("GPS PDOP", new RDFType(SCHEMA_GPS_PDOP), ValueType.TEXT)
//                .addOutputData("calculated maf", new RDFType(SCHEMA_CALCULATED_MAF), ValueType.TEXT)
//                .addOutputData("o2 lambda current", new RDFType(SCHEMA_O2_LAMBDA_CURRENT), ValueType.TEXT)
//                .addOutputData("o2 lambda current ER", new RDFType(SCHEMA_O2_LAMBDA_CURRENT_ER), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE), ValueType.TEXT)
//                .addOutputData("o2 lambda voltage ER", new RDFType(SCHEMA_O2_LAMBDA_VOLTAGE_ER), ValueType.TEXT)
//                .inRegion(Region.city("Muenster"))
//                .withPricingModel(BigIotTypes.PricingModel.FREE)
//                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
//                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
//                .withRoute(route_measurements)
//                .withAccessRequestHandler(getRequestHandler());
//    }
//
//    // Speed Values Offering Description for Endpoint:  /measurements {speed only}
//    protected RegistrableOfferingDescriptionChain addSpeedValuesOfferingDescription(RegistrableOfferingDescriptionChain offering) {
//        return offering
//                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
//                .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
//                .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME)
//                .addInputData("page", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
//                // track components:
//                .addOutputData("id", new RDFType(SCHEMA_ID), ValueType.TEXT)
//                .addOutputData("sensor", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
//                .addOutputData("length", new RDFType(SCHEMA_LENGTH), ValueType.NUMBER)
//                // track measurements:
//                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
//                .inRegion(Region.city("Muenster"))
//                .withPricingModel(BigIotTypes.PricingModel.FREE)
//                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE)
//                .withProtocol(BigIotTypes.EndpointType.HTTP_GET)
//                .withRoute(route_speed_measurements)
//                .withAccessRequestHandler(getRequestHandler());
//    }

}

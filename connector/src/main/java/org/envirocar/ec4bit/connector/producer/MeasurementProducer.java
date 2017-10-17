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
package org.envirocar.ec4bit.connector.producer;

import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.BigIotTypes.PricingModel;
import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.Information;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;
import org.eclipse.bigiot.lib.query.elements.Region;
import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.EC4BITProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class MeasurementProducer extends EC4BITProducer {

//    private static final String SCHEMA_BIGIOT_RDFTYPE = "bigiot:RawMeasurements";
    private static final String SCHEMA_BIGIOT_RDFTYPE = "bigiot:DrivingMeasurements";

    @Value("${bigiot.applications.driving_data.local_id}")
    private String localId;
    @Value("${bigiot.applications.driving_data.name}")
    private String name;
    @Value("${bigiot.applications.driving_data.route}")
    private String route;

    @Autowired
    private MeasurementRequestHandler requestHandler;

    /**
     *
     * @return
     */
    @Override
    protected RegistrableOfferingDescription getOfferingDescription() {
        return provider.createOfferingDescription(localId)
                .withInformation(new Information(name, new RDFType(SCHEMA_BIGIOT_RDFTYPE)))
                // not supported in the marketpalce, use simple inputdataelements instead.
                //                .addInputData("bbox", new RDFType(SCHEMA_BBOX), IOData.createMembers()
                //                        .addInputData("xMin", new RDFType(SCHEMA_BBOX_XMIN), ValueType.NUMBER)
                //                        .addInputData("yMin", new RDFType(SCHEMA_BBOX_YMIN), ValueType.NUMBER)
                //                        .addInputData("xMax", new RDFType(SCHEMA_BBOX_XMAX), ValueType.NUMBER)
                //                        .addInputData("yMax", new RDFType(SCHEMA_BBOX_YMAX), ValueType.NUMBER))
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME)
                //                .addInputData("during", new RDFType("bigiot:timeInterval"), IOData.createMembers()
                //                        .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
                //                        .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME))
                .addInputData("pageNumber", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
                //                .addInputData("page", new RDFType(SCHEMA_PAGE), IOData.createMembers()
                //                        .addInputData("pageNumber", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER))
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .addOutputData("id", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addOutputData("time", new RDFType(SCHEMA_TIMESTAMP), ValueType.TEXT)
                .addOutputData("sensor", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
                .addOutputData("track", new RDFType(SCHEMA_TRACK), ValueType.TEXT)
                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.TEXT)
                .addOutputData("co2", new RDFType(SCHEMA_CO2), ValueType.TEXT)
                .addOutputData("consumption", new RDFType(SCHEMA_CONSUMPTION), ValueType.TEXT)
                .addOutputData("maf", new RDFType(SCHEMA_MAF), ValueType.TEXT)
                .addOutputData("GPS speed", new RDFType(SCHEMA_GPS_SPEED), ValueType.TEXT)
                .addOutputData("GPS altitude", new RDFType(SCHEMA_GPS_ALTITUDE), ValueType.TEXT)
                .addOutputData("Intake Temperature", new RDFType(SCHEMA_INTAKE_TEMPERATURE), ValueType.TEXT)
                .addOutputData("Intake Pressure", new RDFType(SCHEMA_INTAKE_PRESSURE), ValueType.TEXT)
                .addOutputData("Rpm", new RDFType(SCHEMA_RPM), ValueType.TEXT)
                .addOutputData("GPS accuracy", new RDFType(SCHEMA_GPS_ACCURACY), ValueType.TEXT)
                .addOutputData("GPS HDOP", new RDFType(SCHEMA_GPS_HDOP), ValueType.TEXT)
                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
                .addOutputData("GPS PDOP", new RDFType(SCHEMA_GPS_PDOP), ValueType.TEXT)
                .addOutputData("GPS VDOP", new RDFType(SCHEMA_GPS_VDOP), ValueType.TEXT)
                //                .addOutputData("measurement", new RDFType("bigiot:DrivingMeasurement"), IOData.createMembers()
                //                        .addOutputData("geoCoordinates", new RDFType("bigiot:geoCoordinates"), IOData.createMembers()
                //                                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                //                                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)))
               

                .inRegion(Region.city("Muenster"))
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

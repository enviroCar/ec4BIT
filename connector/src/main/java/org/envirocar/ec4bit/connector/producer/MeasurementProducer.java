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
                // measurement filter options:
                // not supported in the marketpalce, use non-nested input data elements instead
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
                .addInputData("page", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
                .addInputData("phenomenons", new RDFType(SCHEMA_PHENOMENONS), ValueType.TEXT)
                //                .addInputData("page", new RDFType(SCHEMA_PAGE), IOData.createMembers()
                //                        .addInputData("pageNumber", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER))
                
                // measurement components:
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .addOutputData("measurementID", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addOutputData("measurementRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
                .addOutputData("time", new RDFType(SCHEMA_TIMESTAMP), ValueType.TEXT)
                .addOutputData("sensorID", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
                .addOutputData("sensorRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
                .addOutputData("trackID", new RDFType(SCHEMA_TRACK), ValueType.TEXT)
                .addOutputData("trackRef", new RDFType(SCHEMA_REF), ValueType.TEXT)
                // measurement phenomenons:
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
                .addOutputData("fuel system loop", new RDFType(SCHEMA_FUEL_SYSTEM_LOOP), ValueType.NUMBER)
                .addOutputData("fuel system status code", new RDFType(SCHEMA_FUEL_SYSTEM_STATUS_CODE), ValueType.NUMBER)
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

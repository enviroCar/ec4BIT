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
//@Component
public class MeasurementProducer extends EC4BITProducer {

    private static final String SCHEMA_BIGIOT_RDFTYPE = "bigiot:RawMeasurements";

    @Value("${bigiot.applications.measurementdata.local_id}")
    private String localId;
    @Value("${bigiot.applications.measurementdata.name}")
    private String name;
    @Value("${bigiot.applications.measurementdata.route}")
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
                .addInputData("bbox", new RDFType(SCHEMA_BBOX), IOData.createMembers()
                        .addInputData("xMin", new RDFType(SCHEMA_BBOX_XMIN), ValueType.NUMBER)
                        .addInputData("yMin", new RDFType(SCHEMA_BBOX_YMIN), ValueType.NUMBER)
                        .addInputData("xMax", new RDFType(SCHEMA_BBOX_XMAX), ValueType.NUMBER)
                        .addInputData("yMax", new RDFType(SCHEMA_BBOX_YMAX), ValueType.NUMBER))
                .addInputData("during", new RDFType("schema:timeInterval"), IOData.createMembers()
                        .addInputData("startDate", new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
                        .addInputData("endDate", new RDFType(SCHEMA_DURING_END), ValueType.DATETIME))
                .addInputData("page", new RDFType("schema:page"), ValueType.NUMBER)
                .addOutputData("measurement", new RDFType("schema:drivingMeasurement"), IOData.createMembers()
                        .addOutputData("geoCoordinates", new RDFType("schema:geoCoordinates"), IOData.createMembers()
                                .addOutputData("longitude", new RDFType("schema:longitude"), ValueType.NUMBER)
                                .addOutputData("latitude", new RDFType("schema:latitude"), ValueType.NUMBER)))
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
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
public class SegmentProducer extends EC4BITProducer {

    private static final String SCHEMA_BIGIOT_RDFTYPE = "bigiot:TrafficSegments";

    @Value("${bigiot.applications.segments_data.local_id}")
    private String localId;
    @Value("${bigiot.applications.segments_data.name}")
    private String name;
    @Value("${bigiot.applications.segments_data.route}")
    private String route;

    @Autowired
    private SegmentRequestHandler requestHandler;

    /**
     *
     * @return
     */
    @Override
    protected RegistrableOfferingDescription getOfferingDescription() {
        return provider.createOfferingDescription(localId)
                .withInformation(new Information(name, new RDFType(SCHEMA_BIGIOT_RDFTYPE)))
                .addInputData("sortBy", new RDFType(SCHEMA_SORT_BY), ValueType.TEXT)
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("featureID", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addInputData("intersects", new RDFType(SCHEMA_INTERSECT), ValueType.TEXT)
                .addInputData("within", new RDFType(SCHEMA_DWITHIN), ValueType.TEXT)
                .addInputData("dwithin", new RDFType(SCHEMA_DWITHIN), ValueType.TEXT)
                .addInputData("greaterThan", new RDFType(SCHEMA_GREATER_THAN), ValueType.TEXT)
                .addInputData("lessThan", new RDFType(SCHEMA_LESS_THAN), ValueType.TEXT)
                .addInputData("betweenIn", new RDFType(SCHEMA_LESS_THAN), ValueType.TEXT)
                // segment components:
                .addOutputData("OSMID", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addOutputData("geometry", new RDFType(SCHEMA_GEOMETRY), IOData.createMembers()
                        .addOutputData("LineString", new RDFType(SCHEMA_COORDINATES), ValueType.TEXT))
                // segment measurements:
                .addOutputData("AvgSpeed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
                .addOutputData("NumSpeed", new RDFType(SCHEMA_AMOUNT), ValueType.NUMBER)
                .inRegion(Region.city("Muenster"))
                .withPricingModel(BigIotTypes.PricingModel.FREE)
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

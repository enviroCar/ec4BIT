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
import org.eclipse.bigiot.lib.model.Information;
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

    private static final String SCHEMA_BIGIOT_RDFTYPE = "bigiot:DrivingTracks";

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
                .withInformation(new Information(name, new RDFType(SCHEMA_BIGIOT_RDFTYPE)))
                // track filter options:
                .addInputData("box", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGE_NUMBER), ValueType.NUMBER)
                .addInputData("trackID", new RDFType(SCHEMA_SINGLE_TRACK_ID), ValueType.TEXT)
                // track components:
                .addOutputData("id", new RDFType(SCHEMA_ID), ValueType.TEXT)
                .addOutputData("sensor", new RDFType(SCHEMA_SENSOR), ValueType.TEXT)
                .addOutputData("length", new RDFType(SCHEMA_LENGTH), ValueType.NUMBER)
                
                .withExpirationInterval(Duration.standardDays(millis))
                
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

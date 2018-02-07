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

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.bigiot.lib.exceptions.InvalidOfferingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.BigIotTypes.PricingModel;
import org.eclipse.bigiot.lib.model.BoundingBox;
import org.eclipse.bigiot.lib.model.Location;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;

import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.connector.EC4BITProducer;
import org.joda.time.DateTime;

import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Arne de Wall <a.dewall@52north.org>
 */
@Component
public class SpeedDataProducer extends EC4BITProducer {

    private static final String SCHEMA_BIGIOT_SPEEDVALUE_CATEGORY = "urn:proposed:Traffic_Speed";

    @Value("${bigiot.applications.speedvalues.local_id}")
    private String localId;
    @Value("${bigiot.applications.speedvalues.name}")
    private String name;
    @Value("${bigiot.applications.speedvalues.route}")
    private String route;
    @Value("${bigiot.applications.speedvalues.expireDate}")
    private String expires;

    @Autowired
    private SpeedRequestHandler requestHandler;

    @Override
    protected RegistrableOfferingDescription getOfferingDescription() {
        DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTime now = new DateTime();
        DateTime expireDate = TEMPORAL_TIME_PATTERN.parseDateTime(expires);
        long millis = expireDate.getMillis() - now.getMillis();
        return provider.createOfferingDescription(localId)
                .withName(name)
                .withCategory(SCHEMA_BIGIOT_SPEEDVALUE_CATEGORY)
                .withTimePeriod(new DateTime(2013, 1, 1, 0, 0, 0), new DateTime(2018,2,8,0,0,0))
                .inRegion(BoundingBox.create(Location.create(-70, -180), Location.create(90, 180)))
                .addInputData("bbox", new RDFType(SCHEMA_BBOX), ValueType.TEXT)
                .addInputData("startDate", new RDFType(SCHEMA_START_DATE), ValueType.DATETIME)
                .addInputData("endDate", new RDFType(SCHEMA_END_DATE), ValueType.DATETIME)
                .addInputData("page", new RDFType(SCHEMA_PAGINATION_PAGENUMBER), ValueType.NUMBER)
                .addOutputData("longitude", new RDFType(SCHEMA_LONGITUDE), ValueType.NUMBER)
                .addOutputData("latitude", new RDFType(SCHEMA_LATITUDE), ValueType.NUMBER)
                .addOutputData("speed", new RDFType(SCHEMA_SPEED), ValueType.NUMBER)
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

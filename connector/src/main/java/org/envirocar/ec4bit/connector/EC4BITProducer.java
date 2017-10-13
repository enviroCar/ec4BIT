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
import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.RDFType;
import org.eclipse.bigiot.lib.model.ValueType;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescription;
import org.eclipse.bigiot.lib.offering.RegistrableOfferingDescriptionChain;
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

    protected RegistrableOfferingDescriptionChain addBboxInput(RegistrableOfferingDescriptionChain offering) {
        return offering
                .addInputData(BBOX, new RDFType(SCHEMA_BBOX), IOData.createMembers()
                        .addInputData(BBOX_XMIN, new RDFType(SCHEMA_BBOX_XMIN), ValueType.NUMBER)
                        .addInputData(BBOX_YMIN, new RDFType(SCHEMA_BBOX_YMIN), ValueType.NUMBER)
                        .addInputData(BBOX_XMAX, new RDFType(SCHEMA_BBOX_XMAX), ValueType.NUMBER)
                        .addInputData(BBOX_YMAX, new RDFType(SCHEMA_BBOX_YMAX), ValueType.NUMBER))
                .addInputData(DURING, new RDFType("schema:timeInterval"), IOData.createMembers()
                        .addInputData(DURING_START, new RDFType(SCHEMA_DURING_START), ValueType.DATETIME)
                        .addInputData(DURING_END, new RDFType(SCHEMA_DURING_END), ValueType.DATETIME))
                .addInputData(PAGE, new RDFType("schema:page"), ValueType.NUMBER);
    }
}

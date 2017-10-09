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

import java.util.List;
import java.util.Map;
import org.eclipse.bigiot.lib.offering.OfferingDescription;
import org.eclipse.bigiot.lib.serverwrapper.BigIotHttpResponse;
import org.envirocar.ec4bit.connector.AbstractRequestHandler;
import org.envirocar.ec4bit.core.model.SpeedValue;
import org.envirocar.ec4bit.core.remote.SpeedDataDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author hafenkran
 */
@Component
public class SpeedRequestHandler extends AbstractRequestHandler {

    @Autowired
    private SpeedDataDAO speedDataDAO;

    @Override
    public BigIotHttpResponse processRequestHandler(OfferingDescription od, Map<String, Object> input) {
        try {
            double[] bbox = getBoundingBoxParams(input);
            List<SpeedValue> entities = speedDataDAO.getEntities(bbox);

            return BigIotHttpResponse.okay()
                    //                    .withBody(blockingGet.get(0).string())
                    .asJsonType();
        } catch (Exception ex) {
            return BigIotHttpResponse.error()
                    .withBody("{\"status\":\"error\"}")
                    .withStatus(422)
                    .asJsonType();
        }
    }

}

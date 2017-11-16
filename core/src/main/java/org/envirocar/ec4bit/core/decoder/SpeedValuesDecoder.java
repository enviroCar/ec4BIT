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
package org.envirocar.ec4bit.core.decoder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import org.envirocar.ec4bit.core.model.SpeedValue;
import org.envirocar.ec4bit.core.model.SpeedValues;

/**
 *
 * @author hafenkran
 */
public class SpeedValuesDecoder extends BaseDeserializer<SpeedValues> {

    private static final String ELEMENT_FEATURES = "features";
    private static final String ELEMENT_GEOMETRY = "geometry";
    private static final String ELEMENT_COORDINATES = "coordinates";
    private static final String ELEMENT_PROPERTIES = "properties";
    private static final String ELEMENT_PHENOMENONS = "phenomenons";
    private static final String ELEMENT_SPEED = "Speed";
    private static final String ELEMENT_VALUE = "value";

    @Override
    public SpeedValues deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        SpeedValues results = new SpeedValues();

        JsonNode node = jp.readValueAsTree();
        ArrayNode features = (ArrayNode) node.get(ELEMENT_FEATURES);

        features.forEach((m) -> {
            SpeedValue result = new SpeedValue();

            // parse the location
            ArrayNode geometry = (ArrayNode) m
                    .get(ELEMENT_GEOMETRY)
                    .get(ELEMENT_COORDINATES);
            double longitude = geometry.get(0).asDouble();
            double latitude = geometry.get(1).asDouble();

            // parse the speed phenomenon
            int speed = m.get(ELEMENT_PROPERTIES)
                    .get(ELEMENT_PHENOMENONS)
                    .get(ELEMENT_SPEED)
                    .get(ELEMENT_VALUE)
                    .asInt();

            result.setLongitude(longitude);
            result.setLatitude(latitude);
            result.setSpeed(speed);

            results.addSpeedValue(result);
        });

        return results;
    }

}

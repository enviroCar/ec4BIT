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
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.model.Track;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class TrackDecoder extends BaseDeserializer<Track> {

    private static final DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final String ELEMENT_PROPERTIES = "properties";
    private static final String ELEMENT_ID = "id";
    private static final String ELEMENT_SENSOR = "sensor";
    private static final String ELEMENT_LENGTH = "length";
    private static final String ELEMENT_FEATURES = "features";
    
    @Override
    public Track deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.readValueAsTree();
        Track result = new Track();
        
        // parse id, sensor, and length:
        JsonNode properties = node.get(ELEMENT_PROPERTIES);

        String id = properties.get(ELEMENT_ID).asText();
        Double length = properties.get(ELEMENT_LENGTH).asDouble();
        String sensor = "https://envirocar.org/api/stable/sensors/"
                + properties
                        .get(ELEMENT_SENSOR)
                        .get(ELEMENT_PROPERTIES)
                        .get(ELEMENT_ID)
                        .asText();

        // parse Measurements:
        MeasurementsDecoder md = new MeasurementsDecoder();
        Measurements measurements = md.deserialize(jp, dc);
      
        result.setSensor(sensor);
        result.setTrackID(id);
        result.setLength(length);
        result.setMeasurements(measurements.getMeasurements());

        return result;
    }

}
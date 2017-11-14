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
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class TracksDecoder extends BaseDeserializer<Tracks> {

    private static final String ELEMENT_PROPERTIES = "properties";
    private static final String ELEMENT_ID = "id";
    private static final String ELEMENT_SENSOR = "sensor";
    private static final String ELEMENT_LENGTH = "length";

    @Override
    public Tracks deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Tracks results = new Tracks();

        JsonNode node = jp.readValueAsTree();
        ArrayNode features = (ArrayNode) node.get("tracks");

        features.forEach((m) -> {
            Track track = new Track();

            String id = m.get(ELEMENT_ID).asText();
            
            Double length = m.get(ELEMENT_LENGTH).asDouble();
            String sensor = m
                            .get(ELEMENT_SENSOR)
                            .get(ELEMENT_PROPERTIES)
                            .get(ELEMENT_ID)
                            .asText();

            track.setSensor(sensor);
            track.setTrackID(id);
            track.setLength(length);

            results.addTrack(track);
        });

        return results;
    }

}

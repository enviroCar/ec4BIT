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
import java.util.ArrayList;
import org.envirocar.ec4bit.core.model.Segment;
import org.envirocar.ec4bit.core.model.CoordinatePair;
import org.envirocar.ec4bit.core.model.Segments;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class SegmentsDecoder extends BaseDeserializer<Segments> {

    private static final String ELEMENT_FEATURES = "features";
    private static final String ELEMENT_ID = "id";
    private static final String ELEMENT_GEOMETRY = "geometry";
    private static final String ELEMENT_COORDINATES = "coordinates";
    private static final String ELEMENT_PROPERTIES = "properties";
    private static final String ELEMENT_OSMID = "OSMID";
    private static final String ELEMENT_AVGSPEED = "AvgSpeed";
    private static final String ELEMENT_NUMSPEED = "NumSpeed";

    @Override
    public Segments deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Segments results = new Segments();

        JsonNode node = jp.readValueAsTree();
        ArrayNode features = (ArrayNode) node.get(ELEMENT_FEATURES);

        features.forEach((m) -> {
            Segment segment = new Segment();

            String id = m.get(ELEMENT_ID).asText();

            ArrayNode coordinates = (ArrayNode) m.get(ELEMENT_GEOMETRY).get(ELEMENT_COORDINATES);

            ArrayList<CoordinatePair> coords = new ArrayList<>();
            
            coordinates.get(0).forEach((coord) -> {
                CoordinatePair cp = new CoordinatePair(
                        coord.get(0).asDouble(),
                        coord.get(1).asDouble()
                );
                coords.add(cp);
            });

            JsonNode properties =  m.get(ELEMENT_PROPERTIES);
            int OSMID = properties.get(ELEMENT_OSMID).asInt();
            double avgSpeed = properties.get(ELEMENT_AVGSPEED).asDouble();
            double numSpeed = properties.get(ELEMENT_NUMSPEED).asDouble();

            segment.setSegmentID(id);
            segment.setPoints(coords);
            segment.setAvgSpeed(avgSpeed);
            segment.setNumSpeed(numSpeed);
            segment.setOsmID(OSMID);

            results.addSegment(segment);
        });

        return results;
    }

}

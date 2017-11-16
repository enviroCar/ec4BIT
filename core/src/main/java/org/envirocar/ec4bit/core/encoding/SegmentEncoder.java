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
package org.envirocar.ec4bit.core.encoding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.List;
import org.envirocar.ec4bit.core.model.CoordinatePair;
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Segment;
import org.envirocar.ec4bit.core.model.Track;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class SegmentEncoder extends BaseJSONEncoder<Segment> {

    @Override
    public void serialize(Segment segment, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeStartObject();
        gen.writeObjectField("segmentID", segment.getSegmentID());
        gen.writeObjectField("AvgSpeed", segment.getAvgSpeed());
        gen.writeObjectField("NumSpeed", segment.getNumSpeed());
        gen.writeObjectField("OSMID", segment.getOsmID());

        List<CoordinatePair> points = segment.getPoints();
        if (!points.isEmpty()) {
            writeArrayOfObjects(gen, "Coordinates", points);
        }

        gen.writeEndObject();
    }

}
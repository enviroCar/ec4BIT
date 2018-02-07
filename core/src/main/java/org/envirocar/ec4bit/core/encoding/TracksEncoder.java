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
package org.envirocar.ec4bit.core.encoding;


import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import java.util.List;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class TracksEncoder extends BaseJSONEncoder<Tracks> {

    @Override
    public void serialize(Tracks tracks, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeStartObject();

        if (tracks.getTracks().size() != 1) {
            writeArrayOfObjects(gen, "Tracks", tracks.getTracks());
        } else {
            Track track = tracks.getTracks().get(0);
            gen.writeObjectField("trackID", track.getTrackID());
            gen.writeObjectField("trackRef", "https://processing.envirocar.org:8081/bigiot/access/tracks?trackID=" + track.getTrackID());
            gen.writeObjectField("sensorID", track.getSensor());
            gen.writeObjectField("sensorRef", "http://envirocar.org/api/stable/sensors/" + track.getSensor());
            gen.writeObjectField("length", track.getLength());

            List<Measurement> measurements = track.getMeasurements();
            if (!measurements.isEmpty()) {
                writeArrayOfObjects(gen, "Measurements", measurements);
            }
        }

        gen.writeEndObject();
    }

}
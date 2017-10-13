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
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.model.Measurement;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author hafenkran
 */
public class MeasurementsDecoder extends BaseDeserializer<Measurements> {

    private static final DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final String ELEMENT_FEATURES = "features";
    private static final String ELEMENT_GEOMETRY = "geometry";
    private static final String ELEMENT_COORDINATES = "coordinates";
    private static final String ELEMENT_PROPERTIES = "properties";
    private static final String ELEMENT_PHENOMENONS = "phenomenons";
    private static final String ELEMENT_ID = "id";
    private static final String ELEMENT_LONGITUDE = "longitude";
    private static final String ELEMENT_LATITUDE = "latitude";
    private static final String ELEMENT_TIME = "time";
    private static final String ELEMENT_TRACK = "track";
    private static final String ELEMENT_SENSOR = "sensor";

    private static final String ELEMENT_SPEED = "Speed";
    private static final String ELEMENT_CO2 = "CO2";
    private static final String ELEMENT_CONSUMPTION = "Consumption";
    private static final String ELEMENT_GPS_SPEED = "GPS Speed";
    private static final String ELEMENT_GPS_ALTITUDE = "GPS Altitude";
    private static final String ELEMENT_MAF = "MAF";
    private static final String ELEMENT_INTAKE_TEMP = "Intake Temperature";
    private static final String ELEMENT_INTAKE_PRESSURE = "Intake Pressure";
    private static final String ELEMENT_RPM = "Rpm";
    private static final String ELEMENT_ENGINE_LOAD = "Engine Load";

    @Override
    public Measurements deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        Measurements results = new Measurements();

        JsonNode node = jp.readValueAsTree();
        ArrayNode features = (ArrayNode) node.get(ELEMENT_FEATURES);

        // TODO: PhenomenonDefinitions um Units erweitern
        
        features.forEach((m) -> {
            Measurement result = new Measurement();

            // parse the location
            ArrayNode geometry = (ArrayNode) node
                    .get(ELEMENT_GEOMETRY)
                    .get(ELEMENT_COORDINATES);
            Double longitude = geometry.get(0).asDouble();
            Double latitude = geometry.get(1).asDouble();

            // parse id, time, track, and sensor
            ArrayNode properties = (ArrayNode) node
                    .get(ELEMENT_PROPERTIES);

            String id = properties.get(ELEMENT_ID).asText();
            String timeStr = properties.get(ELEMENT_TIME).asText();
            DateTime time = TEMPORAL_TIME_PATTERN.parseDateTime(timeStr);
            String track = properties.get(ELEMENT_TRACK).asText();
            String sensor = "https://envirocar.org/api/stable/sensors/"
                    + properties
                            .get(ELEMENT_SENSOR)
                            .get(ELEMENT_PROPERTIES)
                            .get(ELEMENT_ID)
                            .asText();

            // parse the phenomenons:
            ArrayNode phenomenons = (ArrayNode) properties
                    .get(ELEMENT_PHENOMENONS);

            Double speed = phenomenons.get(ELEMENT_SPEED).asDouble();
            Double co2 = phenomenons.get(ELEMENT_CO2).asDouble();
            Double consumption = phenomenons.get(ELEMENT_CONSUMPTION).asDouble();
            Double gps_speed = phenomenons.get(ELEMENT_GPS_SPEED).asDouble();
            Double gps_alt = phenomenons.get(ELEMENT_GPS_ALTITUDE).asDouble();
            Double maf = phenomenons.get(ELEMENT_MAF).asDouble();
            Double intake_temp = phenomenons.get(ELEMENT_INTAKE_TEMP).asDouble();
            Double intake_press = phenomenons.get(ELEMENT_INTAKE_PRESSURE).asDouble();
            Integer rpm = phenomenons.get(ELEMENT_RPM).asInt();
            Integer engine_load = phenomenons.get(ELEMENT_ENGINE_LOAD).asInt();

            result.setMeasurementID(id);
            result.setSensor(sensor);
            result.setTrackID(track);
            result.setTime(time);

            result.setLongitude(longitude);
            result.setLatitude(latitude);

            result.setSpeed(speed);
            result.setCo2(co2);
            result.setConsumption(consumption);
            result.setGps_speed(gps_speed);
            result.setGps_altitude(gps_alt);
            result.setMaf(maf);
            result.setIntake_temp(intake_temp);
            result.setIntake_pressure(intake_press);
            result.setRpm(rpm);
            result.setEngine_load(engine_load);

            results.addMeasurement(result);
        });

        return results;
    }

}

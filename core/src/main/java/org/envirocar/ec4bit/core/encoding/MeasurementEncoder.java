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
import org.envirocar.ec4bit.core.model.Measurement;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class MeasurementEncoder extends BaseJSONEncoder<Measurement> {

    @Override
    public void serialize(Measurement value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("id", value.getMeasurementID());
        gen.writeObjectField("longitude", value.getLongitude());
        gen.writeObjectField("latitude", value.getLatitude());
        gen.writeObjectField("time", value.getTime());
        gen.writeObjectField("track", value.getTrackID());
        gen.writeObjectField("sensor", value.getSensor());
        if (value.getSpeed() != null) {
            gen.writeObjectField("Speed", value.getSpeed());
        }
        if (value.getCo2() != null) {
            gen.writeObjectField("CO2", value.getCo2());
        }
        if (value.getConsumption() != null) {
            gen.writeObjectField("Consumption", value.getConsumption());
        }
        if (value.getGps_speed() != null) {
            gen.writeObjectField("GPS Speed", value.getGps_speed());
        }
        if (value.getGps_altitude() != null) {
            gen.writeObjectField("GPS Altitude", value.getGps_altitude());
        }
        if (value.getMaf() != null) {
            gen.writeObjectField("MAF", value.getMaf() );
        }
        if (value.getIntake_pressure() != null) {
            gen.writeObjectField("Intake Pressure", value.getIntake_pressure() );
        }
        if (value.getIntake_temp() != null) {
            gen.writeObjectField("Intake Temperature", value.getIntake_temp() );
        }
        if (value.getRpm() != null) {
            gen.writeObjectField("Rpm", value.getRpm() );
        }
        gen.writeEndObject();
    }

}

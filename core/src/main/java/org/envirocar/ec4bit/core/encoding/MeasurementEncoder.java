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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class MeasurementEncoder extends BaseJSONEncoder<Measurement> {

    private static final DateTimeFormatter TEMPORAL_TIME_PATTERN = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z");

    @Override
    public void serialize(Measurement value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {

        gen.writeStartObject();
        gen.writeObjectField("measurementID", value.getMeasurementID());
        gen.writeObjectField("measurementRef", "https://processing.envirocar.org:8081/bigiot/access/measurements?measurementID=" + value.getMeasurementID());
        gen.writeObjectField("longitude", value.getLongitude());
        gen.writeObjectField("latitude", value.getLatitude());
        // get TimeStamp String:
        String time = value.getTime().toString(TEMPORAL_TIME_PATTERN);

        gen.writeObjectField("time", time);

        if (value.getTrackID() != null) {
            gen.writeObjectField("trackID", value.getTrackID());
            gen.writeObjectField("trackRef", "https://processing.envirocar.org:8081/bigiot/access/tracks?trackID=" + value.getTrackID());
        }
        if (value.getSensorID() != null) {
            gen.writeObjectField("sensorID", value.getSensorID());
            gen.writeObjectField("sensorRef", "http://envirocar.org/api/stable/sensors/" + value.getSensorID());
        }
        if (value.getSpeed() != null) {
            gen.writeObjectField("Speed", value.getSpeed());
        }
        if (value.getCo2() != null) {
            gen.writeObjectField("CO2", value.getCo2());
        }
        if (value.getConsumption() != null) {
            gen.writeObjectField("Consumption", value.getConsumption());
        }
        if (value.getEngine_load() != null) {
            gen.writeObjectField("Engine Load", value.getEngine_load());
        }
        if (value.getGps_speed() != null) {
            gen.writeObjectField("GPS Speed", value.getGps_speed());
        }
        if (value.getGps_altitude() != null) {
            gen.writeObjectField("GPS Altitude", value.getGps_altitude());
        }
        if (value.getMaf() != null) {
            gen.writeObjectField("MAF", value.getMaf());
        }
        if (value.getIntake_pressure() != null) {
            gen.writeObjectField("Intake Pressure", value.getIntake_pressure());
        }
        if (value.getIntake_temp() != null) {
            gen.writeObjectField("Intake Temperature", value.getIntake_temp());
        }
        if (value.getRpm() != null) {
            gen.writeObjectField("Rpm", value.getRpm());
        }
        if (value.getFuel_system_loop() != null) {
            gen.writeObjectField("Fuel System Loop", value.getFuel_system_loop());
        }
        if (value.getFuel_system_status_code() != null) {
            gen.writeObjectField("Fuel System Status Code", value.getFuel_system_status_code());
        }
        if (value.getGps_accuracy() != null) {
            gen.writeObjectField("GPS Accuracy", value.getGps_accuracy());
        }
        if (value.getGps_bearing() != null) {
            gen.writeObjectField("GPS Bearing", value.getGps_bearing());
        }
        if (value.getLong_term_fuel_trim_1() != null) {
            gen.writeObjectField("Long-Term Fuel Trim 1", value.getLong_term_fuel_trim_1());
        }
        if (value.getShort_term_fuel_trim_1() != null) {
            gen.writeObjectField("Short-Term Fuel Trim 1", value.getShort_term_fuel_trim_1());
        }
        if (value.getThrottle_position() != null) {
            gen.writeObjectField("Throttle Position", value.getThrottle_position());
        }
        if (value.getGps_hdop() != null) {
            gen.writeObjectField("GPS HDOP", value.getGps_hdop());
        }
        if (value.getGps_vdop() != null) {
            gen.writeObjectField("GPS VDOP", value.getGps_vdop());
        }
        if (value.getGps_pdop() != null) {
            gen.writeObjectField("GPS PDOP", value.getGps_pdop());
        }
        if (value.getCalculated_maf() != null) {
            gen.writeObjectField("Calculated MAF", value.getCalculated_maf());
        }
        if (value.getO2_lambda_current() != null) {
            gen.writeObjectField("O2 Lambda Current", value.getO2_lambda_current());
        }
        if (value.getO2_lambda_current_ER() != null) {
            gen.writeObjectField("O2 Lambda Current ER", value.getO2_lambda_current_ER());
        }
        if (value.getO2_lambda_voltage() != null) {
            gen.writeObjectField("O2 Lambda Voltage", value.getO2_lambda_voltage());
        }
        if (value.getO2_lambda_voltage_ER() != null) {
            gen.writeObjectField("O2 Lambda Voltage ER", value.getO2_lambda_voltage_ER());
        }

        gen.writeEndObject();
    }

}
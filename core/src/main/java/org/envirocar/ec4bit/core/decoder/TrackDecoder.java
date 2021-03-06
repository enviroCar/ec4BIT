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
package org.envirocar.ec4bit.core.decoder;


import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.model.Track;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

import org.joda.time.DateTime;

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
    
    private static final String ELEMENT_PHENOMENONS = "phenomenons";
    private static final String ELEMENT_TIME = "time";
    private static final String ELEMENT_TRACK = "track";
    private static final String ELEMENT_GEOMETRY = "geometry";
    private static final String ELEMENT_COORDINATES = "coordinates";
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
    private static final String ELEMENT_FUEL_SYSTEM_STATUS_CODE = "Fuel System Status Code";
    private static final String ELEMENT_GPS_ACCURACY = "GPS Accuracy";
    private static final String ELEMENT_GPS_BEARING = "GPS Bearing";
    private static final String ELEMENT_LONG_TERM_FUEL_TRIM_1 = "Long-Term Fuel Trim 1";
    private static final String ELEMENT_SHORT_TERM_FUEL_TRIM_1 = "Short-Term Fuel Trim 1";
    private static final String ELEMENT_THROTTLE_POSITION = "Throttle Position";
    private static final String ELEMENT_GPS_HDOP = "GPS HDOP";
    private static final String ELEMENT_GPS_VDOP = "GPS VDOP";
    private static final String ELEMENT_GPS_PDOP = "GPS PDOP";
    private static final String ELEMENT_CALCULATED_MAF = "Calculated MAF";
    private static final String ELEMENT_O2_LAMBDA_CURRENT = "O2 Lambda Current";
    private static final String ELEMENT_O2_LAMBDA_CURRENT_ER = "O2 Lambda Current ER";
    private static final String ELEMENT_O2_LAMBDA_VOLTAGE = "O2 Lambda Voltage";
    private static final String ELEMENT_O2_LAMBDA_VOLTAGE_ER = "O2 Lambda Voltage ER";
    
    private static final String ELEMENT_VALUE = "value";
    
    private static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public Track deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        JsonNode node = jp.readValueAsTree();
        Track result = new Track();

        // parse id, sensor, and length:
        JsonNode properties = node.get(ELEMENT_PROPERTIES);

        String trackID = properties.get(ELEMENT_ID).asText();
        Double length = round( properties.get(ELEMENT_LENGTH).asDouble(), 3);
        String sensorID = properties
                        .get(ELEMENT_SENSOR)
                        .get(ELEMENT_PROPERTIES)
                        .get(ELEMENT_ID)
                        .asText();

        // parse Measurements:
        Measurements results = new Measurements();
        ArrayNode features = (ArrayNode) node.get(ELEMENT_FEATURES);

        // TODO: PhenomenonDefinitions um Units erweitern
        features.forEach((m) -> {
            Measurement measurement = new Measurement();

            // parse the location
            ArrayNode geometry = (ArrayNode) m
                    .get(ELEMENT_GEOMETRY)
                    .get(ELEMENT_COORDINATES);
            Double longitude = geometry.get(0).asDouble();
            Double latitude = geometry.get(1).asDouble();

            // parse id, time, track, and sensor
            JsonNode measurement_properties = m.get(ELEMENT_PROPERTIES);

            String measurement_id = measurement_properties.get(ELEMENT_ID).asText();
            String timeStr = measurement_properties.get(ELEMENT_TIME).asText();
            timeStr = timeStr.substring(0, timeStr.length() - 1);
            DateTime time = TEMPORAL_TIME_PATTERN.parseDateTime(timeStr);
            if (measurement_properties.get(ELEMENT_TRACK) != null) {
                String mTrackID = measurement_properties.get(ELEMENT_TRACK).asText();
                measurement.setTrackID(mTrackID);
            }
            if (measurement_properties.get(ELEMENT_SENSOR) != null) {
                String mSensorID = measurement_properties
                                .get(ELEMENT_SENSOR)
                                .get(ELEMENT_PROPERTIES)
                                .get(ELEMENT_ID)
                                .asText();
                measurement.setSensorID(mSensorID);
            }

            // parse the phenomenons:
            JsonNode phenomenons = measurement_properties
                    .get(ELEMENT_PHENOMENONS);

            Integer speed = phenomenons.get(ELEMENT_SPEED).get(ELEMENT_VALUE).asInt();
            if (phenomenons.get(ELEMENT_CO2) != null) {
                Double co2 = round( phenomenons.get(ELEMENT_CO2).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setCo2(co2);
            }
            if (phenomenons.get(ELEMENT_CONSUMPTION) != null) {
                Double consumption = round( phenomenons.get(ELEMENT_CONSUMPTION).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setConsumption(consumption);
            }
            if (phenomenons.get(ELEMENT_GPS_SPEED) != null) {
                Double gps_speed = round( phenomenons.get(ELEMENT_GPS_SPEED).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setGps_speed(gps_speed);
            }
            if (phenomenons.get(ELEMENT_GPS_ALTITUDE) != null) {
                Double gps_alt = round(  phenomenons.get(ELEMENT_GPS_ALTITUDE).get(ELEMENT_VALUE).asDouble(), 7);
                measurement.setGps_altitude(gps_alt);
            }
            if (phenomenons.get(ELEMENT_MAF) != null) {
                Double maf =round( phenomenons.get(ELEMENT_MAF).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setMaf(maf);
            }
            if (phenomenons.get(ELEMENT_INTAKE_TEMP) != null) {
                Double intake_temp = phenomenons.get(ELEMENT_INTAKE_TEMP).get(ELEMENT_VALUE).asDouble();
                measurement.setIntake_temp(intake_temp);
            }
            if (phenomenons.get(ELEMENT_INTAKE_PRESSURE) != null) {
                Double intake_press = round( phenomenons.get(ELEMENT_INTAKE_PRESSURE).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setIntake_pressure(intake_press);
            }
            if (phenomenons.get(ELEMENT_RPM) != null) {
                Integer rpm = phenomenons.get(ELEMENT_RPM).get(ELEMENT_VALUE).asInt();
                measurement.setRpm(rpm);
            }
            if (phenomenons.get(ELEMENT_ENGINE_LOAD) != null) {
                Integer engine_load = phenomenons.get(ELEMENT_ENGINE_LOAD).get(ELEMENT_VALUE).asInt();
                measurement.setEngine_load(engine_load);
            }
            if (phenomenons.get(ELEMENT_FUEL_SYSTEM_STATUS_CODE) != null) {
                String fuel_system_status_code = phenomenons.get(ELEMENT_FUEL_SYSTEM_STATUS_CODE).get(ELEMENT_VALUE).asText();
                measurement.setFuel_system_status_code(fuel_system_status_code);
            }
            if (phenomenons.get(ELEMENT_GPS_ACCURACY) != null) {
                Double gps_accuracy = round( phenomenons.get(ELEMENT_GPS_ACCURACY).get(ELEMENT_VALUE).asDouble(), 7);
                measurement.setGps_accuracy(gps_accuracy);
            }
            if (phenomenons.get(ELEMENT_GPS_BEARING) != null) {
                Double gps_bearing = round( phenomenons.get(ELEMENT_GPS_BEARING).get(ELEMENT_VALUE).asDouble(), 2);
                measurement.setGps_bearing(gps_bearing);
            }
            if (phenomenons.get(ELEMENT_LONG_TERM_FUEL_TRIM_1) != null) {
                Integer long_term_fuel_trim = phenomenons.get(ELEMENT_LONG_TERM_FUEL_TRIM_1).get(ELEMENT_VALUE).asInt();
                measurement.setLong_term_fuel_trim_1(long_term_fuel_trim);
            }
            if (phenomenons.get(ELEMENT_SHORT_TERM_FUEL_TRIM_1) != null) {
                Integer short_term_fuel_trim_1 = phenomenons.get(ELEMENT_SHORT_TERM_FUEL_TRIM_1).get(ELEMENT_VALUE).asInt();
                measurement.setShort_term_fuel_trim_1(short_term_fuel_trim_1);
            }
            if (phenomenons.get(ELEMENT_THROTTLE_POSITION) != null) {
                Integer throttle_position = phenomenons.get(ELEMENT_THROTTLE_POSITION).get(ELEMENT_VALUE).asInt();
                measurement.setThrottle_position(throttle_position);
            }
            if (phenomenons.get(ELEMENT_GPS_HDOP) != null) {
                Double gps_hdop = round( phenomenons.get(ELEMENT_GPS_HDOP).get(ELEMENT_VALUE).asDouble(), 5);
                measurement.setGps_hdop(gps_hdop);
            }
            if (phenomenons.get(ELEMENT_GPS_VDOP) != null) {
                Double gps_vdop = round( phenomenons.get(ELEMENT_GPS_VDOP).get(ELEMENT_VALUE).asDouble(), 5);
                measurement.setGps_vdop(gps_vdop);
            }
            if (phenomenons.get(ELEMENT_GPS_PDOP) != null) {
                Double gps_pdop = round( phenomenons.get(ELEMENT_GPS_PDOP).get(ELEMENT_VALUE).asDouble(), 5);
                measurement.setGps_pdop(gps_pdop);
            }
            if (phenomenons.get(ELEMENT_CALCULATED_MAF) != null) {
                Double maf = round( phenomenons.get(ELEMENT_CALCULATED_MAF).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setCalculated_maf(maf);
            }
            if (phenomenons.get(ELEMENT_GPS_HDOP) != null) {
                Double gps_hdop = round( phenomenons.get(ELEMENT_GPS_HDOP).get(ELEMENT_VALUE).asDouble(), 5);
                measurement.setGps_hdop(gps_hdop);
            }
            if (phenomenons.get(ELEMENT_O2_LAMBDA_CURRENT_ER) != null) {
                Double o2_lambda_current_er = round( phenomenons.get(ELEMENT_O2_LAMBDA_CURRENT_ER).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setO2_lambda_current_ER(o2_lambda_current_er);
            }
            if (phenomenons.get(ELEMENT_O2_LAMBDA_CURRENT) != null) {
                Double o2_lambda_current = round( phenomenons.get(ELEMENT_O2_LAMBDA_CURRENT).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setO2_lambda_current(o2_lambda_current);
            }
            if (phenomenons.get(ELEMENT_O2_LAMBDA_VOLTAGE) != null) {
                Double o2_lambda_voltage = round( phenomenons.get(ELEMENT_O2_LAMBDA_VOLTAGE).get(ELEMENT_VALUE).asDouble(), 4);
                measurement.setO2_lambda_voltage(o2_lambda_voltage);
            }
            if (phenomenons.get(ELEMENT_O2_LAMBDA_VOLTAGE_ER) != null) {
                Double o2_lambda_voltage_er = round( phenomenons.get(ELEMENT_O2_LAMBDA_VOLTAGE_ER).get(ELEMENT_VALUE).asDouble(), 5);
                measurement.setO2_lambda_voltage_ER(o2_lambda_voltage_er);
            }

            measurement.setMeasurementID(measurement_id);
            measurement.setTime(time);

            measurement.setLongitude(longitude);
            measurement.setLatitude(latitude);

            measurement.setSpeed(speed);

            results.addMeasurement(measurement);
        });

        result.setSensor(sensorID);
        result.setTrackID(trackID);
        result.setLength(length);
        result.setMeasurements(results.getMeasurements());

        return result;
    }

}

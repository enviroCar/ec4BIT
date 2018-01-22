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
 * The ec4BIT connector is distributed in the hope that it will be useful, but
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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import org.envirocar.ec4bit.core.model.Measurements;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class MeasurementsEncoder extends BaseJSONEncoder<Measurements> {

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

    private static final String UNIT_CALCULATED_MAF = "g/s";
    private static final String UNIT_CO2 = "kg/h";
    private static final String UNIT_CONSUMPTION = "l/h";
    private static final String UNIT_ENGINE_LOAD = "%";
    private static final String UNIT_FUEL_SYSTEM_STATUS_CODE = "category";
    private static final String UNIT_GPS_ACCURACY = "%";
    private static final String UNIT_GPS_ALTITUDE = "%";
    private static final String UNIT_GPS_BEARING = "deg";
    private static final String UNIT_GPS_HDOP = "precision";
    private static final String UNIT_GPS_PDOP = "precision";
    private static final String UNIT_GPS_SPEED = "km/h";
    private static final String UNIT_GPS_VDOP = "precision";
    private static final String UNIT_INTAKE_PRESSURE = "kPa";
    private static final String UNIT_INTAKE_TEMP = "c";
    private static final String UNIT_LONG_TERM_FUEL_TRIM_1 = "%";
    private static final String UNIT_MAF = "l/s";
    private static final String UNIT_O2_LAMBDA_CURRENT = "A";
    private static final String UNIT_O2_LAMBDA_CURRENT_ER = "ratio";
    private static final String UNIT_O2_LAMBDA_VOLTAGE = "V";
    private static final String UNIT_O2_LAMBDA_VOLTAGE_ER = "ratio";
    private static final String UNIT_RPM = "u/min";
    private static final String UNIT_SHORT_TERM_FUEL_TRIM_1 = "%";
    private static final String UNIT_SPEED = "km/h";
    private static final String UNIT_THROTTLE_POSITION = "%";

    @Override
    public void serialize(Measurements measurements, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();

        gen.writeObjectFieldStart("phenomenonUnits");

        if (measurements.containsPhenomDefinition(ELEMENT_SPEED)) {
            gen.writeObjectField(ELEMENT_SPEED, UNIT_SPEED);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_CO2)) {
            gen.writeObjectField(ELEMENT_CO2, UNIT_CO2);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_CONSUMPTION)) {
            gen.writeObjectField(ELEMENT_CONSUMPTION, UNIT_CONSUMPTION);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_SPEED)) {
            gen.writeObjectField(ELEMENT_GPS_SPEED, UNIT_GPS_SPEED);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_ALTITUDE)) {
            gen.writeObjectField(ELEMENT_GPS_ALTITUDE, UNIT_GPS_ALTITUDE);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_MAF)) {
            gen.writeObjectField(ELEMENT_MAF, UNIT_MAF);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_INTAKE_TEMP)) {
            gen.writeObjectField(ELEMENT_INTAKE_TEMP, UNIT_INTAKE_TEMP);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_INTAKE_PRESSURE)) {
            gen.writeObjectField(ELEMENT_INTAKE_PRESSURE, UNIT_INTAKE_PRESSURE);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_RPM)) {
            gen.writeObjectField(ELEMENT_RPM, UNIT_RPM);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_ENGINE_LOAD)) {
            gen.writeObjectField(ELEMENT_ENGINE_LOAD, UNIT_ENGINE_LOAD);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_FUEL_SYSTEM_STATUS_CODE)) {
            gen.writeObjectField(ELEMENT_FUEL_SYSTEM_STATUS_CODE, UNIT_FUEL_SYSTEM_STATUS_CODE);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_ACCURACY)) {
            gen.writeObjectField(ELEMENT_GPS_ACCURACY, UNIT_GPS_ACCURACY);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_BEARING)) {
            gen.writeObjectField(ELEMENT_GPS_BEARING, UNIT_GPS_BEARING);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_LONG_TERM_FUEL_TRIM_1)) {
            gen.writeObjectField(ELEMENT_LONG_TERM_FUEL_TRIM_1, UNIT_LONG_TERM_FUEL_TRIM_1);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_SHORT_TERM_FUEL_TRIM_1)) {
            gen.writeObjectField(ELEMENT_SHORT_TERM_FUEL_TRIM_1, UNIT_SHORT_TERM_FUEL_TRIM_1);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_THROTTLE_POSITION)) {
            gen.writeObjectField(ELEMENT_THROTTLE_POSITION, UNIT_THROTTLE_POSITION);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_HDOP)) {
            gen.writeObjectField(ELEMENT_GPS_HDOP, UNIT_GPS_HDOP);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_VDOP)) {
            gen.writeObjectField(ELEMENT_GPS_VDOP, UNIT_GPS_VDOP);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_GPS_PDOP)) {
            gen.writeObjectField(ELEMENT_GPS_PDOP, UNIT_GPS_PDOP);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_CALCULATED_MAF)) {
            gen.writeObjectField(ELEMENT_CALCULATED_MAF, UNIT_CALCULATED_MAF);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_O2_LAMBDA_CURRENT)) {
            gen.writeObjectField(ELEMENT_O2_LAMBDA_CURRENT, UNIT_O2_LAMBDA_CURRENT);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_O2_LAMBDA_CURRENT_ER)) {
            gen.writeObjectField(ELEMENT_O2_LAMBDA_CURRENT_ER, UNIT_O2_LAMBDA_CURRENT_ER);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_O2_LAMBDA_VOLTAGE)) {
            gen.writeObjectField(ELEMENT_O2_LAMBDA_VOLTAGE, UNIT_O2_LAMBDA_VOLTAGE);
        }
        if (measurements.containsPhenomDefinition(ELEMENT_O2_LAMBDA_VOLTAGE_ER)) {
            gen.writeObjectField(ELEMENT_O2_LAMBDA_VOLTAGE_ER, UNIT_O2_LAMBDA_VOLTAGE_ER);
        }
        gen.writeEndObject();

        writeArrayOfObjects(gen, "Measurements", measurements.getMeasurements());

        gen.writeEndObject();
    }

}

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

import org.envirocar.ec4bit.core.model.Measurements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

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
    private static final String ELEMENT_GPS_ACCURACY = "GPS Accuracy";
    private static final String ELEMENT_GPS_BEARING = "GPS Bearing";
    private static final String ELEMENT_LONG_TERM_FUEL_TRIM_1 = "Long-Term Fuel Trim 1";
    private static final String ELEMENT_SHORT_TERM_FUEL_TRIM_1 = "Short-Term Fuel Trim 1";
    private static final String ELEMENT_FUEL_SYSTEM_STATUS_CODE = "Fuel System Status Code";
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
        
        writeArrayOfObjects(gen, measurements.getMeasurements());

    }

}
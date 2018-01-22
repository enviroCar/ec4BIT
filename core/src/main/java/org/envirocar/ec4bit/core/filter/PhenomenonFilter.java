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
package org.envirocar.ec4bit.core.filter;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class PhenomenonFilter {

    private boolean speed = true;
    private boolean co2 = true;
    private boolean consumption = true;
    private boolean gps_speed = true;
    private boolean gps_altitude = true;
    private boolean maf = true;
    private boolean intake_temperature = true;
    private boolean intake_pressure = true;
    private boolean rpm = true;
    private boolean engine_load = true;
    private boolean fuel_system_status_code = true;
    private boolean gps_accuracy = true;
    private boolean gps_bearing = true;
    private boolean long_term_fuel_trim_1 = true;
    private boolean short_term_fuel_trim_1 = true;
    private boolean throlle_position = true;
    private boolean gps_hdop = true;
    private boolean gps_vdop = true;
    private boolean gps_pdop = true;
    private boolean calculated_maf = true;
    private boolean o2_lambda_current = true;
    private boolean o2_lambda_current_er = true;
    private boolean o2_lambda_voltage = true;
    private boolean o2_lambda_voltage_er = true;

    public PhenomenonFilter() {
    }

    ;

    public PhenomenonFilter(boolean speed, boolean co2, boolean consumption, boolean gps_speed, boolean gps_altitude, boolean maf, boolean intake_temperature, boolean intake_pressure, boolean rpm, boolean engine_load, boolean fuel_system_status_code, boolean gps_accuracy, boolean gps_bearing, boolean long_term_fuel_trim_1, boolean short_term_fuel_trim_1, boolean throlle_position, boolean gps_hdop, boolean gps_vdop, boolean gps_pdop, boolean calculated_maf, boolean o2_lambda_current, boolean o2_lambda_current_er, boolean o2_lambda_voltage, boolean o2_lambda_voltage_er) {
        this.speed = speed;
        this.co2 = co2;
        this.consumption = consumption;
        this.gps_speed = gps_speed;
        this.gps_altitude = gps_altitude;
        this.maf = maf;
        this.intake_temperature = intake_temperature;
        this.intake_pressure = intake_pressure;
        this.rpm = rpm;
        this.engine_load = engine_load;
        this.fuel_system_status_code = fuel_system_status_code;
        this.gps_accuracy = gps_accuracy;
        this.gps_bearing = gps_bearing;
        this.long_term_fuel_trim_1 = long_term_fuel_trim_1;
        this.short_term_fuel_trim_1 = short_term_fuel_trim_1;
        this.throlle_position = throlle_position;
        this.gps_hdop = gps_hdop;
        this.gps_vdop = gps_vdop;
        this.gps_pdop = gps_pdop;
        this.calculated_maf = calculated_maf;
        this.o2_lambda_current = o2_lambda_current;
        this.o2_lambda_current_er = o2_lambda_current_er;
        this.o2_lambda_voltage = o2_lambda_voltage;
        this.o2_lambda_voltage_er = o2_lambda_voltage_er;
    }

    public boolean getSpeed() {
        return speed;
    }

    public boolean getCo2() {
        return co2;
    }

    public boolean getConsumption() {
        return consumption;
    }

    public boolean getGps_speed() {
        return gps_speed;
    }

    public boolean getGps_altitude() {
        return gps_altitude;
    }

    public boolean getMaf() {
        return maf;
    }

    public boolean getIntake_temperature() {
        return intake_temperature;
    }

    public boolean getIntake_pressure() {
        return intake_pressure;
    }

    public boolean getRpm() {
        return rpm;
    }

    public boolean getEngine_load() {
        return engine_load;
    }

    public boolean getFuel_system_status_code() {
        return fuel_system_status_code;
    }

    public boolean getGps_accuracy() {
        return gps_accuracy;
    }

    public boolean getGps_bearing() {
        return gps_bearing;
    }

    public boolean getLong_term_fuel_trim_1() {
        return long_term_fuel_trim_1;
    }

    public boolean getShort_term_fuel_trim_1() {
        return short_term_fuel_trim_1;
    }

    public boolean getThrolle_position() {
        return throlle_position;
    }

    public boolean getGps_hdop() {
        return gps_hdop;
    }

    public boolean getGps_vdop() {
        return gps_vdop;
    }

    public boolean getGps_pdop() {
        return gps_pdop;
    }

    public boolean getCalculated_maf() {
        return calculated_maf;
    }

    public boolean getO2_lambda_current() {
        return o2_lambda_current;
    }

    public boolean getO2_lambda_current_er() {
        return o2_lambda_current_er;
    }

    public boolean getO2_lambda_voltage() {
        return o2_lambda_voltage;
    }

    public boolean getO2_lambda_voltage_er() {
        return o2_lambda_voltage_er;
    }

    public boolean hasPhenomenonsFiltered() {
        return this.speed
                || this.co2
                || this.consumption
                || this.gps_speed
                || this.gps_altitude
                || this.maf
                || this.intake_temperature
                || this.intake_pressure
                || this.rpm
                || this.engine_load
                || this.fuel_system_status_code
                || this.gps_accuracy
                || this.gps_bearing
                || this.long_term_fuel_trim_1
                || this.short_term_fuel_trim_1
                || this.throlle_position
                || this.gps_hdop
                || this.gps_vdop
                || this.gps_pdop
                || this.calculated_maf
                || this.o2_lambda_current
                || this.o2_lambda_current_er
                || this.o2_lambda_voltage
                || this.o2_lambda_voltage_er;
    }

//    @Override
//    public String toString() {
//        return "SpatialFilter{" + "xmin=" + xmin + ", ymin=" + ymin + ", xmax=" + xmax + ", ymax=" + ymax + '}';
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 7;
//        hash = 71 * hash + (int) (Double.booleanToLongBits(this.xmin) ^ (Double.booleanToLongBits(this.xmin) >>> 32));
//        hash = 71 * hash + (int) (Double.booleanToLongBits(this.ymin) ^ (Double.booleanToLongBits(this.ymin) >>> 32));
//        hash = 71 * hash + (int) (Double.booleanToLongBits(this.xmax) ^ (Double.booleanToLongBits(this.xmax) >>> 32));
//        hash = 71 * hash + (int) (Double.booleanToLongBits(this.ymax) ^ (Double.booleanToLongBits(this.ymax) >>> 32));
//        return hash;
//    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final PhenomenonFilter other = (PhenomenonFilter) obj;
//        if (Double.booleanToLongBits(this.xmin) != Double.booleanToLongBits(other.xmin)) {
//            return false;
//        }
//        if (Double.booleanToLongBits(this.ymin) != Double.booleanToLongBits(other.ymin)) {
//            return false;
//        }
//        if (Double.booleanToLongBits(this.xmax) != Double.booleanToLongBits(other.xmax)) {
//            return false;
//        }
//        if (Double.booleanToLongBits(this.ymax) != Double.booleanToLongBits(other.ymax)) {
//            return false;
//        }
//        return true;
//    }
    public void setSpeed(boolean speed) {
        this.speed = speed;
    }

    public void setCo2(boolean co2) {
        this.co2 = co2;
    }

    public void setConsumption(boolean consumption) {
        this.consumption = consumption;
    }

    public void setGps_speed(boolean gps_speed) {
        this.gps_speed = gps_speed;
    }

    public void setGps_altitude(boolean gps_altitude) {
        this.gps_altitude = gps_altitude;
    }

    public void setMaf(boolean maf) {
        this.maf = maf;
    }

    public void setIntake_temperature(boolean intake_temperature) {
        this.intake_temperature = intake_temperature;
    }

    public void setIntake_pressure(boolean intake_pressure) {
        this.intake_pressure = intake_pressure;
    }

    public void setRpm(boolean rpm) {
        this.rpm = rpm;
    }

    public void setEngine_load(boolean engine_load) {
        this.engine_load = engine_load;
    }

    public void setFuel_system_status_code(boolean fuel_system_status_code) {
        this.fuel_system_status_code = fuel_system_status_code;
    }

    public void setGps_accuracy(boolean gps_accuracy) {
        this.gps_accuracy = gps_accuracy;
    }

    public void setGps_bearing(boolean gps_bearing) {
        this.gps_bearing = gps_bearing;
    }

    public void setLong_term_fuel_trim_1(boolean long_term_fuel_trim_1) {
        this.long_term_fuel_trim_1 = long_term_fuel_trim_1;
    }

    public void setShort_term_fuel_trim_1(boolean short_term_fuel_trim_1) {
        this.short_term_fuel_trim_1 = short_term_fuel_trim_1;
    }

    public void setThrolle_position(boolean throlle_position) {
        this.throlle_position = throlle_position;
    }

    public void setGps_hdop(boolean gps_hdop) {
        this.gps_hdop = gps_hdop;
    }

    public void setGps_vdop(boolean gps_vdop) {
        this.gps_vdop = gps_vdop;
    }

    public void setGps_pdop(boolean gps_pdop) {
        this.gps_pdop = gps_pdop;
    }

    public void setCalculated_maf(boolean calculated_maf) {
        this.calculated_maf = calculated_maf;
    }

    public void setO2_lambda_current(boolean o2_lambda_current) {
        this.o2_lambda_current = o2_lambda_current;
    }

    public void setO2_lambda_current_er(boolean o2_lambda_current_er) {
        this.o2_lambda_current_er = o2_lambda_current_er;
    }

    public void setO2_lambda_voltage(boolean o2_lambda_voltage) {
        this.o2_lambda_voltage = o2_lambda_voltage;
    }

    public void setO2_lambda_voltage_er(boolean o2_lambda_voltage_er) {
        this.o2_lambda_voltage_er = o2_lambda_voltage_er;
    }

    public void setAllPhenomenononsTo(boolean turnOn) {
        speed = turnOn;
        co2 = turnOn;
        consumption = turnOn;
        gps_speed = turnOn;
        gps_altitude = turnOn;
        maf = turnOn;
        intake_temperature = turnOn;
        intake_pressure = turnOn;
        rpm = turnOn;
        engine_load = turnOn;
        fuel_system_status_code = turnOn;
        gps_accuracy = turnOn;
        gps_bearing = turnOn;
        long_term_fuel_trim_1 = turnOn;
        short_term_fuel_trim_1 = turnOn;
        throlle_position = turnOn;
        gps_hdop = turnOn;
        gps_vdop = turnOn;
        gps_pdop = turnOn;
        calculated_maf = turnOn;
        o2_lambda_current = turnOn;
        o2_lambda_current_er = turnOn;
        o2_lambda_voltage = turnOn;
        o2_lambda_voltage_er = turnOn;
    }

}

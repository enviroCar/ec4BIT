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
package org.envirocar.ec4bit.core.model;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
public class Measurement implements BaseEntity {

    private String measurementID;
    private DateTime time;
    private String sensor;
    private String trackID;
    private Double longitude;
    private Double latitude;
    private Double co2,
            consumption,
            gps_speed, gps_altitude, gps_bearing,
            maf,
            Intake_pressure,
            gps_vdop, gps_pdop, gps_hdop, gps_accuracy,
            calculated_maf,
            o2_lambda_current, o2_lambda_current_ER, o2_lambda_voltage, o2_lambda_voltage_ER,
            Intake_temp;

    private Integer speed,
            rpm,
            engine_load,
            fuel_system_status_code,
            long_term_fuel_trim_1,
            short_term_fuel_trim_1,
            throttle_position;

    public Measurement() {
        this(null, null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null,
                null, null, null, null, null);
    }

    public Measurement(String measurementID, DateTime time, String sensor, String trackID, Double longitude, Double latitude,
            Double co2, Double consumption, Double gps_speed, Double gps_bearing, Double gps_altitude, Double maf,
            Double Intake_pressure, Integer rpm, Integer engine_load,
            Double gps_vdop, Double gps_pdop, Double gps_hdop,
            Double calculated_maf, Double Intake_temp,
            Double o2_lambda_current, Double o2_lambda_current_ER, Double o2_lambda_voltage, Double o2_lambda_voltage_ER, Double gps_accuracy,
            Integer speed, Integer fuel_system_status_code,
            Integer long_term_fuel_trim_1, Integer short_term_fuel_trim_1, Integer throttle_position) {
        this.measurementID = measurementID;
        this.time = time;
        this.sensor = sensor;
        this.trackID = trackID;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.co2 = co2;
        this.consumption = consumption;
        this.gps_speed = gps_speed;
        this.gps_altitude = gps_altitude;
        this.maf = maf;
        this.Intake_temp = Intake_temp;
        this.Intake_pressure = Intake_pressure;
        this.rpm = rpm;
        this.engine_load = engine_load;
        this.gps_vdop = gps_vdop;
        this.gps_pdop = gps_pdop;
        this.gps_hdop = gps_hdop;
        this.calculated_maf = calculated_maf;
        this.o2_lambda_current = o2_lambda_current;
        this.o2_lambda_current_ER = o2_lambda_current_ER;
        this.o2_lambda_voltage = o2_lambda_voltage;
        this.o2_lambda_voltage_ER = o2_lambda_voltage_ER;
        this.fuel_system_status_code = fuel_system_status_code;
        this.gps_accuracy = gps_accuracy;
        this.gps_bearing = gps_bearing;
        this.long_term_fuel_trim_1 = long_term_fuel_trim_1;
        this.short_term_fuel_trim_1 = short_term_fuel_trim_1;
        this.throttle_position = throttle_position;
    }

    public String getMeasurementID() {
        return measurementID;
    }

    public void setMeasurementID(String measurementID) {
        this.measurementID = measurementID;
    }

    public Double getGps_vdop() {
        return gps_vdop;
    }

    public void setGps_vdop(Double gps_vdop) {
        this.gps_vdop = gps_vdop;
    }

    public Double getGps_pdop() {
        return gps_pdop;
    }

    public void setGps_pdop(Double gps_pdop) {
        this.gps_pdop = gps_pdop;
    }

    public Double getGps_hdop() {
        return gps_hdop;
    }

    public void setGps_hdop(Double gps_hdop) {
        this.gps_hdop = gps_hdop;
    }

    public Double getCalculated_maf() {
        return calculated_maf;
    }

    public void setCalculated_maf(Double calculated_maf) {
        this.calculated_maf = calculated_maf;
    }

    public Double getO2_lambda_current() {
        return o2_lambda_current;
    }

    public void setO2_lambda_current(Double o2_lambda_current) {
        this.o2_lambda_current = o2_lambda_current;
    }

    public Double getO2_lambda_current_ER() {
        return o2_lambda_current_ER;
    }

    public void setO2_lambda_current_ER(Double o2_lambda_current_ER) {
        this.o2_lambda_current_ER = o2_lambda_current_ER;
    }

    public Double getO2_lambda_voltage() {
        return o2_lambda_voltage;
    }

    public void setO2_lambda_voltage(Double o2_lambda_voltage) {
        this.o2_lambda_voltage = o2_lambda_voltage;
    }

    public Double getO2_lambda_voltage_ER() {
        return o2_lambda_voltage_ER;
    }

    public void setO2_lambda_voltage_ER(Double o2_lambda_voltage_ER) {
        this.o2_lambda_voltage_ER = o2_lambda_voltage_ER;
    }

    public Integer getFuel_system_status_code() {
        return fuel_system_status_code;
    }

    public void setFuel_system_status_code(Integer fuel_system_status_code) {
        this.fuel_system_status_code = fuel_system_status_code;
    }

    public Double getGps_accuracy() {
        return gps_accuracy;
    }

    public void setGps_accuracy(Double gps_accuracy) {
        this.gps_accuracy = gps_accuracy;
    }

    public Double getGps_bearing() {
        return gps_bearing;
    }

    public void setGps_bearing(Double gps_bearing) {
        this.gps_bearing = gps_bearing;
    }

    public Integer getLong_term_fuel_trim_1() {
        return long_term_fuel_trim_1;
    }

    public void setLong_term_fuel_trim_1(Integer long_term_fuel_trim_1) {
        this.long_term_fuel_trim_1 = long_term_fuel_trim_1;
    }

    public Integer getShort_term_fuel_trim_1() {
        return short_term_fuel_trim_1;
    }

    public void setShort_term_fuel_trim_1(Integer short_term_fuel_trim_1) {
        this.short_term_fuel_trim_1 = short_term_fuel_trim_1;
    }

    public Integer getThrottle_position() {
        return throttle_position;
    }

    public void setThrottle_position(Integer throttle_position) {
        this.throttle_position = throttle_position;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getSensorID() {
        return sensor;
    }

    public void setSensorID(String sensor) {
        this.sensor = sensor;
    }

    public String getTrackID() {
        return trackID;
    }

    public void setTrackID(String trackID) {
        this.trackID = trackID;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getConsumption() {
        return consumption;
    }

    public void setConsumption(Double consumption) {
        this.consumption = consumption;
    }

    public Double getGps_speed() {
        return gps_speed;
    }

    public void setGps_speed(Double gps_speed) {
        this.gps_speed = gps_speed;
    }

    public Double getGps_altitude() {
        return gps_altitude;
    }

    public void setGps_altitude(Double gps_altitude) {
        this.gps_altitude = gps_altitude;
    }

    public Double getMaf() {
        return maf;
    }

    public void setMaf(Double maf) {
        this.maf = maf;
    }

    public Double getIntake_temp() {
        return Intake_temp;
    }

    public void setIntake_temp(Double Intake_temp) {
        this.Intake_temp = Intake_temp;
    }

    public Double getIntake_pressure() {
        return Intake_pressure;
    }

    public void setIntake_pressure(Double Intake_pressure) {
        this.Intake_pressure = Intake_pressure;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Integer getEngine_load() {
        return engine_load;
    }

    public void setEngine_load(Integer engine_load) {
        this.engine_load = engine_load;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public boolean hasCo2() {
        return this.co2 != null;
    }

    public boolean hasConsumption() {
        return this.consumption != null;
    }

    public boolean hasGps_speed() {
        return this.gps_speed != null;
    }

    public boolean hasGps_altitude() {
        return this.gps_altitude != null;
    }

    public boolean hasGps_bearing() {
        return this.gps_bearing != null;
    }

    public boolean hasMaf() {
        return this.maf != null;
    }

    public boolean hasIntake_pressure() {
        return this.Intake_pressure != null;
    }

    public boolean hasGps_vdop() {
        return this.gps_vdop != null;
    }

    public boolean hasGps_pdop() {
        return this.gps_pdop != null;
    }

    public boolean hasGps_hdop() {
        return this.gps_hdop != null;
    }

    public boolean hasGps_accuracy() {
        return this.gps_accuracy != null;
    }

    public boolean hasCalculated_maf() {
        return this.calculated_maf != null;
    }

    public boolean hasO2_lambda_current() {
        return this.o2_lambda_current != null;
    }

    public boolean hasO2_lambda_current_ER() {
        return this.o2_lambda_current_ER != null;
    }

    public boolean hasO2_lambda_voltage() {
        return this.o2_lambda_voltage != null;
    }

    public boolean hasO2_lambda_voltage_ER() {
        return this.o2_lambda_voltage_ER != null;
    }

    public boolean hasIntake_temp() {
        return this.Intake_temp != null;
    }

    public boolean hasSpeed() {
        return this.speed != null;
    }

    public boolean hasRpm() {
        return this.rpm != null;
    }

    public boolean hasEngine_load() {
        return this.engine_load != null;
    }

    public boolean hasFuel_system_status_code() {
        return this.fuel_system_status_code != null;
    }

    public boolean hasLong_term_fuel_trim_1() {
        return this.long_term_fuel_trim_1 != null;
    }

    public boolean hasShort_term_fuel_trim_1() {
        return this.short_term_fuel_trim_1 != null;
    }

    public boolean hasThrottle_position() {
        return this.throttle_position != null;
    }

}

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
    private Double speed,
            co2,
            consumption,
            gps_speed, gps_altitude,
//            gps_vdop, gps_pdop, gps_hdop, gps_hood,
            maf,
//            calculated_maf,
            Intake_temp, Intake_pressure;
//            o2_lambda_current, o2_lambda_current_ER, o2_lambda_voltage, o2_lambda_voltage_ER;
    private Integer rpm,
            engine_load;
//            fuel_system_loop,
//            fuel_system_status_code,
//            gps_accuracy,
//            gps_bearing,
//            long_term_fuel_trim_1,
//            short_term_fuel_trim_1,
//            throttle_position;
            
    public Measurement() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Measurement(String measurementID, DateTime time, String sensor, String trackID, Double longitude, Double latitude, 
            Double speed, Double co2, Double consumption, Double gps_speed, Double gps_altitude, Double maf,
            Double Intake_temp, Double Intake_pressure, Integer rpm, Integer engine_load) {
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
    }

    public String getMeasurementID() {
        return measurementID;
    }

    public void setMeasurementID(String measurementID) {
        this.measurementID = measurementID;
    }

    public DateTime getTime() {
        return time;
    }

    public void setTime(DateTime time) {
        this.time = time;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
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

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

}

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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.envirocar.ec4bit.core.RemoteConfiguration;
import org.envirocar.ec4bit.core.filter.PhenomenonFilter;
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.remote.MeasurementsDAO;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import(RemoteConfiguration.class)
public class MeasurementsTest {

    @Autowired
    private MeasurementService measurementService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testMeasurementsSimple() throws IOException {
        Call<Measurements> measurements = this.measurementService.getAsMeasurements(1);
        Measurements values = measurements.execute().body();
        Assert.assertNotNull(values);
    }

    @Test
    public void testMeasurementID() throws IOException {
        Call<Measurement> measurement = this.measurementService.getAsMeasurement("5a4f1ee744ea85087e3a0a87");
        Measurement value = measurement.execute().body();
        Assert.assertNotNull(value);
    }

    @Test
    public void testMeasurementsBBOX() throws IOException {
        Double latMin = 41d;
        Double latMax = 42d;
        Double longMin = 2d;
        Double longMax = 3d;
        String bboxString = longMin + "," + latMin + "," + longMax + "," + latMax;
        Call<Measurements> measurements = this.measurementService
                .getAsMeasurements(bboxString, null, null, null);
        Measurements values = measurements.execute().body();
        Assert.assertNotNull(values);
        for (Measurement m : values.getMeasurements()) {
            if (m.hasLatitude()) {
                Double latitude = m.getLatitude();
                Assert.assertTrue(latitude <= latMax);
                Assert.assertTrue(latitude >= latMin);
            }
            if (m.hasLongitude()) {
                Double longitude = m.getLongitude();
                Assert.assertTrue(longitude <= longMax);
                Assert.assertTrue(longitude >= longMin);
            }
        }
    }

    @Test
    public void testMeasurementsStartDate() throws IOException {
        String startDate = "2018-01-13T00:00:00Z";
        DateTime dt_start = new DateTime(startDate);
        dt_start = dt_start.minusHours(1);
        Call<Measurements> measurements = this.measurementService
                .getAsMeasurements(null, startDate, null, null);
        Measurements values = measurements.execute().body();
        Assert.assertNotNull(values);
        for (Measurement m : values.getMeasurements()) {
            DateTime measurementTime = m.getTime();
            long timeDiff = measurementTime.getMillis() - dt_start.getMillis();
            Assert.assertTrue(timeDiff >= 0);
        }
    }

    @Test
    public void testMeasurementsEndDate() throws IOException {
        String endDate = "2014-07-28T00:00:00Z";
        DateTime dt_end = new DateTime(endDate);
        dt_end = dt_end.minusHours(1);
        Call<Measurements> measurements = this.measurementService
                .getAsMeasurements(null, null, endDate, null);
        Measurements values = measurements.execute().body();
        Assert.assertNotNull(values);
        for (Measurement m : values.getMeasurements()) {
            DateTime measurementTime = m.getTime();
            long timeDiff = dt_end.getMillis() - measurementTime.getMillis();
            Assert.assertTrue(timeDiff >= 0);
        }
    }

    @Test
    public void testMeasurementsStartDateEndDate() throws IOException {
        String startDate = "2018-01-05T00:00:00Z";
        DateTime dt_start = new DateTime(startDate);
        dt_start = dt_start.minusHours(1);
        String endDate = "2018-01-27T00:00:00Z";
        DateTime dt_end = new DateTime(endDate);
        dt_end = dt_end.minusHours(1);
        Call<Measurements> measurements = this.measurementService
                .getAsMeasurements(null, startDate, endDate, null);
        Measurements values = measurements.execute().body();
        Assert.assertNotNull(values);
        for (Measurement m : values.getMeasurements()) {
            DateTime measurementTime = m.getTime();
            long timeDiff = measurementTime.getMillis() - dt_start.getMillis();
            Assert.assertTrue(timeDiff >= 0);
            timeDiff = dt_end.getMillis() - measurementTime.getMillis();
            Assert.assertTrue(timeDiff >= 0);
        }
    }

    @Test
    public void testMeasurementsPhenomenons() throws IOException {

        PhenomenonFilter phenomenonFilter = new PhenomenonFilter();
        phenomenonFilter.setAllPhenomenononsTo(false);
        phenomenonFilter.setCo2(true);
        phenomenonFilter.setConsumption(true);
        phenomenonFilter.setSpeed(true);
        Call<Measurements> asMeasurements = measurementService
                .getAsMeasurements(null, null, null, "1");
        Measurements values = asMeasurements.execute().body();
        Measurements filteredMeasurements = new Measurements();
        // process Measurements and set false-filtered phenomenons to null:
        if (values.getMeasurements() != null) {
            for (Measurement m : values.getMeasurements()) {
                Measurement filtered = new Measurement();
                boolean hasPhenomenons = false;

                if (m.hasCo2() && phenomenonFilter.getCo2()) {
                    filtered.setCo2(m.getCo2());
                    hasPhenomenons = true;
                }
                if (m.hasCalculated_maf() && phenomenonFilter.getCalculated_maf()) {
                    filtered.setCalculated_maf(m.getCalculated_maf());
                    hasPhenomenons = true;
                }
                if (m.hasConsumption() && phenomenonFilter.getConsumption()) {
                    filtered.setConsumption(m.getConsumption());
                    hasPhenomenons = true;
                }
                if (m.hasEngine_load() && phenomenonFilter.getEngine_load()) {
                    filtered.setEngine_load(m.getEngine_load());
                    hasPhenomenons = true;
                }
                if (m.hasFuel_system_status_code() && phenomenonFilter.getFuel_system_status_code()) {
                    filtered.setFuel_system_status_code(m.getFuel_system_status_code());
                    hasPhenomenons = true;
                }
                if (m.hasGps_accuracy() && phenomenonFilter.getGps_accuracy()) {
                    filtered.setGps_accuracy(m.getGps_accuracy());
                    hasPhenomenons = true;
                }
                if (m.hasGps_altitude() && phenomenonFilter.getGps_altitude()) {
                    filtered.setGps_altitude(m.getGps_altitude());
                    hasPhenomenons = true;
                }
                if (m.hasGps_bearing() && phenomenonFilter.getGps_bearing()) {
                    filtered.setGps_bearing(m.getGps_bearing());
                    hasPhenomenons = true;
                }
                if (m.hasGps_hdop() && phenomenonFilter.getGps_hdop()) {
                    filtered.setGps_hdop(m.getGps_hdop());
                    hasPhenomenons = true;
                }
                if (m.hasGps_pdop() && phenomenonFilter.getGps_pdop()) {
                    filtered.setGps_pdop(m.getGps_pdop());
                    hasPhenomenons = true;
                }
                if (m.hasGps_speed() && phenomenonFilter.getGps_speed()) {
                    filtered.setGps_speed(m.getGps_speed());
                    hasPhenomenons = true;
                }
                if (m.hasGps_vdop() && phenomenonFilter.getGps_vdop()) {
                    filtered.setGps_vdop(m.getGps_vdop());
                    hasPhenomenons = true;
                }
                if (m.hasIntake_pressure() && phenomenonFilter.getIntake_pressure()) {
                    filtered.setIntake_pressure(m.getIntake_pressure());
                    hasPhenomenons = true;
                }
                if (m.hasLong_term_fuel_trim_1() && phenomenonFilter.getLong_term_fuel_trim_1()) {
                    filtered.setLong_term_fuel_trim_1(m.getLong_term_fuel_trim_1());
                    hasPhenomenons = true;
                }
                if (m.hasMaf() && phenomenonFilter.getMaf()) {
                    filtered.setMaf(m.getMaf());
                    hasPhenomenons = true;
                }
                if (m.hasO2_lambda_current() && phenomenonFilter.getO2_lambda_current()) {
                    filtered.setO2_lambda_current(m.getO2_lambda_current());
                    hasPhenomenons = true;
                }
                if (m.hasO2_lambda_current_ER() && phenomenonFilter.getO2_lambda_current_er()) {
                    filtered.setO2_lambda_current_ER(m.getO2_lambda_current_ER());
                    hasPhenomenons = true;
                }
                if (m.hasO2_lambda_voltage() && phenomenonFilter.getO2_lambda_voltage()) {
                    filtered.setO2_lambda_voltage(m.getO2_lambda_voltage());
                    hasPhenomenons = true;
                }
                if (m.hasO2_lambda_voltage_ER() && phenomenonFilter.getO2_lambda_voltage_er()) {
                    filtered.setO2_lambda_voltage_ER(m.getO2_lambda_voltage_ER());
                    hasPhenomenons = true;
                }
                if (m.hasRpm() && phenomenonFilter.getRpm()) {
                    filtered.setRpm(m.getRpm());
                    hasPhenomenons = true;
                }
                if (m.hasShort_term_fuel_trim_1() && phenomenonFilter.getShort_term_fuel_trim_1()) {
                    filtered.setShort_term_fuel_trim_1(m.getShort_term_fuel_trim_1());
                    hasPhenomenons = true;
                }
                if (m.hasSpeed() && phenomenonFilter.getSpeed()) {
                    filtered.setSpeed(m.getSpeed());
                    hasPhenomenons = true;
                }
                if (m.hasThrottle_position() && phenomenonFilter.getThrolle_position()) {
                    filtered.setThrottle_position(m.getThrottle_position());
                    hasPhenomenons = true;
                }

                if (hasPhenomenons) {
                    // add default items of meaasurement:
                    filtered.setLatitude(m.getLatitude());
                    filtered.setLongitude(m.getLongitude());
                    filtered.setTime(m.getTime());
                    filtered.setMeasurementID(m.getMeasurementID());
                    filtered.setSensorID(m.getSensorID());
                    filteredMeasurements.addMeasurement(filtered);
                }
            }
            if (phenomenonFilter.getSpeed()) {
                filteredMeasurements.addPhenomDefinition("Speed");
            }
            if (phenomenonFilter.getCo2()) {
                filteredMeasurements.addPhenomDefinition("CO2");
            }
            if (phenomenonFilter.getConsumption()) {
                filteredMeasurements.addPhenomDefinition("Consumption");
            }
            if (phenomenonFilter.getGps_speed()) {
                filteredMeasurements.addPhenomDefinition("GPS Speed");
            }
            if (phenomenonFilter.getGps_altitude()) {
                filteredMeasurements.addPhenomDefinition("GPS Altitude");
            }
            if (phenomenonFilter.getMaf()) {
                filteredMeasurements.addPhenomDefinition("MAF");
            }
            if (phenomenonFilter.getIntake_temperature()) {
                filteredMeasurements.addPhenomDefinition("Intake Temperature");
            }
            if (phenomenonFilter.getIntake_pressure()) {
                filteredMeasurements.addPhenomDefinition("Intake Pressure");
            }
            if (phenomenonFilter.getRpm()) {
                filteredMeasurements.addPhenomDefinition("Rpm");
            }
            if (phenomenonFilter.getEngine_load()) {
                filteredMeasurements.addPhenomDefinition("Engine Load");
            }
            if (phenomenonFilter.getFuel_system_status_code()) {
                filteredMeasurements.addPhenomDefinition("Fuel System Status Code");
            }
            if (phenomenonFilter.getGps_accuracy()) {
                filteredMeasurements.addPhenomDefinition("GPS Accuracy");
            }
            if (phenomenonFilter.getGps_bearing()) {
                filteredMeasurements.addPhenomDefinition("GPS Bearing");
            }
            if (phenomenonFilter.getLong_term_fuel_trim_1()) {
                filteredMeasurements.addPhenomDefinition("Long-Term Fuel Trim 1");
            }
            if (phenomenonFilter.getShort_term_fuel_trim_1()) {
                filteredMeasurements.addPhenomDefinition("Short-Term Fuel Trim 1");
            }
            if (phenomenonFilter.getThrolle_position()) {
                filteredMeasurements.addPhenomDefinition("Throttle Position");
            }
            if (phenomenonFilter.getGps_hdop()) {
                filteredMeasurements.addPhenomDefinition("GPS HDOP");
            }
            if (phenomenonFilter.getGps_pdop()) {
                filteredMeasurements.addPhenomDefinition("GPS PDOP");
            }
            if (phenomenonFilter.getGps_vdop()) {
                filteredMeasurements.addPhenomDefinition("GPS VDOP");
            }
            if (phenomenonFilter.getCalculated_maf()) {
                filteredMeasurements.addPhenomDefinition("Calculated MAF");
            }
            if (phenomenonFilter.getO2_lambda_current()) {
                filteredMeasurements.addPhenomDefinition("O2 Lambda Current");
            }
            if (phenomenonFilter.getO2_lambda_current_er()) {
                filteredMeasurements.addPhenomDefinition("O2 Lambda Current ER");
            }
            if (phenomenonFilter.getO2_lambda_voltage()) {
                filteredMeasurements.addPhenomDefinition("O2 Lambda Voltage");
            }
            if (phenomenonFilter.getO2_lambda_voltage_er()) {
                filteredMeasurements.addPhenomDefinition("O2 Lambda Voltage ER");
            }
        }
        Assert.assertNotNull(filteredMeasurements);
        for (Measurement m
                : filteredMeasurements.getMeasurements()) {
            Assert.assertTrue(m.hasCo2());
            Assert.assertTrue(m.hasSpeed());
            Assert.assertTrue(m.hasConsumption());
            Assert.assertNotNull(m.getCo2());
            Assert.assertNotNull(m.getSpeed());
            Assert.assertNotNull(m.getConsumption());
        }
    }
}

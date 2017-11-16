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
package org.envirocar.ec4bit.core.remote;

import java.io.IOException;
import org.envirocar.ec4bit.core.filter.MeasurementFilter;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.PhenomenonFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.remote.services.MeasurementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;

/**
 *
 * @author dewall
 */
@Component
public class MeasurementsDAO implements AbstractDAO<Measurements, MeasurementFilter> {

    private static final Logger LOG = LoggerFactory.getLogger(MeasurementsDAO.class);

    @Autowired
    private MeasurementService measurementService;

    @Override
    public Measurements get(MeasurementFilter filter) {
        String bboxParam = null;
        String timeBeforeParam = null;
        String timeAfterParam = null;
        String pageParam = null;
        PhenomenonFilter phenomenonFilter = new PhenomenonFilter();
        Boolean phenomenonFilterFilters = false;

        if (filter.hasSpatialFilter()) {
            SpatialFilter bbox = filter.getSpatialFilter();
            bboxParam = bbox.string();
        }
        if (filter.hasTemporalFilter()) {
            TemporalFilter temp = filter.getTemporalFilter();
            timeBeforeParam = temp.stringBefore();
            timeAfterParam = temp.stringAfter();
        }
        if (filter.hasPaginationFilter()) {
            PaginationFilter temp = filter.getPaginationFilter();
            pageParam = temp.string();
        }
        if (filter.hasPhenomenonFilter()) {
            phenomenonFilter = filter.getPhenomenonFilter();
            phenomenonFilterFilters = phenomenonFilter.hasPhenomenonsFiltered();
        }

        Call<Measurements> asMeasurements = measurementService
                .getAsMeasurements(bboxParam, timeAfterParam, timeBeforeParam, pageParam);

        try {
            Measurements body = asMeasurements.execute().body();

            if (phenomenonFilterFilters) {
                Measurements filteredMeasurements = new Measurements();
                // process Measurements and set false-filtered phenomenons to null:
                if (body.getMeasurements() != null) {
                    for (Measurement m : body.getMeasurements()) {
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
                        if (m.hasFuel_system_loop() && phenomenonFilter.getFuel_system_loop()) {
                            filtered.setFuel_system_loop(m.getFuel_system_loop());
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
                    if (phenomenonFilter.getFuel_system_loop()) {
                        filteredMeasurements.addPhenomDefinition("Fuel System Loop");
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
                return filteredMeasurements;
            } else {
                body.addPhenomDefinition("Speed");
                body.addPhenomDefinition("CO2");
                body.addPhenomDefinition("Consumption");
                body.addPhenomDefinition("GPS Speed");
                body.addPhenomDefinition("GPS Altitude");
                body.addPhenomDefinition("MAF");
                body.addPhenomDefinition("Intake Temperature");
                body.addPhenomDefinition("Intake Pressure");
                body.addPhenomDefinition("Rpm");
                body.addPhenomDefinition("Engine Load");
                body.addPhenomDefinition("Fuel System Loop");
                body.addPhenomDefinition("Fuel System Status Code");
                body.addPhenomDefinition("GPS Accuracy");
                body.addPhenomDefinition("GPS Bearing");
                body.addPhenomDefinition("Long-Term Fuel Trim 1");
                body.addPhenomDefinition("Short-Term Fuel Trim 1");
                body.addPhenomDefinition("Throttle Position");
                body.addPhenomDefinition("GPS HDOP");
                body.addPhenomDefinition("GPS PDOP");
                body.addPhenomDefinition("GPS VDOP");
                body.addPhenomDefinition("Calculated MAF");
                body.addPhenomDefinition("O2 Lambda Current");
                body.addPhenomDefinition("O2 Lambda Current ER");
                body.addPhenomDefinition("O2 Lambda Voltage");
                body.addPhenomDefinition("O2 Lambda Voltage ER");
                return body;
            }
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }

}

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
package org.envirocar.ec4bit.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import java.util.Map;
import org.eclipse.bigiot.lib.handlers.AccessRequestHandler;
import org.eclipse.bigiot.lib.offering.OfferingDescription;
import org.eclipse.bigiot.lib.serverwrapper.BigIotHttpResponse;
import org.envirocar.ec4bit.connector.exception.KeyNotFoundException;
import org.envirocar.ec4bit.connector.exception.RequestProcessingException;
import org.envirocar.ec4bit.core.filter.PaginationFilter;
import org.envirocar.ec4bit.core.filter.PhenomenonFilter;
import org.envirocar.ec4bit.core.filter.SpatialFilter;
import org.envirocar.ec4bit.core.filter.TemporalFilter;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author dewall
 */
public abstract class AbstractRequestHandler<E> implements AccessRequestHandler, Constants {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractRequestHandler.class);

    @Autowired
    private ObjectMapper mapper;

    private Class<? extends E> clazz;

    /**
     * Constructor.
     *
     * @param clazz
     */
    public AbstractRequestHandler(Class<? extends E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public BigIotHttpResponse processRequestHandler(OfferingDescription od, Map<String, Object> map, String subscriberId, String consumerInfo) {
        try {
            E responseEntity = processRequest(od, map);
            String body = mapper.writeValueAsString(responseEntity);

            return BigIotHttpResponse.okay()
                    .withBody(body)
                    .asJsonType();
        } catch (RequestProcessingException e) {
            LOG.error(e.getMessage(), e);
            return BigIotHttpResponse.error()
                    .withBody("{\"status\":\"error\"}")
                    .withStatus(e.getErrorCode())
                    .asJsonType();
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
            return BigIotHttpResponse.error()
                    .withBody("{\"status\":\"error\"}")
                    .withStatus(422)
                    .asJsonType();
        }
    }

    protected <T> T checkAndGetValue(String key, Map<String, Object> input) throws KeyNotFoundException {
        if (!input.containsKey(key)) {
            throw new KeyNotFoundException(key);
        }
        return (T) input.get(key);
    }

    protected SpatialFilter getSpatialFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        String bbox = checkAndGetValue(BBOX, input);
        String[] points = bbox.split(" ");
        String[] coordsA = points[0].split(",");
        String[] coordsB = points[1].split(",");
        double xMin = Double.valueOf(coordsA[0]);
        double yMin = Double.valueOf(coordsA[1]);
        double xMax = Double.valueOf(coordsB[0]);
        double yMax = Double.valueOf(coordsB[1]);
        return new SpatialFilter(xMin, yMin, xMax, yMax);
    }

    protected double[] getBoundingBoxParams(Map<String, Object> input) throws KeyNotFoundException {
        String bbox = checkAndGetValue(BBOX, input);
        String[] points = bbox.split(" ");
        String[] coordsA = points[0].split(",");
        String[] coordsB = points[1].split(",");
        double xMin = Double.valueOf(coordsA[0]);
        double yMin = Double.valueOf(coordsA[1]);
        double xMax = Double.valueOf(coordsB[0]);
        double yMax = Double.valueOf(coordsB[1]);
        return new double[]{xMin, yMin, xMax, yMax};
    }

    protected PaginationFilter getPaginationFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        int pageNumber = Integer.valueOf(checkAndGetValue(PAGE, input));
        return new PaginationFilter(pageNumber);
    }

    protected TemporalFilter getTemporalFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        DateTime dt_start = null;
        DateTime dt_end = null;
        String after = checkAndGetValue(TIME_AFTER, input);
        if (after != null) {
            dt_start = DateTime.parse(after,
                    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
        }
        String before = checkAndGetValue(TIME_BEFORE, input);
        if (before != null) {
            dt_end = DateTime.parse(before,
                    DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ"));
        }
        return new TemporalFilter(dt_start, dt_end);
    }

    protected String getTrackID(Map<String, Object> input) throws KeyNotFoundException {
        String trackID = checkAndGetValue(SINGLE_TRACK, input);
        return trackID;
    }

    protected PhenomenonFilter getPhenomenonFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        String phenomString = checkAndGetValue(PHENOMENONS, input);
        PhenomenonFilter pf;
        if (phenomString != null) {
            pf = new PhenomenonFilter(
                    false, false, false, false, false,
                    false, false, false, false, false,
                    false, false, false, false, false,
                    false, false, false, false, false,
                    false, false, false, false, false);
            phenomString = phenomString.toLowerCase();            
            String[] phenomenons = phenomString.split(",");
            for (int i = 0; i < phenomenons.length; i++) {
                if (phenomenons[i].equals("co2")) {
                    pf.setCo2(true);
                    continue;
                }
                if (phenomenons[i].equals("engine load")) {
                    pf.setEngine_load(true);
                    continue;
                }
                if (phenomenons[i].equals("calculated maf")) {
                    pf.setCalculated_maf(true);
                    continue;
                }
                if (phenomenons[i].equals("consumption")) {
                    pf.setConsumption(true);
                    continue;
                }
                if (phenomenons[i].equals("fuel system loop")) {
                    pf.setFuel_system_loop(true);
                    continue;
                }
                if (phenomenons[i].equals("fuel system status code")) {
                    pf.setFuel_system_status_code(true);
                    continue;
                }
                if (phenomenons[i].equals("gps accuracy")) {
                    pf.setGps_accuracy(true);
                    continue;
                }
                if (phenomenons[i].equals("gps alitude")) {
                    pf.setGps_altitude(true);
                    continue;
                }
                if (phenomenons[i].equals("gps bearing")) {
                    pf.setGps_bearing(true);
                    continue;
                }
                if (phenomenons[i].equals("gps hdop")) {
                    pf.setGps_hdop(true);
                    continue;
                }
                if (phenomenons[i].equals("gps pdop")) {
                    pf.setGps_pdop(true);
                    continue;
                }
                if (phenomenons[i].equals("gps speed")) {
                    pf.setGps_speed(true);
                    continue;
                }
                if (phenomenons[i].equals("gps vdop")) {
                    pf.setGps_vdop(true);
                    continue;
                }
                if (phenomenons[i].equals("intake pressure")) {
                    pf.setIntake_pressure(true);
                    continue;
                }
                if (phenomenons[i].equals("intake temperature")) {
                    pf.setIntake_temperature(true);
                    continue;
                }
                if (phenomenons[i].equals("long-term fuel trim 1")) {
                    pf.setLong_term_fuel_trim_1(true);
                    continue;
                }
                if (phenomenons[i].equals("maf")) {
                    pf.setMaf(true);
                    continue;
                }
                if (phenomenons[i].equals("o2 lambda current")) {
                    pf.setO2_lambda_current(true);
                    continue;
                }
                if (phenomenons[i].equals("o2 lambda current er")) {
                    pf.setO2_lambda_current_er(true);
                    continue;
                }
                if (phenomenons[i].equals("o2 lambda voltage")) {
                    pf.setO2_lambda_voltage(true);
                    continue;
                }
                if (phenomenons[i].equals("o2 lambda voltage er")) {
                    pf.setO2_lambda_voltage_er(true);
                    continue;
                }
                if (phenomenons[i].equals("rpm")) {
                    pf.setRpm(true);
                    continue;
                }
                if (phenomenons[i].equals("short-term fuel trim 1")) {
                    pf.setShort_term_fuel_trim_1(true);
                    continue;
                }
                if (phenomenons[i].equals("speed")) {
                    pf.setSpeed(true);
                    continue;
                }
                if (phenomenons[i].equals("throttle position")) {
                    pf.setThrolle_position(true);
                }
            }

        } else {
            pf = new PhenomenonFilter(
                    true, true, true, true, true,
                    true, true, true, true, true,
                    true, true, true, true, true,
                    true, true, true, true, true,
                    true, true, true, true, true);
        }
        return pf;
    }

    public abstract E processRequest(OfferingDescription od, Map<String, Object> map) throws RequestProcessingException;
}

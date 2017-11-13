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
    public BigIotHttpResponse processRequestHandler(OfferingDescription od, Map<String, Object> map) {
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
        PhenomenonFilter pf = new PhenomenonFilter(
                    true, true, true, true, true, 
                    true, true, true, true, true, 
                    true, true, true, true, true, 
                    true, true, true, true, true, 
                    true, true, true, true, true);
        if (phenomString != null) {
            phenomString = phenomString.toLowerCase();
            if (!phenomString.contains("speed")) {
                pf.setSpeed(false);
            }
            if (!phenomString.contains("co2")) {
                pf.setCo2(false);
            }
//                    speed, co2, consumption, gps_speed, gps_altitude,
//                    maf, intake_temperature, intake_pressure, rpm, engine_load,
//                    fuel_system_loop, fuel_system_status_code, gps_accuracy, gps_bearing, long_term_fuel_trim_1,
//                    short_term_fuel_trim_1, throlle_position, gps_hdop, gps_vdop, gps_pdop,
//                    calculated_maf, o2_lambda_current, o2_lambda_current_er, o2_lambda_voltage, o2_lambda_voltage_er
        }
        return pf;
    }

    public abstract E processRequest(OfferingDescription od, Map<String, Object> map) throws RequestProcessingException;
}
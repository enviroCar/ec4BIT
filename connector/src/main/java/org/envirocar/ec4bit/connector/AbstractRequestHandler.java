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
        Map<String, Object> bbox = checkAndGetValue(BBOX, input);
        double xMax = Double.valueOf(checkAndGetValue(BBOX_XMAX, bbox));
        double yMax = Double.valueOf(checkAndGetValue(BBOX_YMAX, bbox));
        double xMin = Double.valueOf(checkAndGetValue(BBOX_XMIN, bbox));
        double yMin = Double.valueOf(checkAndGetValue(BBOX_YMIN, bbox));
        return new SpatialFilter(xMin, yMin, xMax, yMax);
    }

    protected double[] getBoundingBoxParams(Map<String, Object> input) throws KeyNotFoundException {
        Map<String, Object> bbox = checkAndGetValue(BBOX, input);
        double xMax = Double.valueOf(checkAndGetValue(BBOX_XMAX, bbox));
        double yMax = Double.valueOf(checkAndGetValue(BBOX_YMAX, bbox));
        double xMin = Double.valueOf(checkAndGetValue(BBOX_XMIN, bbox));
        double yMin = Double.valueOf(checkAndGetValue(BBOX_YMIN, bbox));
        return new double[]{xMin, yMin, xMax, yMax};
    }
    
    protected PaginationFilter getPaginationFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        Map<String, Object> page = checkAndGetValue(PAGE, input);
        int pageNumber = Integer.valueOf(checkAndGetValue(PAGE_NUMBER, page));
        return new PaginationFilter(pageNumber);
    }

    protected TemporalFilter getTemporalFilterParams(Map<String, Object> input) throws KeyNotFoundException {
        Map<String, Object> during = checkAndGetValue(DURING, input);
        Map<String, Object> st_date = checkAndGetValue(DURING_START, during);
        Object year = checkAndGetValue("year", st_date);
        String month = checkAndGetValue("monthOfYear", st_date);
        month = ("00" + month).substring(month.length());
        String day = checkAndGetValue("dayOfMonth", st_date);
        day = ("00" + day).substring(day.length());
        String hours = checkAndGetValue("hourOfDay", st_date);
        hours = ("00" + hours).substring(hours.length());
        String mins = checkAndGetValue("minuteOfHour", st_date);
        mins = ("00" + mins).substring(mins.length());
        String secs = checkAndGetValue("secondOfMinute", st_date);
        secs = ("00" + secs).substring(secs.length());
        DateTime dt_start = DateTime.parse(year + "-" + month + "-" + day + "T" + hours + ":" + mins + ":" + secs,
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"));

        Map<String, Object> end_date = checkAndGetValue(DURING_END, during);
        year = checkAndGetValue("year", end_date);
        month = checkAndGetValue("monthOfYear", end_date);
        month = ("00" + month).substring(month.length());
        day = checkAndGetValue("dayOfMonth", end_date);
        day = ("00" + day).substring(day.length());
        hours = checkAndGetValue("hourOfDay", end_date);
        hours = ("00" + hours).substring(hours.length());
        mins = checkAndGetValue("minuteOfHour", end_date);
        mins = ("00" + mins).substring(mins.length());
        secs = checkAndGetValue("secondOfMinute", end_date);
        secs = ("00" + secs).substring(secs.length());
        DateTime dt_end = DateTime.parse(year + "-" + month + "-" + day + "T" + hours + ":" + mins + ":" + secs,
                DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"));

        return new TemporalFilter(dt_start, dt_end);
    }

    public abstract E processRequest(OfferingDescription od, Map<String, Object> map) throws RequestProcessingException;
}

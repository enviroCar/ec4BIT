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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.envirocar.ec4bit.connector;

import java.util.Map;
import org.eclipse.bigiot.lib.handlers.AccessRequestHandler;

/**
 *
 * @author hafenkran
 */
public abstract class AbstractRequestHandler implements AccessRequestHandler, Constants {

    protected <T> T checkAndGetValue(String key, Map<String, Object> input) throws Exception {
        if (!input.containsKey(key)) {
            throw new Exception("Key does not exist: " + key);
        }
        return (T) input.get(key);
    }

    protected double[] getBoundingBoxParams(Map<String, Object> input) throws Exception {
        Map<String, Object> bbox = checkAndGetValue(BBOX, input);
        double xMax = Double.valueOf(checkAndGetValue(BBOX_XMAX, bbox));
        double yMax = Double.valueOf(checkAndGetValue(BBOX_YMAX, bbox));
        double xMin = Double.valueOf(checkAndGetValue(BBOX_XMIN, bbox));
        double yMin = Double.valueOf(checkAndGetValue(BBOX_YMIN, bbox));
        return new double[]{xMin, yMin, xMax, yMax};
    }
}

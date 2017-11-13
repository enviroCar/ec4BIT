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
        Boolean PhenomenonFilterFilters = false;

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
            PhenomenonFilterFilters = phenomenonFilter.hasPhenomenonsFiltered();
        }

        Call<Measurements> asMeasurements = measurementService
                .getAsMeasurements(bboxParam, timeAfterParam, timeBeforeParam, pageParam);

        try {
            Measurements body = asMeasurements.execute().body();

            if (PhenomenonFilterFilters) {
                Measurements filteredMeasurements = new Measurements();
                // process Measurements and set false-filtered phenomenons to null:
                if (body.getMeasurements() != null) {
                    for (Measurement m : body.getMeasurements()) {
                        // == REPLACE CODE WITH == //
                        if (phenomenonFilter.getSpeed() && m.hasSpeed()) {
                            filteredMeasurements.addMeasurement(m);
                            continue;
                        }
                        if (phenomenonFilter.getCo2() && m.hasCo2()) {
                            filteredMeasurements.addMeasurement(m);
                            continue;
                        }
                        // === WITH: === //
                        // for each phenomenon p in m do:
                            // bool isRelevant = false;
                            // if phenomenonFilter.get(p) = true
                                // isRelevant = true;
                            // else 
                                // m.setP(null);
                            // if isRelevant 
                                // filteredMeasurements.addMeasurement(m);
                    }
                }
                return filteredMeasurements;
            }

            return body;
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex); // TODO proper logging and exception handling
        }

        return null;
    }

}

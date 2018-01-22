/*
 * Copyright (C) 2013 - 2018 the enviroCar community
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
package org.envirocar.ec4bit.core.decoder;


import org.envirocar.ec4bit.core.encoding.MeasurementEncoder;
import org.envirocar.ec4bit.core.encoding.MeasurementsEncoder;
import org.envirocar.ec4bit.core.encoding.SpeedValueEncoder;
import org.envirocar.ec4bit.core.encoding.SpeedValuesEncoder;
import org.envirocar.ec4bit.core.encoding.TrackEncoder;
import org.envirocar.ec4bit.core.encoding.TracksEncoder;
import org.envirocar.ec4bit.core.model.Measurement;
import org.envirocar.ec4bit.core.model.Measurements;
import org.envirocar.ec4bit.core.model.SpeedValue;
import org.envirocar.ec4bit.core.model.SpeedValues;
import org.envirocar.ec4bit.core.model.Track;
import org.envirocar.ec4bit.core.model.Tracks;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 *
 * @author dewall
 */
public class ECModule extends SimpleModule {

    public ECModule() {
        this.addSerializer(SpeedValue.class, new SpeedValueEncoder());
        this.addSerializer(SpeedValues.class, new SpeedValuesEncoder());
        this.addDeserializer(SpeedValues.class, new SpeedValuesDecoder());
        this.addSerializer(Measurement.class, new MeasurementEncoder());
        this.addSerializer(Measurements.class, new MeasurementsEncoder());
        this.addDeserializer(Measurement.class, new MeasurementDecoder());
        this.addDeserializer(Measurements.class, new MeasurementsDecoder());
        this.addSerializer(Track.class, new TrackEncoder());
        this.addSerializer(Tracks.class, new TracksEncoder());
        this.addDeserializer(Track.class, new TrackDecoder());
        this.addDeserializer(Tracks.class, new TracksDecoder());
    }

}

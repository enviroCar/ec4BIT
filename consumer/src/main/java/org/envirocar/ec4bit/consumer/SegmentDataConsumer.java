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
package org.envirocar.ec4bit.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.eclipse.bigiot.lib.Consumer;
import org.eclipse.bigiot.lib.exceptions.AccessToNonActivatedOfferingException;
import org.eclipse.bigiot.lib.exceptions.AccessToNonSubscribedOfferingException;
import org.eclipse.bigiot.lib.exceptions.IncompleteOfferingQueryException;
import org.eclipse.bigiot.lib.feed.AccessFeed;
import org.eclipse.bigiot.lib.model.BigIotTypes;
import org.eclipse.bigiot.lib.model.IOData;
import org.eclipse.bigiot.lib.model.Information;
import org.eclipse.bigiot.lib.model.Price.Euros;
import org.eclipse.bigiot.lib.offering.AccessParameters;
import org.eclipse.bigiot.lib.offering.Offering;
import org.eclipse.bigiot.lib.offering.SubscribableOfferingDescription;
import org.eclipse.bigiot.lib.query.OfferingQuery;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maurin Radtke <m.radtke@52north.org>
 */
@Component
public class SegmentDataConsumer {

    public SegmentDataConsumer(
            @Value("${bigiot.consumer.id}") String consumerId,
            @Value("${bigiot.marketplace.url}") String marketplaceUrl,
            @Value("${bigiot.consumer.secret}") String consumerSecret) throws IOException, IncompleteOfferingQueryException, InterruptedException, ExecutionException, AccessToNonActivatedOfferingException, AccessToNonSubscribedOfferingException {
        Consumer consumer = new Consumer(consumerId, marketplaceUrl);
        consumer.authenticate(consumerSecret);

        OfferingQuery query = OfferingQuery.create("SegmentDataQuery")
                .withInformation(new Information("SegmentsDataQuery", "bigiot:TrafficSegments"))
                .withPricingModel(BigIotTypes.PricingModel.PER_ACCESS)
                .withMaxPrice(Euros.amount(0.002))
                .withLicenseType(BigIotTypes.LicenseType.OPEN_DATA_LICENSE);
        CompletableFuture<List<SubscribableOfferingDescription>> listFuture = consumer.discover(query);
        listFuture.thenApply(SubscribableOfferingDescription::showOfferingDescriptions);
        List<SubscribableOfferingDescription> list = listFuture.get();

        for (SubscribableOfferingDescription desc : list) {
            System.out.println(desc.toString());
        }
        for (SubscribableOfferingDescription desc : list) {
            System.out.println(desc.toString());
        }

        if (list != null && !list.isEmpty()) {

            List<Offering> offerings = new ArrayList<>();
            for (SubscribableOfferingDescription desc : list) {
                CompletableFuture<Offering> offeringFuture = desc.subscribe();
                Offering offering = offeringFuture.get();
                offerings.add(offering);
            }

            // Prepare Access Parameters
            AccessParameters accessParameters = AccessParameters.create()
//                    .addNameValue("sortBy", "NumSpeed")
//                    .addNameValue("box", "50.076,7.5 52.08,8.00")
//                    .addNameValue("featureID", "10fc17c0-6223-46bc-9606-1bbfb3df42ce");
//                    .addNameValue("intersects", "7.65,50 7.65,52 7.65,52 8,52 8,52 8,50 8,5 7.65,50")
                    .addNameValue("dwithin", "7.65,51.3 10000");

            // Create an Access Feed with callbacks for the received results		
            Duration feedDuration = Duration.standardHours(2);
            Duration feedInterval = Duration.standardSeconds(2);

            List<AccessFeed> accessFeeds = new ArrayList<>();
            for (Offering offering : offerings) {
                AccessFeed accessFeed = offering.accessContinuous(accessParameters,
                        feedDuration.getMillis(),
                        feedInterval.getMillis(),
                        (f, r) -> {
                            if (r == null) {
                                System.out.println("yea");
                            }

                            System.out.println("Received data: " + r.asJsonNode().toString());
                        },
                        (f, r) -> {
                            System.out.println("Feed operation failed");
                            f.stop();
                        });
                accessFeeds.add(accessFeed);
            }

            // Run until user presses the ENTER key
            System.out.println(">>>>>>  Terminate ExampleConsumer by pressing ENTER  <<<<<<");
            Scanner keyboard = new Scanner(System.in);
            keyboard.nextLine();

            // Stop Access Feed
            for (AccessFeed accessFeed : accessFeeds) {
                accessFeed.stop();
            }

            // Unsubscribe the Offering
            for (Offering offering : offerings) {
                offering.unsubscribe();
            }

        } else {
            // No active Offerings could be discovered 
            System.out.println(">>>>>>  No matching offering found  <<<<<<");
        }

        // Terminate consumer instance
        consumer.terminate();
    }
}
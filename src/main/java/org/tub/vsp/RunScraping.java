package org.tub.vsp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tub.vsp.data.container.StreetBaseDataContainer;
import org.tub.vsp.io.JsonIo;

import java.util.List;

public class RunScraping {
    private static final Logger logger = LogManager.getLogger(RunScraping.class);

    public static void main(String[] args) {
        StreetScraper scraper = new StreetScraper();

        logger.info("Starting scraping");
        List<StreetBaseDataContainer> allStreetBaseData = scraper.extractAllBaseData();


        JsonIo jsonIo = new JsonIo();
        logger.info("Writing json");
        jsonIo.writeJson(allStreetBaseData, "output/street_data.json");
    }
}

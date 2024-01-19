package org.tub.vsp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class RunScraping {
    private static final Logger logger = LogManager.getLogger(RunScraping.class);

    public static void main(String[] args) throws IOException {
        StreetScraper scraper = new StreetScraper();
        List<String> projectUrls = scraper.getProjectUrls();
        logger.info("Found {} projects", projectUrls.size());
        logger.info(projectUrls);
    }
}

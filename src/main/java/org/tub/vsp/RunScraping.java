package org.tub.vsp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.tub.vsp.data.container.StreetBaseDataContainer;
import org.tub.vsp.io.JsonIo;

import java.io.IOException;
import java.util.List;

public class RunScraping {
    private static final Logger logger = LogManager.getLogger(RunScraping.class);

    public static void main(String[] args) throws IOException {
        StreetScraper scraper = new StreetScraper();
        List<StreetBaseDataContainer> allStreetBaseData = scraper.getAllStreetBaseData();

        Gson gson = new GsonBuilder().serializeNulls()
                                     .setPrettyPrinting()
                                     .create();

        JsonIo jsonIo = new JsonIo(gson);
        jsonIo.writeJson(allStreetBaseData, "output/street_data.json");


    }
}

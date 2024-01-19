package org.tub.vsp.data.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.StreetBaseDataContainer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StreetScraper extends Scraper {
    private static final Logger logger = LogManager.getLogger(StreetScraper.class);

    @Override
    protected String getBaseUrl() {
        return "https://www.bvwp-projekte.de/strasse/";
    }

    public List<StreetBaseDataContainer> getAllStreetBaseData() throws IOException {
        List<String> projectUrls = getProjectUrls();
        logger.info("Found {} projects", projectUrls.size());
        logger.info(projectUrls);

        return projectUrls.stream()
                          .map(this::getStreetBaseData)
                          .filter(Optional::isPresent)
                          .map(Optional::get)
                          .toList();
    }

    private Optional<StreetBaseDataContainer> getStreetBaseData(String projectUrl) {
        Document doc;

        try {
            doc = Jsoup.connect(projectUrl)
                       .get();
        } catch (IOException e) {
            logger.warn("Could not connect to {}", projectUrl);
            logger.warn("Skipping project {}", projectUrl);
            return Optional.empty();
        }

        if (!checkIfProjectIsScrapable(doc)) {
            return Optional.empty();
        }

        StreetBaseDataContainer streetBaseDataContainer = new StreetBaseDataContainer();
        return Optional.of(streetBaseDataContainer.setUrl(projectUrl)
                                                  .setProjectInformation(new ProjectInformationMapper().mapDocument(doc)));
    }

    protected Optional<StreetBaseDataContainer> getStreetBaseData(Document doc) {
        if (!checkIfProjectIsScrapable(doc)) {
            return Optional.empty();
        }

        StreetBaseDataContainer streetBaseDataContainer = new StreetBaseDataContainer();
        return Optional.of(streetBaseDataContainer.setProjectInformation(new ProjectInformationMapper().mapDocument(doc))
                                                  .setEmissions(new EmissionsMapper().mapDocument(doc))
                                                  .setPhysicalEffect(new PhysicalEffectMapper().mapDocument(doc))
                                                  .setCostBenefitAnalysis(new CostBenefitMapper().mapDocument(doc)));
    }

    private boolean checkIfProjectIsScrapable(Document doc) {
        //TODO
        return true;
    }
}

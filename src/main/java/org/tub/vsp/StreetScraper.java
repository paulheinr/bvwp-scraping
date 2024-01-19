package org.tub.vsp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.StreetBaseDataContainer;
import org.tub.vsp.data.mapper.CostBenefitMapper;
import org.tub.vsp.data.mapper.PhysicalEffectMapper;
import org.tub.vsp.data.mapper.ProjectInformationMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class StreetScraper extends Scraper {
    private static final Logger logger = LogManager.getLogger(StreetScraper.class);

    private final ProjectInformationMapper projectInformationMapper = new ProjectInformationMapper();
    private final PhysicalEffectMapper physicalEffectMapper = new PhysicalEffectMapper();
    private final CostBenefitMapper costBenefitMapper = new CostBenefitMapper();

    @Override
    protected String getBaseUrl() {
        return "https://www.bvwp-projekte.de/strasse/";
    }

    public List<StreetBaseDataContainer> getAllStreetBaseData() throws IOException {
        List<String> projectUrls = getProjectUrls();
        logger.info("Found {} projects", projectUrls.size());
        logger.info(projectUrls);

        return projectUrls.stream()
                          .map(this::getStreetBaseDataFromUrl)
                          .filter(Optional::isPresent)
                          .map(Optional::get)
                          .toList();
    }

    private Optional<StreetBaseDataContainer> getStreetBaseDataFromUrl(String projectUrl) {
        Document doc;

        try {
            doc = Jsoup.connect(projectUrl)
                       .get();
        } catch (IOException e) {
            logger.warn("Could not connect to {}", projectUrl);
            logger.warn("Skipping project.");
            return Optional.empty();
        }

        return getStreetBaseData(doc);
    }

    public Optional<StreetBaseDataContainer> getStreetBaseData(Document doc) {
        if (!checkIfProjectIsScrapable(doc)) {
            return Optional.empty();
        }

        StreetBaseDataContainer streetBaseDataContainer = new StreetBaseDataContainer();
        return Optional.of(streetBaseDataContainer.setProjectInformation(projectInformationMapper.mapDocument(doc))
                                                  .setPhysicalEffect(physicalEffectMapper.mapDocument(doc))
                                                  .setCostBenefitAnalysis(costBenefitMapper.mapDocument(doc)));
    }

    private boolean checkIfProjectIsScrapable(Document doc) {
        //TODO
        return true;
    }
}

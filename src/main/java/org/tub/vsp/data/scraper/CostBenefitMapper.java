package org.tub.vsp.data.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.CostBenefitAnalysisDataContainer;

public class CostBenefitMapper implements DocumentMapper<CostBenefitAnalysisDataContainer> {
    private static final Logger logger = LogManager.getLogger(CostBenefitMapper.class);

    @Override
    public CostBenefitAnalysisDataContainer mapDocument(Document document) {
        return new CostBenefitAnalysisDataContainer();
    }
}

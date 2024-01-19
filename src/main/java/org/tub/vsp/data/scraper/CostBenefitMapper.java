package org.tub.vsp.data.scraper;

import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.CostBenefitAnalysisDataContainer;

public class CostBenefitMapper implements DocumentMapper<CostBenefitAnalysisDataContainer> {

    @Override
    public CostBenefitAnalysisDataContainer mapDocument(Document document) {
        return new CostBenefitAnalysisDataContainer();
    }
}

package org.tub.vsp.data.scraper;

import org.jsoup.nodes.Document;
import org.tub.vsp.data.container.PhysicalEffectDataContainer;

public class PhysicalEffectMapper implements DocumentMapper<PhysicalEffectDataContainer> {
    @Override
    public PhysicalEffectDataContainer mapDocument(Document document) {
        return new PhysicalEffectDataContainer().setEmissionsDataContainer(new EmissionsMapper().mapDocument(document));
    }
}

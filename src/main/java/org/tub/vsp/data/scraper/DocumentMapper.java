package org.tub.vsp.data.scraper;

import org.jsoup.nodes.Document;

public interface DocumentMapper<T> {
    public T mapDocument(Document document);
}

package org.tub.vsp.data.mapper;

import org.jsoup.nodes.Document;

public interface DocumentMapper<T> {
    public T mapDocument(Document document);
}

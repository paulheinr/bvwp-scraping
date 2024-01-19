package org.tub.vsp;

import org.jsoup.nodes.Element;

import java.util.Optional;

public class JSoupUtils {
    public static String getTextFromRowAndCol(Element table, int rowIndex, int colIndex) {
        return table.select("tr")
                    .get(rowIndex)
                    .select("td")
                    .get(colIndex)
                    .text();
    }

    public static Optional<Element> firstRowWithKeyInCol(Element table, String key, int colIndex) {
        return table.select("tr")
                    .stream()
                    .filter(r -> r.child(colIndex)
                                  .text()
                                  .contains(key))
                    .findFirst();
    }
}

package org.tub.vsp;

import org.jsoup.nodes.Element;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
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

    public static Double parseDouble(String s) throws ParseException {
        return NumberFormat.getInstance(Locale.GERMANY)
                           .parse(s)
                           .doubleValue();
    }
}

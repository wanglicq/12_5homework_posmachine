package parser;

import domain.SecondHalfPrice;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class SecondHalfPriceParser extends Parser<SecondHalfPrice>{

    private static final Pattern PATTERN = compile("^(\\w+)$");

    @Override
    protected SecondHalfPrice parseLine(String line) {
        String barcode = line;
        return new SecondHalfPrice(barcode);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}

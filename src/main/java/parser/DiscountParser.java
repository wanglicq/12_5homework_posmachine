package parser;

import domain.Discount;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class DiscountParser extends Parser<Discount> {
    private static final Pattern PATTERN = compile("^(\\w+):(\\d+)$");

    @Override
    protected Discount parseLine(String line) {
        String barcode = line.split(":")[0];
        double price = Double.parseDouble(line.split(":")[1]);
        return new Discount(barcode, price);
    }

    @Override
    protected Pattern getPattern() {
        return PATTERN;
    }
}

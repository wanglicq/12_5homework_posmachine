package inter;

import domain.CartItem;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class TheParser {
    private static final Pattern PATTERN = compile("^(\\w+)-(\\d+)$");

    public List<CartItem> parse(List<String> input, String symbol) {
        List<CartItem> list = new ArrayList<CartItem>();
        for (String line : input) {
            if (!PATTERN.matcher(line).matches()) {
                throw new IllegalArgumentException("invalid input format");
            }
            String[] splitLine = line.split(symbol);
            String barcode = splitLine[0];
            int quantity = Integer.parseInt(splitLine[1]);
            list.add(new CartItem(barcode, quantity));
        }
        return list;
    }

}


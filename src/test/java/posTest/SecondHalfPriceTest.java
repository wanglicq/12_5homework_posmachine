package posTest;

import domain.SecondHalfPrice;
import org.junit.Test;
import parser.SecondHalfPriceParser;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SecondHalfPriceTest {
    @Test
    public void should_return_empty_when_given_none_second_half_price() throws Exception {
        List<String> empty = Arrays.asList();
        List<SecondHalfPrice> result = new SecondHalfPriceParser().parse(empty);
        assertThat(result.size(), is(0));
    }

    @Test
    public void should_return_1_half_second_price() throws Exception {
        List<String> input = Arrays.asList("I1");
        List<SecondHalfPrice> result = new SecondHalfPriceParser().parse(input);
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getBarcode(), is("I1"));
    }

    @Test
    public void should_return_2_when_has_2_second_half_price_goods() throws Exception {
        List<String> input = Arrays.asList("I1", "I2");
        List<SecondHalfPrice> result = new SecondHalfPriceParser().parse(input);
        assertThat(result.size(), is(2));
        assertThat(result.get(0).getBarcode(), is("I1"));
        assertThat(result.get(1).getBarcode(), is("I2"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_invalid_input() {
        new SecondHalfPriceParser().parse(asList("bulabula-5"));
    }
}

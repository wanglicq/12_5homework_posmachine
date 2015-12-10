package posTest;

import domain.Discount;
import org.junit.Test;
import parser.DiscountParser;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class discountTest {
    @Test
    public void should_return_empty_when_given_none() throws Exception {
        // given
        List<String> input = Arrays.asList();
        // when
        List<Discount> result = new DiscountParser().parse(input);
        //then
        assertThat(result.size(),is(0));

    }

    @Test
    public void should_return_1_discount_when_input_1_discount() throws Exception {
        List<String> input = Arrays.asList("I1:75");
        List<Discount> result = new DiscountParser().parse(input);
        assertThat(result.size(),is(1));
        assertThat(result.get(0).getBarcode(), is("I1"));
        assertEquals(result.get(0).getDiscount(), 75, 1e-6);

    }

    @Test
    public void should_return_2_discounts_when_input_multiple_discount() throws Exception {
        List<String> input = Arrays.asList("I1:75", "I2:90");
        List<Discount> result = new DiscountParser().parse(input);
        assertThat(result.size(),is(2));
        assertThat(result.get(0).getBarcode(), is("I1"));
        assertEquals(result.get(0).getDiscount(), 75, 1e-6);
        assertThat(result.get(1).getBarcode(), is("I2"));
        assertEquals(result.get(1).getDiscount(), 90, 1e-6);

    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_data() {
        new DiscountParser().parse(asList("lalala"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_complain_invalid_discount() {
        new DiscountParser().parse(asList("lalala:d8"));
    }
}

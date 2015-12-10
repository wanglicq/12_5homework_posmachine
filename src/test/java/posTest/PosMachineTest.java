package posTest;

import domain.CartItem;
import domain.Item;
import inter.PosMachine;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PosMachineTest {
    private PosMachine posMachine;

    @Before
    public void setUp() {
        List<Item> allItems = Arrays.asList(new Item("ITEM000001", 40),
                new Item("ITEM000003", 50), new Item("ITEM000005", 60));
        posMachine = new PosMachine(allItems);

    }

    @Test
    public void should_return_0_when_shopping_cart_empty() {
        List<CartItem> emptyCart = Arrays.asList();
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(0d));
    }

    @Test
    public void should_calculate_total_when_given_1_item() {
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 1));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(50d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_items_in_second_half_price() {
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000003", 3));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(125d));
    }

    @Test
    public void should_calculate_total_when_given_multiple_types_item() {
        List<CartItem> emptyCart = Arrays.asList(new CartItem("ITEM000001", 3), new CartItem("ITEM000003", 2));
        double total = posMachine.calculate(emptyCart);

        assertThat(total, is(150d));
    }

    @Test
    public void should_calculate_total_when_item5_has_discount() throws Exception {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000005", 3));
        double total = posMachine.calculate(itemCart);
        System.out.println(total);

        assertThat(total, is(162d));
    }

    @Test
    public void should_calculate_total_when_item3_has_second_half_price() throws Exception {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000003", 2));
        double total = posMachine.calculate(itemCart);
        System.out.println(total);

        assertThat(total, is(75d));
    }

    @Test
    public void should_calculate_total_when_item1_has_discount_and_second_half_price() throws Exception {
        List<CartItem> itemCart = Arrays.asList(new CartItem("ITEM000001", 3));
        double total = posMachine.calculate(itemCart);
        System.out.println(total);

        assertThat(total, is(75d));
    }

}


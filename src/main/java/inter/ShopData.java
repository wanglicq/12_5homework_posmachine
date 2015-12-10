package inter;

import java.util.Arrays;
import java.util.List;

public final class ShopData {

    public static final List<String> ITEMS_DATA =
            new readTxt().readData("data/itemlist.txt");

    public static final List<String> SHOPPING_CART_DATA =
            new readTxt().readData("data/cart.txt");


    public static final List<String> DISCOUNT_ITEMS =
            new readTxt().readData("data/discount_promotion.txt");


    public static final List<String> SECOND_HALF_PRICE =
            new readTxt().readData("data/second_half_price_promotion.txt");

}
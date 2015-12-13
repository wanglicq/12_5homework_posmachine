package promotion;


import domain.CartItem;
import domain.Item;
import org.javatuples.Pair;

public class CompositePromotion implements Promotion {

    private final Promotion[] promotions;

    public CompositePromotion(Promotion... promotions) {

        this.promotions = promotions;
    }

    @Override
    public Pair<Double, Double> getPromotion(Item item, CartItem cartItem) {
        double newPrice = item.getPrice();
        double savePrice = 0.0;

        for (Promotion promotion : promotions) {
            Pair<Double, Double> promotionResult = promotion.getPromotion(item, cartItem);
            newPrice = promotionResult.getValue0();
            item.setPrice(newPrice);
            savePrice += promotionResult.getValue1();
        }

        return new Pair<>(newPrice, savePrice);
    }
}

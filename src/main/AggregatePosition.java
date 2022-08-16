package main;

import java.util.List;

@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
public class AggregatePosition {
    private String account;
    private String instrument;
    private Integer quantity;
    private List<Integer> trades;

    @Override
    public String toString() {
        return "AggregatePosition{" +
                "account='" + account + '\'' +
                ", instrument='" + instrument + '\'' +
                ", quantity=" + quantity +
                ", trades='" + trades + '\'' +
                '}';
    }

    public AggregatePosition(String account, String instrument, Integer quantity, List<Integer> trades) {
        this.account = account;
        this.instrument = instrument;
        this.quantity = quantity;
        this.trades = trades;
    }


}

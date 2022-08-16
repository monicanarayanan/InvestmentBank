package main;

@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
public class RealTimePosition {

    private Integer trade_id;
    private Integer trade_version;
    private String security_id;
    private Integer quantity;
    private String direction;
    private String acc_no;
    private String operation;

    @Override
    public String toString() {
        return "RealTimePosition{" +
                "trade_id=" + trade_id +
                ", trade_version=" + trade_version +
                ", security_id='" + security_id + '\'' +
                ", quantity=" + quantity +
                ", direction='" + direction + '\'' +
                ", acc_no='" + acc_no + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }

    public Integer getTrade_id() {
        return trade_id;
    }

    public Integer getTrade_version() {
        return trade_version;
    }

    public String getSecurity_id() {
        return security_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getDirection() {
        return direction;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public String getOperation() {
        return operation;
    }


    public RealTimePosition(Integer trade_id, Integer trade_version, String security_id, Integer quantity, String direction, String acc_no, String operation) {
        this.trade_id = trade_id;
        this.trade_version = trade_version;
        this.security_id = security_id;
        this.quantity = quantity;
        this.direction = direction;
        this.acc_no = acc_no;
        this.operation = operation;
    }


}



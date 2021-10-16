package hnt.com.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "pair", nullable = false)
    private String pair;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "price_of_pair", nullable = false)
    private BigDecimal price_of_pair;

    @Column(name = "amount_of_coin", nullable = false)
    private String amount_of_coin;

    @Column(name = "total_price_of_pair", nullable = false)
    private String total_price_of_pair;

    @Column(name = "fee_coin", nullable = false)
    private String fee_coin;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    public Log() {
    }

    public Log(String pair, String action, BigDecimal price_of_pair, String amount_of_coin, String total_price_of_pair, String fee_coin, LocalDateTime date) {
        this.pair = pair;
        this.action = action;
        this.price_of_pair = price_of_pair;
        this.amount_of_coin = amount_of_coin;
        this.total_price_of_pair = total_price_of_pair;
        this.fee_coin = fee_coin;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getPrice_of_pair() {
        return price_of_pair;
    }

    public void setPrice_of_pair(BigDecimal price_of_pair) {
        this.price_of_pair = price_of_pair;
    }

    public String getAmount_of_coin() {
        return amount_of_coin;
    }

    public void setAmount_of_coin(String amount_of_coin) {
        this.amount_of_coin = amount_of_coin;
    }

    public String getTotal_price_of_pair() {
        return total_price_of_pair;
    }

    public void setTotal_price_of_pair(String total_price_of_pair) {
        this.total_price_of_pair = total_price_of_pair;
    }

    public String getFee_coin() {
        return fee_coin;
    }

    public void setFee_coin(String fee_coin) {
        this.fee_coin = fee_coin;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Log)) {
            return false;
        } else {
            Log compare = (Log) obj;
            if (compare.getPair().contentEquals(getPair()) &&
                    compare.getPair().contentEquals(getPair()) &&
                    compare.getAction().contentEquals(getAction()) &&
                    compare.getPrice_of_pair().compareTo(getPrice_of_pair()) == 0 &&
                    compare.getAmount_of_coin().contentEquals(getAmount_of_coin()) &&
                    compare.getTotal_price_of_pair().contentEquals(getTotal_price_of_pair()) &&
                    compare.getFee_coin().contentEquals(getFee_coin()) &&
                    compare.getDate().compareTo(getDate()) == 0
            ) {
                return true;
            } else {
                return false;
            }
        }
    }
}

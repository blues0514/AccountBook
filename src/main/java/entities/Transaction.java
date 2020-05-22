package entities;

import entities.base.Entity;
import lombok.Data;

import java.sql.Date;


@Data
public class Transaction extends Entity {
    private int transactionId;
    private int accountId;
    private int amount;
    private Date date;
    private String detail;
    private int transactionCategoryId;
    private boolean isIncome;

    @Override
    public String getKeyText() {
        return null;
    }
}

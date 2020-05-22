package entities;

import entities.base.Entity;
import lombok.Data;

@Data
public class TransactionCategory extends Entity {
    private int transactionCategoryId;
    private String name;
    private boolean isIncome;


    @Override
    public String getKeyText() {
        return null;
    }
}

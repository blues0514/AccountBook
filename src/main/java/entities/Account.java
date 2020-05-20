package entities;

import entities.base.Entity;
import lombok.Data;

@Data
public class Account extends Entity {
    private int accountId;
    private int accountNumber;
    private int memberId;
    private String bank;

    @Override
    public String getKeyText() {
        return null;
    }
}

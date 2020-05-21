package entities;

import entities.base.Entity;
import lombok.Data;

@Data
public class Member extends Entity {
    private int memberId;
    private String id;
    private String password;
    private int targetAmount;

    @Override
    public String getKeyText() {
        return Integer.toString(memberId);
    }
}

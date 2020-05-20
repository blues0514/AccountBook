package entities;

import lombok.Data;

@Data
public class Account {
    private int accountId;
    private int accountNumber;
    private int memberId;
    private String bank;
}

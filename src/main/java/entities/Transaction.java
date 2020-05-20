package entities;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private int transactionId;
    private int accountId;
    private int amount;
    private Date date;
    private String detail;
    private int transactionCategoryId;
}

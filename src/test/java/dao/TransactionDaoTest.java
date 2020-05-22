package dao;

import entities.Transaction;
import entities.TransactionCategory;
import helpers.ConnectionString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDaoTest {

    @BeforeAll
    static void initializeConnectionString(){
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.8;database=AccountBook;user=sa;password=1234");
    }

    @Test
    void getByIsIncome() {
        ArrayList<Transaction> transactions =
                TransactionDao.getInstance().getByIsIncome(1);
        for (Transaction transaction :transactions) {
            boolean isIncome = transaction.isIncome();
            assertEquals(true,isIncome);
        }
    }

    @Test
    void getByMonth() {
//        ArrayList<Transaction> transactions =
//        TransactionDao.getInstance().getByMonth(5);
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction.getDate());
//            assertEquals("05",transaction.getDate()
//                    .toString().substring(5,7));
//        }
    }

    @Test
    void getByPeriod() {
//        ArrayList<Transaction> transactions =
//                TransactionDao.getInstance().getByPeriod(1,5,21,21);
//        for (Transaction transaction :transactions) {
//            assertEquals("21",transaction.getDate().toString().substring(8));
//        }
    }
}
import dao.MemberDao;
import dao.TransactionDao;
import entities.Member;
import entities.Transaction;
import helpers.ConnectionString;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void transactionInquiry(int isIncome){
        ArrayList<Transaction> transactions =
                TransactionDao.getInstance().getByIsIncome(isIncome);
        for (Transaction transaction: transactions) {
            System.out.println(transaction.getDate()+","
                    +transaction.getDetail()+": "+transaction.getAmount());
        }
    }

    private static void transactionInquiryByMonth(int month) {
        ArrayList<Transaction> transactions
                =TransactionDao.getInstance().getByMonth(month);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }

    private static void transactionInquiryByDay(int memberId, int month, int startDay, int endDay){
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByPeriod(memberId,month,startDay,endDay);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }
}

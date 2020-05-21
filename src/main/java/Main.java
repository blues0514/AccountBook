import dao.AccountDao;
import dao.MemberDao;
import dao.TransactionDao;
import entities.Account;
import entities.Member;
import entities.Transaction;
import helpers.ConnectionString;

import java.util.ArrayList;
import java.util.Scanner;
//
public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
        Member member = new Member();
        Transaction transaction = new Transaction();

        getUser(member);
        System.out.printf("%s님 환영합니다.\n", member.getId());

        System.out.println("이용 중인 계좌");
        printAccount(member);


        System.out.println("월별 거래내역");
        printMonthTransactions(member);
        transactionInquiryByDay(2,1, 1);



        //수입,지출 입력
        //insertTransaction(amount, detail, transactionCategoryId);

        //수입,지출 조회
        //transactionInquiry(transactionCategoryId);

    }

    static Member getUser(Member member){
        Scanner sc = new Scanner(System.in);
        boolean login = true;

        while (login) {
            try {
                System.out.println("id");
                String userId = sc.nextLine();
                System.out.println("pw");
                String userPw = sc.nextLine();

                Member loginUser = MemberDao.getInstance().login(userId, userPw);
                member.setId(loginUser.getId());
                member.setPassword(loginUser.getPassword());
                member.setMemberId(loginUser.getMemberId());
                member.setTargetAmount(loginUser.getTargetAmount());

                if (member.getPassword().equals(userPw))
                    login = false;
            } catch (NullPointerException e) {
                System.out.println("존재 하지 않는 ID");
            }
        }
        return member;
    }

    static void printAccount(Member member) {
        ArrayList<Account> accounts =
                AccountDao.getInstance().getAccountNumbers(member.getMemberId());
        for (Account account : accounts)
            System.out.print(account.getAccountNumber() + " ");
        System.out.println();
    }

    static void printMonthTransactions(Member member){
        ArrayList<Transaction> transactions =TransactionDao.getInstance().getTransactions(2);
        for (Transaction transaction1 : transactions)
            System.out.println(transaction1);
    }
    static void insertTransaction(int amount, String detail,int transactionCategoryId){
        Transaction entity = new Transaction();
        entity.setAmount(amount);
        entity.setDetail(detail);
        entity.setTransactionCategoryId(transactionCategoryId);
        TransactionDao.getInstance().insert(entity);
    }

    public static void transactionInquiry(int isIncome){
        ArrayList<Transaction> transactions =
                TransactionDao.getInstance().getByIsIncome(isIncome);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }

    private static void transactionInquiryByMonth(int month) {
        ArrayList<Transaction> transactions
                =TransactionDao.getInstance().getByMonth(month);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }

    private static void transactionInquiryByDay(int month, int startDay, int endDay){
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByDay(month,startDay,endDay);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }


}

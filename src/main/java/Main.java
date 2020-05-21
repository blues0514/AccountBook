import dao.MemberDao;
import dao.TransactionDao;
import entities.Member;
import entities.Transaction;
import helpers.ConnectionString;

import java.util.ArrayList;
import java.util.Scanner;
//
public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.8;database=AccountBook;user=sa;password=1234");
        Member member = new Member();
        //수입,지출 입력
//        insertTransaction(amount, detail, transactionCategoryId);

        //수입,지출 조회
//        transactionInquiry(transactionCategoryId);
          getUser(member);
          int currentMemberId = member.getMemberId();

          transactionInquiryByDay(currentMemberId,5,1,30 );
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

//    private static void parseDate()  {
//        int date = 4;
//        String date2 = "2019-04-25";
////        date = date.substring(0,4)+"-"+date.substring(4,6)+"-"+date.substring(6,8);
//        System.out.println(date);
//        LocalDate startDate = LocalDate.parse(date);
//        ChronoLocalDate endDate = LocalDate.parse(date2);
//        System.out.println(startDate.compareTo(endDate));
//        System.out.println(date2);
//    }

    private static void transactionInquiryByMonth(int month) {
        ArrayList<Transaction> transactions
                =TransactionDao.getInstance().getByMonth(month);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }

    private static void transactionInquiryByDay(int memberId, int month, int startDay, int endDay){
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByDay(memberId,month,startDay,endDay);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
    }


}

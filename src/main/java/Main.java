import dao.TransactionDao;
import entities.Transaction;
import helpers.ConnectionString;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://192.168.1.8;database=AccountBook;user=sa;password=1234");
//        Member member = new Member();
//        member.setId("test2");
//        member.setPassword("1234");
//        member.setTargetAmount(10000);
//        MemberDao.getInstance().insert(member);

              /*
    님 환영합니다
    보유 계좌
    현재 시간
    잔고
    1. 수입 입력  2. 지출 입력 3. 수입 조회 4. 지출 조회 5. 통계 보기 6. 종료

    - 수입 입력 , 지출 입력

    -  수입 조회
     : 총 수입, 최근 수입 내역 3건  1. 기간 조회 2.월별  3. 주별 4. 일별
     : TransactionCategory의 IsIncome이 true일 경우 모든 row 불러와서 합 계산
    -  지출 조회
     : = 총 수입

    - 통계 보기
     : 보류
    - 종료
     : login = false
     */

        //수입,지출 입력
//        insertTransaction(amount, detail, transactionCategoryId);

        //수입,지출 조회
//        transactionInquiry(transactionCategoryId);
        //
//        transactionInquiryByDate();
//        transactionInquiryByDay(1,3);
//        transactionInquiryByMonth(5);
//        transactionInquiry(1);
//        System.out.println("======================");
//        transactionInquiry(0);
        transactionInquiryByDay(5,1,30);
//
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

    private static void transactionInquiryByDay(int month, int startDay, int endDay){
        TransactionDao.getInstance().getByDay(month,startDay,endDay);
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByDay(month,startDay,endDay);
        for (Transaction transaction: transactions) {
            System.out.println(transaction);
        }
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




}

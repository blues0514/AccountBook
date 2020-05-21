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
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
        Member member = new Member();
        member.setId("test2");
        member.setPassword("1234");
        member.setTargetAmount(10000);
        MemberDao.getInstance().insert(member);

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
                //수입,지출 입력
        insertTransaction(amount, detail, transactionCategoryId);

        //수입,지출 조회
        transactionInquiry(transactionCategoryId);
        //
        //

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

import dao.MemberDao;
import dao.TransactionDao;
import entities.Member;
import entities.Transaction;
import helpers.ConnectionString;

import javax.swing.text.html.parser.Entity;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");

        String id = getName();
        System.out.println(id);

        //수입,지출 입력
//        insertTransaction(amount, detail, transactionCategoryId);

        //수입,지출 조회
//        transactionInquiry(transactionCategoryId);
        //
        //

    }

    static String getName() {
        Scanner sc = new Scanner(System.in);
        boolean login = true;
        String userId = null;
        String userPw;
        while (login) {
            try {
                System.out.println("id");
                userId = sc.nextLine();
                System.out.println("pw");
                userPw = sc.nextLine();
                login = MemberDao.getInstance().login(userId, userPw) != 1;
            } catch (NullPointerException e) {
                System.out.println("존재 하지 않는 ID");
            }
        }
        return userId;
    }

    static void insertTransaction(int amount, String detail, int transactionCategoryId) {
        Transaction entity = new Transaction();
        entity.setAmount(amount);
        entity.setDetail(detail);
        entity.setTransactionCategoryId(transactionCategoryId);
        TransactionDao.getInstance().insert(entity);
    }

    static void transactionInquiry(int transactionCategoryId) {
        TransactionDao.getInstance().getByTransactionCategoryId(transactionCategoryId);
    }


}

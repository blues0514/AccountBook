import dao.AccountDao;
import dao.MemberDao;
import dao.TransactionDao;
import entities.Account;
import entities.Member;
import entities.Transaction;
import helpers.ConnectionString;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");

        Member member = new Member();
        int usingMemberId = member.getMemberId();

        getUser(member);
        System.out.printf("%s님 환영합니다.\n", member.getId());

        System.out.println("계좌를 선택해주세요 (왼쪽부터 0)");
        int usingAccountId = selectAccount(usingMemberId);

        System.out.println("이용 가능 항목");
        usingService(usingMemberId, usingAccountId);
    }

    private static void usingService(int usingMemberId, int usingAccountId) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while(run) {
            System.out.println("1.수입 목록 2.지출 목록 3.기간별 조회 4. 종료");
            int menu = sc.nextInt();
            switch (menu) {
                case 1:
                    transactionInquiry(usingMemberId,usingAccountId,1);
                    break;
                case 2:
                    transactionInquiry(usingMemberId,usingAccountId,0);
                    break;
                case 3:
                    System.out.println("1.월별 2.기간 지정");
                    int menuBy3 = sc.nextInt();
                    switch (menuBy3) {
                        case 1:
                            System.out.println("원하는 월 입력");
                            int hopeMonth = sc.nextInt();
                            transactionInquiryByMonth(usingMemberId,usingAccountId,hopeMonth);
                            break;
                        case 2:
                            System.out.println("원하는 월, 시작 일, 마지막 일 순으로 입력");
                            int[] month_start_last = new int[3];
                            for (int i = 0; i < 3; i++)
                                month_start_last[i] = sc.nextInt();
                            transactionInquiryByDay(
                                    usingMemberId,
                                    usingAccountId,
                                    month_start_last[0],
                                    month_start_last[1],
                                    month_start_last[2]);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("종료");
                    run = false;
            }
        }
    }

    static Member getUser(Member member) {
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

    static int selectAccount(int memberId) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts =
                AccountDao.getInstance().getAccountNumbers(memberId);
        for (Account account : accounts)
            System.out.print(account.getAccountNumber() + " ");
        System.out.println();
        int num = sc.nextInt();

        return accounts.get(num).getAccountId();
    }

    public static void transactionInquiry(int memberId, int accountId, int isIncome) {
        ArrayList<Transaction> transactions =
                TransactionDao.getInstance().getByIsIncome(memberId,accountId, isIncome);
        for (Transaction transaction : transactions) {
            System.out.printf("날짜 : %tF 금액 : %d, 이용처 : %s\n",
                    transaction.getDate(), transaction.getAmount(), transaction.getDetail());
        }
    }

    private static void transactionInquiryByMonth(int memberId, int accountId,int month) {
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByMonth(memberId, accountId,month);
        for (Transaction transaction : transactions) {
            System.out.printf("날짜 : %tF 금액 : %d, 이용처 : %s\n",
                    transaction.getDate(), transaction.getAmount(), transaction.getDetail());
        }
    }

    private static void transactionInquiryByDay(int memberId,int accountId, int month, int startDay, int endDay) {
        ArrayList<Transaction> transactions
                = TransactionDao.getInstance().getByPeriod(memberId, accountId,month, startDay, endDay);
        for (Transaction transaction : transactions) {
            System.out.printf("날짜 : %tF 금액 : %d, 이용처 : %s\n",
                    transaction.getDate(), transaction.getAmount(), transaction.getDetail());
        }
    }


}

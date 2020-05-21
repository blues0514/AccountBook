import dao.MemberDao;
import entities.Member;
import helpers.ConnectionString;

public class Main {
    public static void main(String[] args) {
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
        Member member = new Member();
        member.setId("test2");
        member.setPassword("1234");
        member.setTargetAmount(10000);
        MemberDao.getInstance().insert(member);
        //
//        하이 준형
    }

}

import dao.AccountDao;
import dao.MemberDao;
import entities.Account;
import entities.Member;
import helpers.ConnectionString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberDaoTest {
    @BeforeAll
    static void initializeConnectionString(){
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
    }

    @Test
    void getByKeyQuery() {
        Member member = MemberDao.getInstance().getByKey(1);

        assertEquals("test", member.getId());
    }

    @Test
    void deleteByKeyQuery() {
    }

    @Test
    void readEntity() {
    }

    @Test
    void getCountQuery() {
    }

    @Test
    void getAllQuery() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }
}
import dao.AccountDao;
import entities.Account;
import helpers.ConnectionString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {

    @BeforeAll
    static void initializeConnectionString(){
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
    }

    @Test
    void getAccoutNumbers() {
        ArrayList<Account> accounts = AccountDao.getInstance().getAccountNumbers(1);

        assertTrue(accounts.size() > 0);

//        for (Account account : accounts) {
//            assertEquals(1, account.g());
//        }
    }
}
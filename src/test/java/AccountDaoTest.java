import dao.AccountDao;
import entities.Account;
import helpers.ConnectionString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountDaoTest {
    @BeforeAll
    static void initializeConnectionString(){
        ConnectionString.getInstance().initialize("jdbc:sqlserver://127.0.0.1;database=AccountBook;user=sa;password=1234");
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

    @Test
    void getByKeyQuery() {

    }

    @Test
    void deleteByKeyQuery() {
    }
}
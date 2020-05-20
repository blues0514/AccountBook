package dao;

public class AccountDao {
    //region singleton
    private AccountDao() {
    }

    private static AccountDao _instance;

    public static AccountDao getInstance() {
        if (_instance == null)
            _instance = new AccountDao();

        return _instance;
    }
//endregion
}

package dao;

import data.ParameterSetter;
import data.base.IntEntityDao;
import entities.Account;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AccountDao extends IntEntityDao<Account> {
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

    @SneakyThrows
    @Override
    protected Account readEntity(ResultSet resultSet) {
        Account entity = new Account();

        entity.setAccountId(resultSet.getInt(1));
        entity.setAccountNumber(resultSet.getString(2));
        entity.setMemberId(resultSet.getInt(3));
        entity.setBank(resultSet.getString(4));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Account";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Account";
    }

    @Override
    public boolean insert(Account entity) {
        //language=TSQL
        String query = "insert into Account values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getAccountNumber());
                statement.setInt(2, entity.getMemberId());
                statement.setString(3, entity.getBank());

            }
        });
    }

    @Override
    public boolean update(Account entity) {
        //language=TSQL
        String query = "update Account set AccountNumber = ?, MemberId = ?, Bank = ? where AccountId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getAccountNumber());
                statement.setInt(2, entity.getMemberId());
                statement.setString(3, entity.getBank());
                statement.setInt(4, entity.getAccountId());
            }
        });
    }

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from Account where AccountId = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Account where AccountId = ?";
    }

    public ArrayList<Account> getAccountNumbers(int memberId) {
        //language=TSQL
        String query = "Select * from Account where MemberId = ?";

        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setInt(1, memberId);
            }
        });
    }


}

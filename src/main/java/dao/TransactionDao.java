package dao;

import data.ParameterSetter;
import data.base.IntEntityDao;
import entities.Transaction;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionDao extends IntEntityDao<Transaction> {
    //region singleton
    private TransactionDao() {
    }

    private static TransactionDao _instance;

    public static TransactionDao getInstance() {
        if (_instance == null)
            _instance = new TransactionDao();

        return _instance;
    }

    //endregion

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from [Transaction] where TransactionId = ?";
    }

    public ArrayList<Transaction> getByTransactionCategoryId(int transactionCategoryId) {
        //language=TSQL
        String query = "SELECT * FROM [Transaction] WHERE TransactionCategoryId=?";

        return getMany(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1,transactionCategoryId);
            }
        });
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete [Transaction] where TransactionId = ?";
    }

    @SneakyThrows
    @Override
    protected Transaction readEntity(ResultSet resultSet) {
        Transaction entity = new Transaction();

        entity.setTransactionId(resultSet.getInt(1));
        entity.setAccountId(resultSet.getInt(2));
        entity.setAmount(resultSet.getInt(3));
        entity.setDate(resultSet.getDate(4));
        entity.setDetail(resultSet.getString(5));
        entity.setTransactionCategoryId(resultSet.getInt(6));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from [Transaction]";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from [Transaction]";
    }

    @Override
    public boolean insert(Transaction entity) {
        //language=TSQL
        String query = "insert into [Transaction] values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
//                statement.setInt(1, entity.getAccountId());
                statement.setInt(2, entity.getAmount());
//                statement.setDate(3, entity.getDate());
                statement.setString(4, entity.getDetail());
                statement.setInt(5, entity.getTransactionCategoryId());
            }
        });
    }

    @Override
    public boolean update(Transaction entity) {
        //language=TSQL
        String query = "update [Transaction] set AccountId = ?, Amount = ?, Date = ?, Detail = ?, TransactionCategoryId = ? where TransactionId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getAccountId());
                statement.setInt(2, entity.getAmount());
                statement.setDate(3, entity.getDate());
                statement.setString(4, entity.getDetail());
                statement.setInt(5, entity.getTransactionCategoryId());
                statement.setInt(6, entity.getTransactionId());
            }
        });
    }
}

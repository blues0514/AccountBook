package dao;

import data.ParameterSetter;
import data.base.IntEntityDao;
import entities.TransactionCategory;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TransactionCategoryDao extends IntEntityDao<TransactionCategory> {
    //region singleton
    private TransactionCategoryDao() {
    }

    private static TransactionCategoryDao _instance;

    public static TransactionCategoryDao getInstance() {
        if (_instance == null)
            _instance = new TransactionCategoryDao();

        return _instance;
    }

    //endregion
    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from TransactionCategory where TransactionCategoryId = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete TransactionCategory where TransactionCategoryId = ?";
    }

    @SneakyThrows
    @Override
    protected TransactionCategory readEntity(ResultSet resultSet) {
        TransactionCategory entity = new TransactionCategory();

        entity.setTransactionCategoryId(resultSet.getInt(1));
        entity.setName(resultSet.getString(2));
        entity.setIncome(resultSet.getBoolean(3));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from TransactionCategory";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from TransactionCategory";
    }

    @Override
    public boolean insert(TransactionCategory entity) {
        //language=TSQL
        String query = "insert into TransactionCategory values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setInt(1, entity.getTransactionCategoryId());
                statement.setString(2, entity.getName());
                statement.setBoolean(3, entity.isIncome());
            }
        });
    }

    @Override
    public boolean update(TransactionCategory entity) {
        //language=TSQL
        String query = "update TransactionCategory set Name = ?, IsIncome = ? where TransactionCategoryId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getName());
                statement.setBoolean(2, entity.isIncome());
                statement.setInt(3, entity.getTransactionCategoryId());
            }
        });
    }
}

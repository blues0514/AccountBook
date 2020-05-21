package dao;

import data.ParameterSetter;
import data.base.IntEntityDao;
import entities.Member;
import lombok.SneakyThrows;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao extends IntEntityDao<Member> {
    //region singleton
    private MemberDao() {
    }

    private static MemberDao _instance;

    public static MemberDao getInstance() {
        if (_instance == null)
            _instance = new MemberDao();

        return _instance;
    }

    //endregion

    @Override
    protected String getByKeyQuery() {
        //language=TSQL
        return "select * from Member where MemberId = ?";
    }

    @Override
    protected String deleteByKeyQuery() {
        //language=TSQL
        return "delete Member where MemberId = ?";
    }

    @SneakyThrows
    @Override
    protected Member readEntity(ResultSet resultSet) {
        Member entity = new Member();

        entity.setMemberId(resultSet.getInt(1));
        entity.setId(resultSet.getString(2));
        entity.setPassword(resultSet.getString(3));
        entity.setTargetAmount(resultSet.getInt(4));

        return entity;
    }

    @Override
    protected String getCountQuery() {
        //language=TSQL
        return "select count(*) from Member";
    }

    @Override
    protected String getAllQuery() {
        //language=TSQL
        return "select * from Member";
    }

    @Override
    public boolean insert(Member entity) {
        //language=TSQL
        String query = "insert into Member values (?, ?, ?)";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getId());
                statement.setString(2, entity.getPassword());
                statement.setInt(3, entity.getTargetAmount());
            }
        });
    }

    @Override
    public boolean update(Member entity) {
        //language=TSQL
        String query = "update Member set Id = ?, Password = ?, TargetAmount = ? where MemberId = ?";

        return execute(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement statement) {
                statement.setString(1, entity.getId());
                statement.setString(2, entity.getPassword());
                statement.setInt(3, entity.getTargetAmount());
                statement.setInt(4, entity.getMemberId());
            }
        });
    }

    public Member login(String id, String pw) {
        //language=TSQL
        String query = "Select * from Member where Id = ?";

        Member member = getOne(query, new ParameterSetter() {
            @SneakyThrows
            @Override
            public void setValue(PreparedStatement preparedStatement) {
                preparedStatement.setString(1, id);
            }
        });
        String userPw = member.getPassword();
        if (!pw.equals(userPw)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}

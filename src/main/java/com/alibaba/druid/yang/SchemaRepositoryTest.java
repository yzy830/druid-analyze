package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.repository.SchemaRepository;
import com.alibaba.druid.util.JdbcConstants;

public class SchemaRepositoryTest {
    public static void main(String[] args) {
        String sql = "select o.order_id as orderId, max(o.order_sum) as orderSum, upper(u.user_name) userName from t_d_order o "
                + "join t_d_user u on o.user_id = u.user_id "
                + "where o.order_sum > 100 group by o.user_id order by o.order_sum desc limit 0,10";
        
        SchemaRepository repository = new SchemaRepository(JdbcConstants.MYSQL);
        
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        
        SQLStatement statement = parser.parseStatement();
        
        repository.resolve((SQLSelectStatement)statement);
    }
}

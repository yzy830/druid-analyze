package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class InsertTest {
    public static void main(String[] args) {
//        String sql = "insert into t_d_order (id, name) values (1, 'test'), (2, 'test-2')";
        String sql = "insert into t_d_order (id, name) select id, name from t_d_order_back";
        
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);

        SQLInsertStatement statement = (SQLInsertStatement)sqlStatementParser.parseStatement();
        
        System.out.println(statement);
    }
}

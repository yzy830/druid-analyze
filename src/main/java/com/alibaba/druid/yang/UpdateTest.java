package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class UpdateTest {
    public static void main(String[] args) {
        String sql = "update t_d_order set order_sum = 30";
        
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);

        SQLUpdateStatement statement = (SQLUpdateStatement)sqlStatementParser.parseStatement();
        
        System.out.println(statement);
    }
}

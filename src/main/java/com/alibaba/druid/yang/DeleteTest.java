package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class DeleteTest {
    public static void main(String[] args) {
        String sql = "delete from t_d_order where order_id = 1";
          
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);
    
        SQLDeleteStatement statement = (SQLDeleteStatement)sqlStatementParser.parseStatement();
          
        System.out.println(statement);
    }
}

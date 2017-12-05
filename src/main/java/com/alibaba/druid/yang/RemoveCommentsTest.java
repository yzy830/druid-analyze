package com.alibaba.druid.yang;

import java.util.List;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class RemoveCommentsTest {
    public static void main(String[] args) {
        String sql = "/*!mycat: schema = vcity_shop*/ select /*+ BKA(t1) */ * from  t_d_shop";
        
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);

        List<SQLStatement> statements = sqlStatementParser.parseStatementList();
        
        System.out.println(statements);
        
        SQLSelectStatement selectStatement = (SQLSelectStatement)statements.get(1);
        SQLSelect select = selectStatement.getSelect();
        MySqlSelectQueryBlock queryBlock = (MySqlSelectQueryBlock)select.getQueryBlock();
        queryBlock.getHints().clear();
        
        System.out.println("after remove comments");
        
        System.out.println(statements);
    }
}

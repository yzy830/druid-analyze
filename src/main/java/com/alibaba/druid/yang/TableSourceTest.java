package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class TableSourceTest {

    public static void main(String[] args) {
        String updateSql = "update t_d_shop set shop_name = 1 where shop_id = 1";
        
        MySqlStatementParser parser = new MySqlStatementParser(updateSql);
        SQLUpdateStatement statement = (SQLUpdateStatement)parser.parseStatement();
        System.out.println(statement.getTableSource().getClass().getSimpleName());
        
        String joinSql = "select * from t_d_shop s join t_d_order o on s.shop_id = o.shop_id";
        parser = new MySqlStatementParser(joinSql);
        SQLSelectStatement selectStatement = (SQLSelectStatement)parser.parseStatement();
        System.out.println(selectStatement.getSelect().getQueryBlock().getFrom().getClass().getSimpleName());
        
        String innerView = "select * from (select * from t_d_shop where shop_id in (1,2,3)) s where s.concern > 100";
        parser = new MySqlStatementParser(innerView);
        selectStatement = (SQLSelectStatement)parser.parseStatement();
        System.out.println(selectStatement.getSelect().getQueryBlock().getFrom().getClass().getSimpleName());
        
        String union = "select * from (select * from t_d_shop where shop_id in (1,2,3) "
                + "union select * from t_d_shop where shop_id in (1,2,3)) s where s.concern > 100";
        parser = new MySqlStatementParser(union);
        selectStatement = (SQLSelectStatement)parser.parseStatement();
        System.out.println(selectStatement.getSelect().getQueryBlock().getFrom().getClass().getSimpleName());
        
        String insertSql = "insert into t_d_shop values (1, 'test')";
        parser = new MySqlStatementParser(insertSql);
        SQLInsertStatement insertStatement = (SQLInsertStatement)parser.parseStatement();
        System.out.println(insertStatement.getTableSource().getClass().getSimpleName());
    }

}

package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;

public class MySQLSchemaVisitorTest {
    public static void main(String[] args) {
        String sql = "select o.order_id as orderId, max(o.order_sum) as orderSum, upper(u.user_name) userName from t_d_order o "
                + "join t_d_user u on o.user_id = u.user_id "
                + "where o.order_sum > 100 group by o.user_id having max(o.order_sum) > 200 order by u.gender desc limit 0,10";
        
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        
        SQLStatement statement = parser.parseStatement();
        
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        statement.accept(visitor);
        
        System.out.println("columns = " + visitor.getColumns());
        System.out.println("conditions = " + visitor.getConditions());
        System.out.println("aggregates = " + visitor.getAggregateFunctions());
        System.out.println("funtions = " + visitor.getFunctions());
        System.out.println("groupByColumns = " + visitor.getGroupByColumns());
        System.out.println("orderByColumns = " + visitor.getOrderByColumns());
        System.out.println("parameters = " + visitor.getParameters());
        System.out.println("relations = " + visitor.getRelationships());
        System.out.println("tables = " + visitor.getTables());
    }
}

package com.alibaba.druid.yang;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.alibaba.druid.util.JdbcUtils;

public class App {
    public static void main( String[] args ) {
//        String sql = "select (select sub_eventId from sub_event s where s.parent_id = eventId) as sub_eventId,eventKey as event_key,eventName,flag from "
//                + "(select * from event where event.group = 'group-1') as v where v.eventId = ? and v.eventKey = ? and v.eventName = ? order by v.eventName, v.eventId";
//        
//        String sql = "select s.shop_name from vcity_shop.t_d_shop s where "
//                + "all(select order_sum from t_d_order o where o.shop_id = s.shop_id) > 100 and name = '1' group by s.gender, s.age";
//        
//        String sql = "select * from t_d_order where shop_id in (1, 2, select shop_id from t_d_shop)";
        
        String sql = "select (s.audit_status or s.status) as status from t_d_shop s";
        
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);
        
        SQLSelectStatement statement = (SQLSelectStatement)sqlStatementParser.parseStatement();
        
        SQLSelect select = statement.getSelect();
        
        select.setOrderBy(null);
        
        SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock)select.getQuery();
        
        StringBuffer out = new StringBuffer();
        SQLASTOutputVisitor outputVisitor = SQLUtils.createFormatOutputVisitor(out, null, JdbcUtils.MYSQL);
        
        for(SQLSelectItem item : sqlSelectQuery.getSelectList()) {
            if(out.length() > 1) {
                out.append(",");
            }
            item.accept(outputVisitor);
        }
        
        System.out.println(out);
    }
}

package com.alibaba.druid.yang;

import com.alibaba.druid.sql.ast.statement.SQLInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;

public class InsertTest {
    public static void main(String[] args) {
//        String sql = "insert into t_d_order (id, name) values (1, 'test'), (2, 'test-2')";
        String sql = "insert into t_d_shop_photo " + "(shop_id,photo_group,photo_url,audit_status,create_time,status ," + 
                     "photo_remark , photo_index)     " + "VALUES                   (300,'threedimPC','test','Y',now(),'Y' , 'test' , 0);";
        
        MySqlStatementParser sqlStatementParser = new MySqlStatementParser(sql);

        SQLInsertStatement statement = (SQLInsertStatement)sqlStatementParser.parseStatement();
        
        System.out.println(statement);
    }
}

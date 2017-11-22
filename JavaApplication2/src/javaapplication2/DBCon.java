/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Margaret.Wang
 */
public class DBCon {
     public static Connection JavaCon() {
        try {
            //加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //创建数据库连接
            String url="jdbc:sqlserver://localhost:1433;DatabaseName=StudentDB";
            Connection conn = DriverManager.getConnection(url,"sa", "bit2010");
            return conn;
        }catch (ClassNotFoundException ex1) {
            //捕获ClassNotFoundException异常
            ex1.printStackTrace();
            return null;
        }catch (SQLException ex2) {
            //捕获SQLException异常
            ex2.printStackTrace();
            return null;
        }
    }
    public static Vector queryData(String sql){
        //调用纯Java方式连接数据库
        Connection conn=JavaCon();
        //声明保存查询结果的Vector对象
        Vector data=new Vector();
        try{
            //在连接对象的基础上创建会话对象
            Statement stmt = conn.createStatement();
            //执行查询SQL语句，返回查询的结果集
            ResultSet rs = stmt.executeQuery(sql);
            //rs结果集中还有下一条记录
            while(rs.next()){
                //声明保存查询结果集中每行数据的Vector对象
                Vector line=new Vector();
                //将查询结果集中的每行数据保存到line对象里
                for (int i = 1; i <= 4; i++) {
                    String item=rs.getObject(i).toString();
                    if(i==4){
                        item=item.substring(0,10);
                    }
                    line.add(item);
                }
                //将每行数据的line对象添加到data对象中
                data.add(line);
            }
            //关闭结果集
            rs.close();
            //关闭会话对象
            stmt.close();
            //关闭连接对象
            conn.close();
            return data;
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public static boolean updateData(String sql) {
        //调用纯Java方式连接数据库的方法
        Connection conn=JavaCon( );
        try{
            //在连接对象的基础上创建会话对象
            Statement stmt = conn.createStatement( );
            //执行参数传入进来的插入、删除或修改的SQL语句，返回受影响的行数
            int rs = stmt.executeUpdate(sql);
            //关闭会话对象
            stmt.close( );
            //关闭连接对象
            conn.close( );
            //如果受影响的行数大于零，则更新数据成功，
            //返回true，否则更新数据失败，返回false
            if(rs>0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            ex.printStackTrace( );
            return false;
        }
    }
    public static Vector prepareQueryData(String stuID, String stuName,String sex, String birth) {
        //调用纯Java方式连接数据库
        Connection conn = JavaCon( );
        //声明保存查询结果的Vector对象
        Vector data = new Vector( );
        try {
            //在连接对象的基础上创建预编译会话对象
            PreparedStatement pstmt = conn.prepareStatement(
                        "Select * from Student where stuID=? and stuName=? and sex=? and birthday=?");
            //设置预编译参数
            pstmt.setString(1, stuID);
            pstmt.setString(2, stuName);
            pstmt.setString(3, sex);
            pstmt.setString(4, birth);
            //执行查询SQL语句，返回查询的结果集
            ResultSet rs = pstmt.executeQuery( );
            //如果rs结果集中还有下一条记录
            while (rs.next( )) {
                //声明保存查询结果集中每行数据的Vector对象
                Vector line = new Vector( );
                //将查询结果集中的每行数据保存到line对象里
                for (int i = 1; i <= 4; i++) {
                    line.add(rs.getObject(i));
                }
                //将每行数据的line对象添加到data对象中
                data.add(line);
            }
            //关闭结果集
            rs.close( );
            //关闭会话对象
            pstmt.close( );
            //关闭连接对象
            conn.close( );
            return data;
        } catch (SQLException ex) {
            ex.printStackTrace( );
            return null;
        }
    }
    public static boolean prepareUpdateData(String stuID, String stuName,String sex, String birth) {
        //调用纯Java方式连接数据库
        Connection conn = JavaCon();
        try {
            //在连接对象的基础上创建预编译会话对象
            PreparedStatement pstmt = conn.prepareStatement("insert into Student values(?,?,?,?)");
            //设置预编译参数
            pstmt.setString(1, stuID);
            pstmt.setString(2, stuName);
            pstmt.setString(3, sex);
            pstmt.setString(4, birth);
            //执行更新SQL语句，返回受影响的行数
            int r = pstmt.executeUpdate( );
            //关闭会话对象
            pstmt.close( );
            //关闭连接对象
            conn.close( );
            //如果受影响的行数大于零，则插入数据成功，
            //返回true，否则插入数据失败，返回false
            if (r > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
        }
    }
}

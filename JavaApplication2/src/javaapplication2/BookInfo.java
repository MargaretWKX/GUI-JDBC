/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.util.Vector;
/**
 *
 * @author Margaret.Wang
 */
class BookInfo {
    String BookID;
    String BookName;
    String Author;
    String Press;
    String PressDate;
    String Status;
     public void getBookInfo(Vector vBook){
         if(!vBook.isEmpty()){
        BookID=vBook.get(0).toString();
        BookName=vBook.get(1).toString();
        Author=vBook.get(2).toString();
        Press=vBook.get(3).toString();
        PressDate=vBook.get(4).toString();
        Status=vBook.get(5).toString();
         }
    }
}

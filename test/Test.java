
import DAO.UserDaos;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMIN
 */
public class Test {
    public static void main(String[] args) throws SQLException {
        UserDaos dao= new UserDaos();
        System.out.println(dao.getAllUser());
    }
}

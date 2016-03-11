package by.gstu.autobase.database.DAO;

import by.gstu.autobase.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Alexandr Kolymago on 16.11.2015.
 */
public interface AccountDAO {

    Connection getConnection() throws SQLException;

    List<Account> findAll();

    Account findAccountById(int id);

    Account findAccountByName(String login);

    boolean delete(int id);

    boolean delete(Account account);

    boolean create(Account account);

    Account update(Account account);

    List<Account> getAllDriversAccount();

    Account findAccountByCar(String carName);
}

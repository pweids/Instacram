package com.instacram.dao;


import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import com.instacram.dto.User;

public class UserDAO {
	private BeanFactory<User> factory;

	public UserDAO() throws DAOException {
		try {
            BeanTable<User> table = BeanTable.getSQLInstance(User.class, "fweiding_users", "com.mysql.jdbc.Driver", "jdbc:mysql:///hw4");
            if (!table.exists()) {
                table.create("username");
            }
            factory = table.getFactory();
        } catch (BeanFactoryException e) {
            throw new DAOException(e);
        }
    }
	
	public User create(String username) throws DAOException {
        try {
            return factory.create(username);
        } catch (DuplicateKeyException e) {
            throw new DAOException("User name already exists: "+username);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }
	
	public void delete(String username) throws DAOException {
        try {
            factory.delete(username);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public User lookup(String username) throws DAOException {
        try {
            return factory.lookup(username);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public void update(User user) throws DAOException {
        try {
            Transaction.begin();
            String username = user.getUsername();
            User dbUser = factory.lookup(username);
            if (dbUser == null) {
                throw new DAOException("User name does not exist: "+username);
            }
            dbUser.setPassword(user.getPassword());
            dbUser.setEmail(user.getEmail());
            Transaction.commit();
        } catch (RollbackException e) {
            throw new DAOException(e);
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

	public User addUser(String username, String password, String email) throws DAOException {
		User u = create(username);
		u.setPassword(password);
		u.setEmail(email);
		update(u);
		return u;
		
	}
}

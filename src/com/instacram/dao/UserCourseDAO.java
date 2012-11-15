package com.instacram.dao;


import java.util.ArrayList;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.MatchArg;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import com.instacram.dto.Course;
import com.instacram.dto.Message;
import com.instacram.dto.User;
import com.instacram.dto.UserCourse;

public class UserCourseDAO {
	private BeanFactory<UserCourse> factory;

	public UserCourseDAO() throws DAOException {
		try {
            BeanTable<UserCourse> table = BeanTable.getSQLInstance(UserCourse.class, "fweiding_usercourse", "com.mysql.jdbc.Driver", "jdbc:mysql:///hw4");
            if (!table.exists()) {
                table.create("username", "coursename");
            }
            factory = table.getFactory();
        } catch (BeanFactoryException e) {
            throw new DAOException(e);
        }
	}

	public UserCourse create(String username, String coursename) throws DAOException {
        try {
            return factory.create(username, coursename);
        } catch (DuplicateKeyException e) {
            throw new DAOException("Message name already exists");
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }
	
	public void delete(String username, String coursename) throws DAOException {
        try {
            factory.delete(username, coursename);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public UserCourse lookup(String username, String coursename) throws DAOException {
        try {
            return factory.lookup(username, coursename);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

	public UserCourse addUserCourse(String username, String coursename) throws DAOException {
		create(username, coursename);
		return new UserCourse(username, coursename);
		
	}
	
	public ArrayList<Course> getCourse(String username) throws DAOException{
		ArrayList<Course> alc = new ArrayList<Course>();
		CourseDAO cdao = new CourseDAO();
		try {
			UserCourse[] c = factory.match(MatchArg.equals("username", username));
			for (int i = 0; i < c.length; i++) {
				Course c2 = cdao.lookup(c[i].getCoursename());
				alc.add(c2);
			}
			return alc;
		} catch (RollbackException e) {
			
		}
		return null;
	}
	
	public ArrayList<User> getUsers(String coursename) throws DAOException{
		ArrayList<User> ulc = new ArrayList<User>();
		UserDAO udao = new UserDAO();
		try {
			UserCourse[] u = factory.match(MatchArg.equals("coursename", coursename));
			for (int i = 0; i < u.length; i++) {
				User u2 = udao.lookup(u[i].getCoursename());
				ulc.add(u2);
			}
			return ulc;
		} catch (RollbackException e) {
			
		}
		return null;
	}
}

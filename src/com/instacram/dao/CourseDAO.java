package com.instacram.dao;

import java.util.ArrayList;
import java.util.Arrays;

import org.mybeans.dao.DAOException;
import org.mybeans.factory.BeanFactory;
import org.mybeans.factory.BeanFactoryException;
import org.mybeans.factory.BeanTable;
import org.mybeans.factory.DuplicateKeyException;
import org.mybeans.factory.RollbackException;
import org.mybeans.factory.Transaction;

import com.instacram.dto.Course;

/**
 * @author fweiding
 *
 */
public class CourseDAO {
	private BeanFactory<Course> factory;

	public CourseDAO() throws DAOException {
		try {
            BeanTable<Course> table = BeanTable.getSQLInstance(Course.class, "fweiding_courses", "com.mysql.jdbc.Driver", "jdbc:mysql:///hw4");
            if (!table.exists()) {
                table.create("name");
            }
            factory = table.getFactory();
        } catch (BeanFactoryException e) {
            throw new DAOException(e);
        }
	}

	public Course create(String name) throws DAOException {
        try {
            return factory.create(name);
        } catch (DuplicateKeyException e) {
            throw new DAOException("Course name already exists: "+ name);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }
	
	public void delete(String name) throws DAOException {
        try {
            factory.delete(name);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public Course lookup(String name) throws DAOException {
        try {
            return factory.lookup(name);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public void update(Course course) throws DAOException {
        try {
            Transaction.begin();
            String coursename = course.getName();
            Course dbCourse = factory.lookup(coursename);
            if (dbCourse == null) {
                throw new DAOException("User name does not exist: "+ coursename);
            }
            dbCourse.setCreator(course.getCreator());
            dbCourse.setInstructor(course.getInstructor());
            Transaction.commit();
        } catch (RollbackException e) {
            throw new DAOException(e);
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

	public Course addCourse(Course c) throws DAOException {
		create(c.getName());
		update(c);
		return c;
		
	}
	
	public ArrayList<Course> getAllCourses() {
		try {
			Course[] courses = factory.match();
			return new ArrayList<Course>(Arrays.asList(courses));
		} catch (RollbackException e) {
			System.out.println("Error getting all courses: " + e);
		}
		return null;
		
	}
}

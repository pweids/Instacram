package com.instacram.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

public class MessageDAO {
	private BeanFactory<Message> factory;

	public MessageDAO() throws DAOException {
		try {
            BeanTable<Message> table = BeanTable.getSQLInstance(Message.class, "fweiding_messages", "com.mysql.jdbc.Driver", "jdbc:mysql:///hw4");
            if (!table.exists()) {
                table.create("id");
            }
            factory = table.getFactory();
        } catch (BeanFactoryException e) {
            throw new DAOException(e);
        }
	}

	public Message create(int id) throws DAOException {
        try {
            return factory.create(id);
        } catch (DuplicateKeyException e) {
            throw new DAOException("Message name already exists: "+ id);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }
	
	public void delete(int id) throws DAOException {
        try {
            factory.delete(id);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public Message lookup(int id) throws DAOException {
        try {
            return factory.lookup(id);
        } catch (RollbackException e) {
            throw new DAOException(e);
        }
    }

    public void update(Message msg) throws DAOException {
        try {
            Transaction.begin();
            int id = msg.getId();
            Message dbMsg = factory.lookup(id);
            if (dbMsg == null) {
                throw new DAOException("User name does not exist: "+ id);
            }
            dbMsg.setContent(msg.getContent());
            dbMsg.setDate(msg.getDate());
            dbMsg.setUsername(msg.getUsername());
            dbMsg.setCoursename(msg.getCoursename());
            Transaction.commit();
        } catch (RollbackException e) {
            throw new DAOException(e);
        } finally {
            if (Transaction.isActive()) Transaction.rollback();
        }
    }

	public Message addMessage(Message msg) throws DAOException {
		create(msg.getId());
		update(msg);
		return msg;
		
	}
	
	public ArrayList<Message> getAllMessages() {
		try {
			Message[] msgs = factory.match();
			if (msgs == null) {
				return null;
			}
			return sortMessages(new ArrayList<Message>(Arrays.asList(msgs)));
		} catch (RollbackException e) {
			System.out.println("Error getting all messages: " + e);
		}
		return null;
	}
	
	public ArrayList<Message> getAllMessages(String course) {
		try {
			Message[] msgs = factory.match(MatchArg.equals("coursename", course));
			if (msgs == null) {
				return null;
			}
			return sortMessages(new ArrayList<Message>(Arrays.asList(msgs)));
		} catch (RollbackException e) {
			System.out.println("Error getting all messages: " + e);
		}
		return null;
	}
	
	public ArrayList<Message> searchMessages(String search) {
		try {
			Message[] msgs = factory.match(MatchArg.contains("content", search));
			if (msgs == null) {
				return null;
			}
			return sortMessages(new ArrayList<Message>(Arrays.asList(msgs)));
		} catch (RollbackException e) {
			System.out.println("Error getting all messages: " + e);
		}
		return null;
	}
	
	public ArrayList<Message> userMessages(User usr) {
		ArrayList<Message> retMsgs = new ArrayList<Message>();
		ArrayList<Course> c = usr.getCourses();
		try {
			CourseDAO cdao = new CourseDAO();
			Message[] msgs = factory.match();
			if (msgs == null) {
				return null;
			}
			for (Message m : msgs) {
				for (Course c1 : c) {
					if (c1.getName().equals(m.getCoursename())) {
						retMsgs.add(m);
					}
				}
			}
			return sortMessages(retMsgs);
		} catch (RollbackException e) {
			System.out.println("Error getting all messages: " + e);
		} catch (DAOException e) {
			
		}
		return null;
	}
	
	private ArrayList<Message> sortMessages(ArrayList<Message> list) {
		
		Collections.sort(list, new Comparator<Message>() {
		    public int compare(Message a, Message b) {
		        return b.getDate().compareTo(a.getDate());
		    }
		});
		return list;
	}
}

package ru.HibernateApp.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.HibernateApp.Entity.Group;



public class GroupDAO implements DAO<Group, Integer> {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = null;



	@Override
	public void create(Group group) {

		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.save(group);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't create new Group", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Group read(Integer id) {
		Group group;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			group = session.get(Group.class, id);
			
			
			
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't read a Group", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return group;
	}

	@Override
	public void delete(Integer key) {
		Group group;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			group = session.get(Group.class, key);
			session.delete(group);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't delete a Group", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<Group> selectAll() {
		List<Group> groupList = new ArrayList<Group>();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			Query<Group> sel = session.createNativeQuery("select * from group_table", Group.class);
			groupList = sel.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't select all Groups", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return groupList;
	}

	@Override
	public void update(Integer key,Group updatedGroup) {
		Group group = new Group();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			group = session.get(Group.class, key);
			if (group == null) {
				throw new RuntimeException("group is null");
			}
			group.setGroupName(updatedGroup.getGroupName());
			
			session.update(group);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't update a Group", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}
}

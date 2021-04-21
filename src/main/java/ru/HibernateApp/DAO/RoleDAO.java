package ru.HibernateApp.DAO;

import java.util.List;
import ru.HibernateApp.Entity.*;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class RoleDAO implements DAO<Role, Integer> {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = null;

	@Override
	public void create(Role role) {
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.save(role);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't create a role", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Role read(Integer id) {
		Role role;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			role = session.get(Role.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't read a role", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return role;
	}

	@Override
	public void delete(Integer id) {
		Role role;

		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			role = session.get(Role.class, id);
			session.delete(role);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't delete a role", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void update(Integer id, Role updatedRole) {
		Role role = new Role();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			role = session.get(Role.class, id);
			if (role == null) {
				throw new RuntimeException("role is null");
			}
			role.setRoleName(updatedRole.getRoleName());
			session.update(role);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't update a role", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<Role> selectAll() {
		List<Role> roleList = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			Query<Role> sel = session.createNativeQuery("select * from role", Role.class);
			roleList = sel.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't select all roles", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return roleList;
	}

}

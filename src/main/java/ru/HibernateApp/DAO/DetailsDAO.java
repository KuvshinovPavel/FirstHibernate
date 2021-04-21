package ru.HibernateApp.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ru.HibernateApp.Entity.*;

public class DetailsDAO implements DAO<Details, Integer> {

	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = null;

	@Override
	public void create(Details details) {
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.save(details);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't create new details", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Details read(Integer id) {
		Details details;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			details = session.get(Details.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't read details", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return details;
	}

	@Override
	public void delete(Integer key) {
		Details details;
		
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			details = session.get(Details.class, key);
			session.delete(details);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't delete details", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public void update(Integer key, Details updatedDetails) {
		Details details = new Details();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			details = session.get(Details.class, key);
			if (details == null) {
				throw new RuntimeException("details is null");
			}
			details.setCity(updatedDetails.getCity());
			details.setCountry(updatedDetails.getCountry());
			details.setEmail(updatedDetails.getEmail());

			session.update(details);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't update details", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<Details> selectAll() {
		List<Details> detailsList = new ArrayList<>();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			Query<Details> sel = session.createNativeQuery("select * from person_info", Details.class);
			detailsList = sel.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't select all details", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return detailsList;
	}

}

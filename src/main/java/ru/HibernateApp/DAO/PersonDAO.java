package ru.HibernateApp.DAO;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ru.HibernateApp.Entity.Person;

public class PersonDAO implements DAO<Person, Integer> {
	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	Session session = null;

	@Override
	public void create(Person person) {

		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			session.save(person);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't create new Person", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public Person read(Integer id) {
		Person person;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			person = session.get(Person.class, id);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't read a Person", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return person;
	}

	@Override
	public void delete(Integer key) {
		Person person;
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			person = session.get(Person.class, key);
			session.delete(person);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't delete a Person", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	@Override
	public List<Person> selectAll() {
		List<Person> personList = new ArrayList<Person>();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			Query<Person> sel = session.createNativeQuery("select * from Person", Person.class);
			personList = sel.getResultList();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't select all persons", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return personList;
	}

	@Override
	public void update(Integer key, Person updatedPerson) {
		Person per = new Person();
		try {
			session = sessionFactory.getCurrentSession();
			session.getTransaction().begin();
			per = session.get(Person.class, key);
			if (per == null) {
				throw new RuntimeException("Person is null");
			}
			
			per.setLastName(updatedPerson.getLastName());
			per.setFirstName(updatedPerson.getFirstName());
			session.update(per);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			throw new RuntimeException("Can't update a Person", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

}

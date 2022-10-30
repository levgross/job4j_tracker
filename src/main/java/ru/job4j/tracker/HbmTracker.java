package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        int rsl = 0;
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                    "UPDATE Item SET name = :iName, created = :iCrt WHERE id = :iId")
                    .setParameter("iName", item.getName())
                    .setParameter("iCrt", item.getCreated())
                    .setParameter("iId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl != 0;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        int rsl = 0;
        try {
            session.beginTransaction();
            rsl = session.createQuery(
                            "DELETE Item WHERE id = :iId")
                    .setParameter("iId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return rsl != 0;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("FROM Item");
        List<Item> rsl = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("FROM Item WHERE name = :iName")
                .setParameter("iName", key);
        List<Item> rsl = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Query<Item> query = session.createQuery("FROM Item WHERE id = :iId")
                .setParameter("iId", id);
        Item rsl = query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return rsl;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}

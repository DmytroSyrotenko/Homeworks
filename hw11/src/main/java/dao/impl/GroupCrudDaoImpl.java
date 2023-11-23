package dao.impl;

import dao.GroupCrudDao;
import entity.Group;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GroupCrudDaoImpl implements GroupCrudDao {

    private final EntityManagerFactory emf = Persistence
            .createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public void create(Group entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Group entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        if (findById(id).isPresent()) {
            entityManager.remove(findById(id).get());
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Group.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Group> findAll() {
        return (List<Group>) entityManager.createQuery("select g from Group g").getResultList();

    }
}

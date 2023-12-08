package dao.impl;

import dao.TransactionCrudDao;
import entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Collection;
import java.util.Optional;

public class TransactionCrudDaoImpl implements TransactionCrudDao {
    private final EntityManagerFactory emf= Persistence.createEntityManagerFactory("jpa-hibernate-mysql");
    private final EntityManager entityManager = emf.createEntityManager();

    @Override
    public void create(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Transaction transaction) {
        entityManager.getTransaction().begin();
        entityManager.merge(transaction);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Long id) {
        entityManager.getTransaction().begin();
        if(findById(id).isPresent()){
            entityManager.remove(findById(id).get());
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Transaction.class,id));
    }

    @Override
    @SuppressWarnings("unchecked")/*TODO check*/
    public Collection<Transaction> findAll() {
            return entityManager.createQuery("select t from Transaction t").getResultList();
    }
}

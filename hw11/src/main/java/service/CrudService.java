package service;

import entity.BaseEntity;
import java.util.Collection;

public interface CrudService<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(Long id);

    E findOne(Long id);

    Collection<E> findAll();
}

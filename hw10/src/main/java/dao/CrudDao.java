package dao;

import entity.BaseEntity;
import paginationdata.PaginationData;
import java.util.Collection;
import java.util.Optional;

public interface CrudDao<BE extends BaseEntity> {

    void create(BE be);

    void update(BE be);

    void delete(Long id);

    Optional<BE> findById(Long id);

    Collection<BE> findAll(PaginationData data);

    boolean existsById(Long id);
}
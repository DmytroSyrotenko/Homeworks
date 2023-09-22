package qq.module1.Impl.interfaces;

import qq.module1.entity.BaseEntity;

public interface CrudService<BE extends BaseEntity> {
    void create(BE be);
    void update(BE be);
    void delete(String id);
    BE findOne(String id);
    BE[] findAll();

}

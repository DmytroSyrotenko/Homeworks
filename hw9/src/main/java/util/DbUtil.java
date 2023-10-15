package util;

import entity.BaseEntity;

import java.util.Collection;
import java.util.UUID;

public class DbUtil {


    public static  <BE extends BaseEntity> String generateId(Collection<BE> entities) {
        String id = UUID.randomUUID().toString();
        if (entities.stream().anyMatch(e -> e.getId().equals(id))) {
            generateId(entities);
        }
        return id;
    }
}

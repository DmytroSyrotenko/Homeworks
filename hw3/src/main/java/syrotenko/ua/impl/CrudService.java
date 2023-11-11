package syrotenko.ua.impl;

import syrotenko.ua.entity.Phone;

public interface CrudService<BE extends Phone> {
    void createCRUD(BE be);
    void updateCRUD(BE be) ;
    void deleteCRUD(String serialNumber);
    BE findOneCRUD(String serialNumber);
    BE[] findAllCRUD();
}

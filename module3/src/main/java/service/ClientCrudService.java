package service;

import entity.Client;

public interface ClientCrudService extends CrudService<Client> {

    Long getCurrentSessionClientId();

    void resetCurrentClientId();
}

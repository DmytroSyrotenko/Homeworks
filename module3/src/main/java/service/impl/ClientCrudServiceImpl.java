package service.impl;

import dao.ClientCrudDao;
import dao.impl.ClientCrudDaoImpl;
import entity.Client;
import service.ClientCrudService;
import java.util.Collection;
import java.util.Optional;

public class ClientCrudServiceImpl implements ClientCrudService {

    private Long currentSessionClientId;

    public Long getCurrentSessionClientId() {
        return currentSessionClientId;
    }

    @Override
    public void resetCurrentClientId() {
        this.currentSessionClientId=null;
    }

    private final ClientCrudDao clientCrudDao = new ClientCrudDaoImpl();


    @Override
    public void create(Client client) {
        clientCrudDao.create(client);
    }

    @Override
    public void update(Client client) {
        clientCrudDao.update(client);
    }

    @Override
    public void delete(Long id) {
        clientCrudDao.delete(id);
    }

    @Override
    public Client findOne(Long id) {
        Optional<Client> optionalClient = clientCrudDao.findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException("Client not found");
        }
        currentSessionClientId = optionalClient.get().getId();
        return optionalClient.get();
    }

    @Override
    public Collection<Client> findAll() {
        return clientCrudDao.findAll();
    }

}

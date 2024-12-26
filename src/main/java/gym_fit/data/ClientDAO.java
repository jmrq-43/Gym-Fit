package gym_fit.data;

import gym_fit.domain.Client;

import java.util.List;

public class ClientDAO implements IClientDAO {
    @Override
    public boolean deleteClient(Client client) {
        return false;
    }

    @Override
    public boolean modifiedClient(Client client) {
        return false;
    }

    @Override
    public boolean addClient(Client client) {
        return false;
    }

    @Override
    public boolean searchClientById(Client client) {
        return false;
    }

    @Override
    public List<Client> clientList() {
        return List.of();
    }
}

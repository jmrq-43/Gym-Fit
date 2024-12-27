package gym_fit.data;

import gym_fit.domain.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> clientList();

    boolean searchClientById(Client client);

    boolean addClient(Client client);

    boolean modifiedClient(Client client);

    boolean deleteClient(Client client);
}

package gym_fit.data;

import gym_fit.domain.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static gym_fit.connection.ConnectionDb.getConnection;

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
        List<Client> clientList = new ArrayList<>();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Connection connectionDb = getConnection();

        var sql = "SELECT * FROM  client ORDER BY id ";

        try {
            preparedStatement = connectionDb.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setMembership(resultSet.getInt("membership"));
                clientList.add(client);
            }
        } catch (Exception e) {
            System.out.println("error to list clients " + e.getMessage());
        } finally {
            try {
                connectionDb.close();
            } catch (Exception e) {
                System.out.println("error closing connection " + e.getMessage());
            }
        }
        return clientList;
    }
}
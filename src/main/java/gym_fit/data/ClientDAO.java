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
        PreparedStatement preparedStatement;
        Connection connection  = getConnection();
        var sql = "DELETE FROM client WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,client.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to deleting client " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean modifiedClient(Client client) {
        PreparedStatement preparedStatement;
        Connection connection = getConnection();
        var sql = "UPDATE client SET name=?, lastName=?, membership=? " +
                "WHERE id =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getMembership());
            preparedStatement.setInt(4, client.getId());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error modifying client " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean addClient(Client client) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        var connection = getConnection();
        var sql = "INSERT INTO client(name, lastName, membership) "
                + " VALUES(?, ?, ?) ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getMembership());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error adding client " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection " + e.getMessage());
            }
        }

        return false;
    }

    @Override
    public boolean searchClientById(Client client) {
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        var connection = getConnection();
        var sql = "SELECT * FROM client WHERE id = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client.setName(resultSet.getString("name"));
                client.setLastName(resultSet.getString("lastName"));
                client.setMembership(resultSet.getInt("membership"));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error retrieving customer by id " + e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Error closing connection " + e.getMessage());
            }
        }
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
            System.out.println("Error to list clients " + e.getMessage());
        } finally {
            try {
                connectionDb.close();
            } catch (Exception e) {
                System.out.println("Error closing connection " + e.getMessage());
            }
        }
        return clientList;
    }
}
package com.angel.dao;
import java.util.List;
import com.angel.entity.Client;
public interface IClientDAO {
    List<Client> getAllClients();
    Client getClientById(int articleId);
    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(int articleId);
    boolean clientExists(String title, String category);
}
 
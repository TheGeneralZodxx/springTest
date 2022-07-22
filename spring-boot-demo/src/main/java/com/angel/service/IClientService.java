package com.angel.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.angel.entity.Client;

public interface IClientService {
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     List<Client> getAllClients();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     Client getClientById(int clientId);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     boolean addClient(Client client);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     void updateClient(Client client);
	 @Secured ({"ROLE_ADMIN"})
     void deleteClient(int clientId);
    @Secured ({"ROLE_ADMIN", "ROLE_USER"})
    boolean addVariousClient(List<Client> clients);
}

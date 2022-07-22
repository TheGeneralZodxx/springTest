package com.angel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angel.dao.IClientDAO;
import com.angel.entity.Client;
@Service
public class ClientService implements IClientService {
	@Autowired
	private IClientDAO clientDAO;
	@Override
	public Client getClientById(int articleId) {
		Client obj = clientDAO.getClientById(articleId);
		return obj;
	}	
	@Override
	public List<Client> getAllClients(){
		return clientDAO.getAllClients();
	}
	@Override
	public synchronized boolean addClient(Client client){
       if (clientDAO.clientExists(client.getName(), client.getAddress())) {
    	   return false;
       } else {
    	   clientDAO.addClient(client);
    	   return true;
       }
	}
	@Override
	public void updateClient(Client client) {
		clientDAO.updateClient(client);
	}
	@Override
	public void deleteClient(int articleId) {
		clientDAO.deleteClient(articleId);
	}

	@Override
	public synchronized boolean addVariousClient(List<Client> clients) {
		try{
			for(Client client:clients){
				clientDAO.addClient(client);
			}
			return true;
		}catch (Exception e){
			return false;
		}
	}
}

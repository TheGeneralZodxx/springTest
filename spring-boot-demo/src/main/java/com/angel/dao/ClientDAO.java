package com.angel.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.angel.entity.Client;
@Transactional
@Repository
public class ClientDAO implements IClientDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Client getClientById(int clientId) {
		return entityManager.find(Client.class, clientId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAllClients() {
		String hql = "FROM Client as atcl ORDER BY atcl.clientId";
		return (List<Client>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addClient(Client client) {
		entityManager.persist(client);
	}
	@Override
	public void updateClient(Client client) {
		Client artcl = getClientById(client.getClientId());
		artcl.setName(client.getName());
		artcl.setAddress(client.getAddress());
		entityManager.flush();
	}
	@Override
	public void deleteClient(int articleId) {
		entityManager.remove(getClientById(articleId));
	}
	@Override
	public boolean clientExists(String title, String category) {
		String hql = "FROM Client as atcl WHERE atcl.name = ? and atcl.address = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title)
		              .setParameter(2, category).getResultList().size();
		return count > 0 ? true : false;
	}
}

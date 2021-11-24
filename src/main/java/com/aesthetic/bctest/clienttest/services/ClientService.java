package com.aesthetic.bctest.clienttest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aesthetic.bctest.clienttest.dto.ClientDTO;
import com.aesthetic.bctest.clienttest.entities.Client;
import com.aesthetic.bctest.clienttest.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	ClientRepository repository;

	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client client = obj.get();

		ClientDTO clientDto = new ClientDTO(client);

		return clientDto;
	}

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);

		Page<ClientDTO> listDto = list.map(x -> new ClientDTO(x));

		return listDto;
	}

	public ClientDTO add(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);

//		Client client = new Client(dto);
//		ClientDTO result = new ClientDTO(client);
//		repository.save(client);
//		return result;
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		Client entity = repository.getOne(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());

	}

	public void delete(Long id) {
		repository.deleteById(id);
		
	}
}

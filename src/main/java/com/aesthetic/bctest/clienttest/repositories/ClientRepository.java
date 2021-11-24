package com.aesthetic.bctest.clienttest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aesthetic.bctest.clienttest.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}

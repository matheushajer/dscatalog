package br.com.matheushajer.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matheushajer.dscatalog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

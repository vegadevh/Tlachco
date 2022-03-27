package com.tlachco.observatoriodigital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlachco.observatoriodigital.domains.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, String>{
	
	@Query(nativeQuery=true, value="Select * from public.usuarios where username = ?1")
	Optional <Usuario> findyByUserName(String username);

}

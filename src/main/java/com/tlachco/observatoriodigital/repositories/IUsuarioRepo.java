package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, String>{

}

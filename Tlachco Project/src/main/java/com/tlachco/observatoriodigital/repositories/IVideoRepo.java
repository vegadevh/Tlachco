package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlachco.observatoriodigital.domains.Video;

public interface IVideoRepo extends JpaRepository<Video, Integer> {
	
	@Query(nativeQuery = true, value="SELECT id_video, enlace, id_categoria\r\n"
			+ "	FROM public.video\r\n"
			+ "order by id_video desc;")
	public List<Video> findAllByIdOrderByDesc() throws DataAccessException;
	
	@Query(nativeQuery = true, value = "SELECT id_video, enlace, id_categoria\r\n"
			+ "	FROM public.video ORDER BY id_video DESC limit 3;")
	public List<Video> findTopVideos() throws DataAccessException;

}

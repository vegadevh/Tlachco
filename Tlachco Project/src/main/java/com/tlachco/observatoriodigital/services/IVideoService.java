package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Video;

public interface IVideoService {
	
	public List<Video> findALL() throws DataAccessException;
	
	public void save(Video video) throws DataAccessException;
	
	public void delete(Integer id_video) throws DataAccessException;
	
	public Video findOne(Integer id_video) throws DataAccessException;

}

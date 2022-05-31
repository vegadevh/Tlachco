package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.repositories.IVideoRepo;

@Service
public class VideoServiceImpl implements IVideoService {
	
	@Autowired
	public IVideoRepo videoRepo;

	@Override
	public List<Video> findALL() throws DataAccessException {
		return videoRepo.findAll();
	}

	@Override
	public void save(Video video) throws DataAccessException {
		videoRepo.save(video);
		
	}

	@Override
	public void delete(Integer id_video) throws DataAccessException {
		Video video = videoRepo.getById(id_video);
		videoRepo.delete(video);
		
	}

	@Override
	public Video findOne(Integer id_video) throws DataAccessException {
		return videoRepo.findById(id_video).orElse(null);
	}
	
	

}

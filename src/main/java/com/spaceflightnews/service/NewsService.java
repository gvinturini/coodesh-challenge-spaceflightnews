package com.spaceflightnews.service;

import com.spaceflightnews.dto.NewsDTO;
import com.spaceflightnews.entity.News;
import com.spaceflightnews.mapper.DTOMapper;
import com.spaceflightnews.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsService {

    private NewsRepository newsRepository;

    private final DTOMapper dtoMapper = DTOMapper.INSTANCE;

    public List<NewsDTO> listAll() {
        return newsRepository.findAll()
                .stream()
                .map(dtoMapper::newsToDTO)
                .collect(Collectors.toList());
    }

    public NewsDTO getById(int id) {
        News news = newsRepository.getById(id);
        return dtoMapper.newsToDTO(news);
    }

}

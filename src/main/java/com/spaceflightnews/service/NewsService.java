package com.spaceflightnews.service;

import com.spaceflightnews.dto.EventsDTO;
import com.spaceflightnews.dto.LaunchesDTO;
import com.spaceflightnews.dto.NewsDTO;
import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import com.spaceflightnews.entity.News;
import com.spaceflightnews.mapper.DTOMapper;
import com.spaceflightnews.repository.EventsRepository;
import com.spaceflightnews.repository.LaunchesRepository;
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
    private LaunchesRepository launchesRepository;
    private EventsRepository eventsRepository;

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

    public void createNews(NewsDTO newsDTO) {
        News news = dtoMapper.newsToModel(newsDTO);
        newsRepository.save(news);
        if (newsDTO.getLaunches() != null) {
            for (Launches l: newsDTO.getLaunches()) {
                LaunchesDTO launchesDTO = dtoMapper.launcesToDTO(l);
                Launches launch = dtoMapper.launcesToModel(launchesDTO);
                launch.setNews(news);
                launchesRepository.save(launch);
            }
        }

        if (newsDTO.getEvents() != null) {
            for (Events e: newsDTO.getEvents()) {
                EventsDTO eventsDTO = dtoMapper.eventsToDTO(e);
                Events events = dtoMapper.eventsToModel(eventsDTO);
                events.setNews(news);
                eventsRepository.save(events);
            }
        }

    }

}

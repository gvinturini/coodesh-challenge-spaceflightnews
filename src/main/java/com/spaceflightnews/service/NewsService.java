package com.spaceflightnews.service;

import com.spaceflightnews.dto.request.EventsDTO;
import com.spaceflightnews.dto.request.LaunchesDTO;
import com.spaceflightnews.dto.request.NewsDTO;
import com.spaceflightnews.dto.response.MessageResponseDTO;
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

    public MessageResponseDTO createNews(NewsDTO newsDTO) {
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

        return createMessageResponse(news.getId(), "Created news with ID ");
    }

    public void delete(int id) {
        News news = newsRepository.getById(id);
        if (news.getLaunches() != null) {
            for (Launches l: news.getLaunches()) {
                launchesRepository.deleteById(l.getIdSeq());
            }
        }
        if (news.getEvents() != null) {
            for (Events e: news.getEvents()) {
                eventsRepository.deleteById(e.getIdSeq());
            }
        }
        newsRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(int id, NewsDTO newsDTO) {
        News newsToUpdate = dtoMapper.newsToModel(newsDTO);
        News updatedNews = newsRepository.getById(id);
        updatedNews.setTitle(newsToUpdate.getTitle());
        updatedNews.setUrl(newsToUpdate.getUrl());
        updatedNews.setImageUrl(newsToUpdate.getImageUrl());
        updatedNews.setNewsSite(newsToUpdate.getNewsSite());
        updatedNews.setSummary(newsToUpdate.getSummary());
        updatedNews.setPublishedAt(newsToUpdate.getPublishedAt());
        updatedNews.setUpdatedAt(newsToUpdate.getUpdatedAt());
        updatedNews.setFeatured(newsToUpdate.isFeatured());

        List<Launches> newLaunches = newsToUpdate.getLaunches();
        List<Launches> updatedLaunches = updatedNews.getLaunches();
        List<Events> newEvents = newsToUpdate.getEvents();
        List<Events> updatedEvents = updatedNews.getEvents();

        if (newLaunches != null && newLaunches.size() == updatedLaunches.size()) {
            for ( int i = 0; i < updatedLaunches.size(); i++ ) {
                String newId = newLaunches.get(i).getId();
                String newProvider = newLaunches.get(i).getProvider();
                updatedLaunches.get(i).setId(newId);
                updatedLaunches.get(i).setProvider(newProvider);
                updatedLaunches.get(i).setNews(newsToUpdate);
                LaunchesDTO launchesDTO = dtoMapper.launcesToDTO(updatedLaunches.get(i));
                Launches launches = dtoMapper.launcesToModel(launchesDTO);
                launchesRepository.save(launches);
            }
        } else if (newLaunches.size() == 0) {
            for (Launches l: newLaunches) {
                LaunchesDTO launchesDTO = dtoMapper.launcesToDTO(l);
                Launches launches = dtoMapper.launcesToModel(launchesDTO);
                launches.setNews(newsToUpdate);
                launchesRepository.save(launches);
            }
        } else {
            for (int j = 0; j < newLaunches.size(); j++) {
                String newId = newLaunches.get(j).getId();
                String newProvider = newLaunches.get(j).getProvider();
                if (updatedLaunches.size() == 0 || updatedLaunches.size() < newLaunches.size()) {
                    Launches launchesToAdd = new Launches();
                    launchesToAdd.setId(newId);
                    launchesToAdd.setProvider(newProvider);
                    launchesToAdd.setNews(newsToUpdate);
                    for (int k = 0; k < updatedLaunches.size(); k++) {
                        if (updatedLaunches.get(k).getId().equals(launchesToAdd.getId())) {
                            updatedLaunches.add(launchesToAdd);
                            LaunchesDTO launchesDTO = dtoMapper.launcesToDTO(launchesToAdd);
                            Launches launches = dtoMapper.launcesToModel(launchesDTO);
                            launchesRepository.save(launches);
                        }
                    }
                } else {
                    updatedLaunches.get(j).setId(newId);
                    updatedLaunches.get(j).setProvider(newProvider);
                    updatedLaunches.get(j).setNews(newsToUpdate);
                    LaunchesDTO launchesDTO = dtoMapper.launcesToDTO(updatedLaunches.get(j));
                    Launches launches = dtoMapper.launcesToModel(launchesDTO);
                    launchesRepository.save(launches);
                }
            }
        }

        if (newEvents.size() > 0 && newEvents.size() == updatedEvents.size()) {
            for ( int i = 0; i < updatedEvents.size(); i++ ) {
                int newId = newEvents.get(i).getId();
                String newProvider = newEvents.get(i).getProvider();
                updatedEvents.get(i).setId(newId);
                updatedEvents.get(i).setProvider(newProvider);
                updatedEvents.get(i).setNews(newsToUpdate);
                EventsDTO eventsDTO = dtoMapper.eventsToDTO(updatedEvents.get(i));
                Events events = dtoMapper.eventsToModel(eventsDTO);
                eventsRepository.save(events);
            }
        } else if (newEvents.size() == 0) {
            for (Events e: newEvents) {
                EventsDTO eventsDTO = dtoMapper.eventsToDTO(e);
                Events events = dtoMapper.eventsToModel(eventsDTO);
                events.setNews(newsToUpdate);
                eventsRepository.save(events);
            }
        } else {
            for (int j = 0; j < newEvents.size(); j++) {
                int newId = newEvents.get(j).getId();
                String newProvider = newEvents.get(j).getProvider();
                if (updatedEvents.size() == 0 || updatedEvents.size() < newEvents.size()) {
                    Events eventsToAdd = new Events();
                    eventsToAdd.setId(newId);
                    eventsToAdd.setProvider(newProvider);
                    eventsToAdd.setNews(newsToUpdate);
                    for (int k = 0; k < updatedEvents.size(); k++) {
                        if (updatedEvents.get(k).getId() != eventsToAdd.getId()) {
                            updatedEvents.add(eventsToAdd);
                            EventsDTO eventsDTO = dtoMapper.eventsToDTO(eventsToAdd);
                            Events events = dtoMapper.eventsToModel(eventsDTO);
                            eventsRepository.save(events);
                        }
                    }
                } else {
                    updatedEvents.get(j).setId(newId);
                    updatedEvents.get(j).setProvider(newProvider);
                    updatedEvents.get(j).setNews(newsToUpdate);
                    EventsDTO eventsDTO = dtoMapper.eventsToDTO(updatedEvents.get(j));
                    Events events = dtoMapper.eventsToModel(eventsDTO);
                    eventsRepository.save(events);
                }

            }
        }

        newsRepository.save(updatedNews);

        return createMessageResponse(updatedNews.getId(), "Updated news with ID ");
    }

    private MessageResponseDTO createMessageResponse(int id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

}

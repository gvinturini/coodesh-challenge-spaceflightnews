package com.spaceflightnews.cronjob;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import com.spaceflightnews.entity.News;
import com.spaceflightnews.repository.EventsRepository;
import com.spaceflightnews.repository.LaunchesRepository;
import com.spaceflightnews.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.reactive.function.client.WebClient.*;

@Component
public class UpdateNewsJob {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private LaunchesRepository launchesRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Scheduled(cron = "0 0 9 * * *")
    public void saveNewArticles() {
        System.out.println(updateNewsOnDb(retrieveNews()));
    }

    private List<News> retrieveNews() {
        WebClient client = create("https://api.spaceflightnewsapi.net/v3");
        Mono<Object[]> retrievedObject = client.get()
                .uri("/articles?_limit=50&_sort=publishedAt:desc")
                .retrieve()
                .bodyToMono(Object[].class);
        Object[] retrievedObjects = retrievedObject.block();
        ObjectMapper mapper = new ObjectMapper();

        if (retrievedObjects != null) {
            List<News> retrievedNews = Arrays.stream(retrievedObjects)
                    .map(object -> mapper.convertValue(object, News.class))
                    .collect(Collectors.toList());
            return retrievedNews;
        } else {
            return null;
        }

    }

    private String updateNewsOnDb(List<News> news) {
        News lastNews = newsRepository.findByOrderByIdDesc().get(0);

        for (News currentNews : news) {
            if (currentNews.getId() != lastNews.getId()) {
                newsRepository.save(currentNews);
                if (currentNews.getLaunches().size() != 0) {
                    for (Launches l : currentNews.getLaunches()) {
                        l.setNews(currentNews);
                        launchesRepository.save(l);
                    }
                }
                if (currentNews.getEvents().size() != 0) {
                    for (Events e : currentNews.getEvents()) {
                        e.setNews(currentNews);
                        eventsRepository.save(e);
                    }
                }

            }
        }
        return "Job completed";
    }

}

package com.spaceflightnews.controller;

import com.spaceflightnews.dto.request.NewsDTO;
import com.spaceflightnews.dto.response.MessageResponseDTO;
import com.spaceflightnews.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String returnStatus() {
        return "Back-end Challenge 2021 \uD83C\uDFC5 - Space Flight News";
    }

    @GetMapping("/articles/")
    public List<NewsDTO> listNews() {
        return newsService.listAll();
    }

    @GetMapping("/articles/{id}")
    public NewsDTO getNewsById(@PathVariable int id) {
        return newsService.getById(id);
    }

    @PostMapping("/articles/")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.createNews(newsDTO);
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNews(@PathVariable int id) {
        newsService.delete(id);
    }

    @PutMapping("/articles/{id}")
    public MessageResponseDTO updateById(@PathVariable int id, @RequestBody NewsDTO newsDTO) {
        return newsService.updateById(id, newsDTO);
    }
}

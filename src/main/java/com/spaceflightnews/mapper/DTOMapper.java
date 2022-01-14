package com.spaceflightnews.mapper;

import com.spaceflightnews.dto.request.EventsDTO;
import com.spaceflightnews.dto.request.LaunchesDTO;
import com.spaceflightnews.dto.request.NewsDTO;
import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import com.spaceflightnews.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    News newsToModel(NewsDTO newsDTO);

    NewsDTO newsToDTO(News news);

    Launches launcesToModel(LaunchesDTO launchesDTO);

    LaunchesDTO launcesToDTO(Launches launches);

    Events eventsToModel(EventsDTO eventsDTO);

    EventsDTO eventsToDTO(Events events);

}

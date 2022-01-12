package com.spaceflightnews.mapper;

import com.spaceflightnews.dto.EventsDTO;
import com.spaceflightnews.dto.LaunchesDTO;
import com.spaceflightnews.dto.NewsDTO;
import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import com.spaceflightnews.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

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

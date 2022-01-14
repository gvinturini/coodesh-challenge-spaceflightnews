package com.spaceflightnews.dto.request;

import com.spaceflightnews.entity.News;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private int idSeq;

    private int id;

    private String provider;

    private News news;
}

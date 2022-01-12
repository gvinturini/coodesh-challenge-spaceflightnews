package com.spaceflightnews.dto;

import com.spaceflightnews.entity.News;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO {

    private int idSeq;

    private int id;

    private String provider;

    private News news;
}

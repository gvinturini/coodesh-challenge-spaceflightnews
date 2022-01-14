package com.spaceflightnews.dto.request;

import com.spaceflightnews.entity.News;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LaunchesDTO {

    private int idSeq;

    private String id;

    private String provider;

    private News news;
}

package com.spaceflightnews.dto;

import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private int id;

    @NotNull
    @Size(max = 300)
    private String title;

    @NotNull
    @Size(max = 185)
    private String url;

    @NotNull
    @Size(max = 250)
    private String imageUrl;

    @NotNull
    @Size(max = 25)
    private String newsSite;

    @NotNull
    @Size(max = 1220)
    private String summary;

    @NotNull
    @Size(max = 25)
    private String publishedAt;

    @NotNull
    @Size(max = 25)
    private String updatedAt;

    @NotNull
    private boolean featured;

    private Set<Launches> launches;

    private Set<Events> events;
}

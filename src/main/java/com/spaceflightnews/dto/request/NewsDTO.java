package com.spaceflightnews.dto.request;

import com.spaceflightnews.entity.Events;
import com.spaceflightnews.entity.Launches;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
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

    private List<Launches> launches;

    private List<Events> events;
}

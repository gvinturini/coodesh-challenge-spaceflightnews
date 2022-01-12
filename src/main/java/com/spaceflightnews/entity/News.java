package com.spaceflightnews.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_news")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "news_site", nullable = false)
    private String newsSite;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "published_at", nullable = false)
    private String publishedAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @Column(name = "featured", nullable = false)
    private boolean featured;

    @OneToMany(mappedBy = "news")
    @JsonManagedReference
    private Set<Launches> launches = new HashSet<>();

    @OneToMany(mappedBy = "news")
    @JsonManagedReference
    private Set<Events> events = new HashSet<>();
}

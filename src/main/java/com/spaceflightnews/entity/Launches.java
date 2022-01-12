package com.spaceflightnews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_launches")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Launches {

    @Id
    @Column(name = "id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int idSeq;

    @Column(name = "id")
    private String id;

    @Column(name = "provider")
    private String provider;

    @ManyToOne
    @JoinColumn(name = "id_news", nullable = false)
    @JsonBackReference
    private News news;

}

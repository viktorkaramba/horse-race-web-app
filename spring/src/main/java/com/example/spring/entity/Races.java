package com.example.spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Races {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int ID;
    private String name;
    private String place;
    private Timestamp date;
    private float prize;
    @ManyToMany(fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        })
    @JoinTable(name = "MEMBERS",
        joinColumns = { @JoinColumn(name = "IDRA")},
        inverseJoinColumns = { @JoinColumn(name = "IDHO")})
    private List<Horses> horses;
    @JsonProperty("ISOVER")
    private Boolean ISOVER;
}

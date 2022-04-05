package com.alkemy.disney.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "characters")
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = { @Index(columnList = "characterId", name = "characterId_index", unique = true) })
public class CharacterEntity implements Serializable{
    
    @Id
    @Column(nullable = false)
    private String characterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String history;

    @ManyToMany
    private List<MovieEntity> movies;

    @OneToOne(cascade = { CascadeType.REMOVE})
    private PhotoEntity photo; 

    public String getCharacterId() {
        return this.characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @JsonManagedReference
    public List<MovieEntity> getMovies() {
        return this.movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }


}

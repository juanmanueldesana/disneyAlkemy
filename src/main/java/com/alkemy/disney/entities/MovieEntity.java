package com.alkemy.disney.entities;

import java.io.Serializable;
import java.time.LocalDate;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "movies")
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = { @Index(columnList = "movieId", name = "movieId_index", unique = true) })
public class MovieEntity implements Serializable{
    
    @Id
    @Column(nullable = false)
    private String movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @Column(nullable = false)
    private Integer classification;

    @ManyToMany
    private List<CharacterEntity> characters;

    @OneToOne(cascade = { CascadeType.REMOVE})
    private PhotoEntity photo;

    @ManyToMany
    private List<GenreEntity> genres;

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClassification() {
        return this.classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public List<CharacterEntity> getCharacters() {
        return this.characters;
    }

    public void setCharacters(List<CharacterEntity> characters) {
        this.characters = characters;
    }

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }

    public List<GenreEntity> getGenres() {
        return this.genres;
    }

    public void setGenres(List<GenreEntity> genres) {
        this.genres = genres;
    }



}

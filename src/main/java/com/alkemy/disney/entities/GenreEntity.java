package com.alkemy.disney.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity(name = "genres")
public class GenreEntity {
    
    @Id
    @Column(nullable = false)
    private String genreId;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @OneToOne
    private PhotoEntity photo;

    @ManyToMany
    private List<MovieEntity> movies;

    
    public String getGenreId() {
        return this.genreId;
    }
    
    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public PhotoEntity getPhoto() {
        return this.photo;
    }

    public void setPhoto(PhotoEntity photo) {
        this.photo = photo;
    }

    public List<MovieEntity> getMovies() {
        return this.movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }


}

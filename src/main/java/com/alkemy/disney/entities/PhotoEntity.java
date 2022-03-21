package com.alkemy.disney.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "photos")
@Table (indexes = { @Index(columnList = "photoId", name = "index_photoId", unique = true)})
public class PhotoEntity implements Serializable{
    
    private static final long serialVersionUID = 1L; 

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    private String photoId;

    @Column(nullable = false)
    private String uri;

    @Column(nullable = false)
    private String fileName;

    public String getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

package guia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Serie {
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
    private Long id; 
    
    @Column(name="title", nullable=false)
    private String title;
    
    @Column(name="sinopse", nullable=false)
    private String synopsis;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
    
    

}

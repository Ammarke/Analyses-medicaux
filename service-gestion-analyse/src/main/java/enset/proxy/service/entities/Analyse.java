package enset.proxy.service.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Analyse {
	@Id
    private String identifiant;
    private String nom;
    private Date date;
    private double prix;
    private String resultat;
    private int status;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

}
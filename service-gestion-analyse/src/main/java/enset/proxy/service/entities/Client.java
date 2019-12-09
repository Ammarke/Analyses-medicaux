package enset.proxy.service.entities;



import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Client {
	@Id
    private String code;
    private String nom;
    private String prenom;
    private String photo;
    @DBRef
    private ArrayList<Analyse> analyses = new ArrayList<Analyse>();
}

package enset.proxy.service.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class AppRole {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleName;
	

}

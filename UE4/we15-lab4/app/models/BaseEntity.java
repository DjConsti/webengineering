package models;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import scala.Serializable;



/**
 * Base entity for all JPA classes
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }

}

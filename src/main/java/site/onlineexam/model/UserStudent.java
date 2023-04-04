package site.onlineexam.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class UserStudent extends User {
    
}

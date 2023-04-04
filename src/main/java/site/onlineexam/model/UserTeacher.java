package site.onlineexam.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("teacher")
public class UserTeacher extends User{
    
}

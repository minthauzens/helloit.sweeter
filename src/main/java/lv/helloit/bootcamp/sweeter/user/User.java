package lv.helloit.bootcamp.sweeter.user;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class User {
    @Id
    private String id;
    @Column
    private String email;
    @Column
    private String passwordHash;
}

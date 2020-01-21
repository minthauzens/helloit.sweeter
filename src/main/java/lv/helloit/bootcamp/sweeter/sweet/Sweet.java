package lv.helloit.bootcamp.sweeter.sweet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//@Entity(name = "SWEET")
@Entity
public class Sweet {
    @Id
    private String id;
    @Column
    private String content;
    @Column
    private String userId;
    @Column
    private LocalDateTime datePosted;
    @Column
    private LocalDateTime dateLastUpdate;
}

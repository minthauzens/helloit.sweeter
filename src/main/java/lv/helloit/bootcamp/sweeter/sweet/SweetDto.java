package lv.helloit.bootcamp.sweeter.sweet;

import lombok.*;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SweetDto {
    private String id;
    private String content;
    private String userId;
    private String userEmail;
    private LocalDateTime datePosted;
    private LocalDateTime dateLastUpdate;
}


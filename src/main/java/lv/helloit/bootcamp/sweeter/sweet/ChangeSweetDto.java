package lv.helloit.bootcamp.sweeter.sweet;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeSweetDto {
    @NotBlank
    private String content;
}

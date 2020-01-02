package lv.helloit.bootcamp.sweeter.sweet;

import javax.validation.constraints.NotBlank;

public class ChangeSweetDto {
    @NotBlank
    private String author;
    @NotBlank
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

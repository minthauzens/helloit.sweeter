package lv.helloit.bootcamp.sweeter.sweet;

import java.time.LocalDateTime;
import java.util.Objects;

public class Sweet {
    private Long id;
    private String content;
    private String userId;
    private LocalDateTime datePosted;
    private LocalDateTime dateLastUpdate;

    public LocalDateTime getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(LocalDateTime dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDateTime datePosted) {
        this.datePosted = datePosted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sweet sweet = (Sweet) o;
        return Objects.equals(id, sweet.id) &&
                Objects.equals(content, sweet.content) &&
                Objects.equals(userId, sweet.userId) &&
                Objects.equals(datePosted, sweet.datePosted) &&
                Objects.equals(dateLastUpdate, sweet.dateLastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, userId, datePosted, dateLastUpdate);
    }

    @Override
    public String toString() {
        return "Sweet{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", datePosted=" + datePosted +
                ", dateLastUpdate=" + dateLastUpdate +
                '}';
    }


    public static final class SweetBuilder {
        private Long id;
        private String content;
        private String userId;
        private LocalDateTime datePosted;
        private LocalDateTime dateLastUpdate;

        private SweetBuilder() {
        }

        public static SweetBuilder aSweet() {
            return new SweetBuilder();
        }

        public SweetBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public SweetBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public SweetBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public SweetBuilder withDatePosted(LocalDateTime datePosted) {
            this.datePosted = datePosted;
            return this;
        }

        public SweetBuilder withDateLastUpdate(LocalDateTime dateLastUpdate) {
            this.dateLastUpdate = dateLastUpdate;
            return this;
        }

        public Sweet build() {
            Sweet sweet = new Sweet();
            sweet.setId(id);
            sweet.setContent(content);
            sweet.setUserId(userId);
            sweet.setDatePosted(datePosted);
            sweet.setDateLastUpdate(dateLastUpdate);
            return sweet;
        }
    }
}

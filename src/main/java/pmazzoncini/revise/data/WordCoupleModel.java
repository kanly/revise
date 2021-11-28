package pmazzoncini.revise.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class WordCoupleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String italianWord;
    private String dutchWord;
    private LocalDateTime createAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItalianWord() {
        return italianWord;
    }

    public void setItalianWord(String italianWord) {
        this.italianWord = italianWord;
    }

    public String getDutchWord() {
        return dutchWord;
    }

    public void setDutchWord(String dutchWord) {
        this.dutchWord = dutchWord;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WordCoupleModel that = (WordCoupleModel) o;
        return id == that.id && Objects.equals(italianWord, that.italianWord) && Objects.equals(dutchWord, that.dutchWord) &&
            Objects.equals(createAt, that.createAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, italianWord, dutchWord, createAt);
    }

    @Override
    public String toString() {
        return "WordCoupleModel{" +
            "id=" + id +
            ", italianWord='" + italianWord + '\'' +
            ", dutchWord='" + dutchWord + '\'' +
            ", createAt=" + createAt +
            '}';
    }
}

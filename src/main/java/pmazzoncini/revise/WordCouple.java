package pmazzoncini.revise;

import pmazzoncini.revise.data.WordCoupleModel;

import java.time.LocalDateTime;

public record WordCouple(String italian, String dutch, LocalDateTime creationTime) {
    public WordCouple(WordCoupleModel dataModel) {
        this(dataModel.getItalianWord(), dataModel.getDutchWord(), dataModel.getCreateAt());
    }
}

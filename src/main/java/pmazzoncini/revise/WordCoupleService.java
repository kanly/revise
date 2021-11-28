package pmazzoncini.revise;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pmazzoncini.revise.data.WordCoupleModel;
import pmazzoncini.revise.data.WordCoupleRepository;

@Service
public class WordCoupleService implements Serializable {
    private final WordCoupleRepository wordCoupleRepository;

    @Autowired
    public WordCoupleService(WordCoupleRepository wordCoupleRepository) {
        this.wordCoupleRepository = wordCoupleRepository;
    }

    public List<WordCouple> fetchAll() {
        return StreamSupport.stream(wordCoupleRepository.findAll().spliterator(), false)
            .map(WordCouple::new)
            .collect(Collectors.toList());
    }

    public void save(WordCouple wordCouple) {
        var wordCoupleModel = new WordCoupleModel();
        wordCoupleModel.setDutchWord(wordCouple.dutch());
        wordCoupleModel.setItalianWord(wordCouple.italian());
        wordCoupleModel.setCreateAt(wordCouple.creationTime());

        wordCoupleRepository.save(wordCoupleModel);
    }

}

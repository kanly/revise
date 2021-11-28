package pmazzoncini.revise;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Stack;

@Route("questions")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class QuestionView extends VerticalLayout {

    @Autowired
    public QuestionView(WordCoupleService wordCoupleService) {
        var wordCouples = wordCoupleService.fetchAll();
        Collections.shuffle(wordCouples);

        var wordCouplesStack = new Stack<WordCouple>();
        for (int i = 0; i < Math.min(15, wordCouples.size()); i++) {
            wordCouplesStack.push(wordCouples.get(i));
        }

        createQuestion(1, wordCouplesStack.size(), wordCouplesStack);
    }

    private void createQuestion(int questionNum, int totalQuestions, Stack<WordCouple> questions) {
        removeAll();
        if (questions.isEmpty()) {
            add(new Text("Done, congratulations!"));
            return;
        }
        var question = questions.pop();
        add(new Text("Question " + questionNum + "/" + totalQuestions));
        var questionLayout = new VerticalLayout();
        var dutch = new TextField("Dutch");
        dutch.setValue(question.dutch());
        dutch.setEnabled(false);
        var italian = new TextField("Italian");
        questionLayout.add(dutch, italian);
        add(questionLayout);

        var button = new Button("Answer",
            e -> {
                e.getSource().setVisible(false);
                if (italian.getValue().equals(question.italian())) {
                    add(new Text("Correct! Awesome!"));
                } else {
                    add(new Text("Aw! Not correct, sorry!"));
                    add(new Text("The correct answer was: " + question.italian()));
                }
                add(new Button("next", event ->
                    createQuestion(questionNum + 1, totalQuestions, questions)
                ));
            });

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        button.setDisableOnClick(true);

        add(button);
    }
}

package pmazzoncini.revise;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Revise",
        shortName = "Revise",
        description = "A support app",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired WordCoupleService service) {

        var dutchWord = textField("Dutch");
        var italianWord = textField("Italian");
        var wordCoupleGrid = buildGrid(service);

        // Button click listeners can be defined as lambda expressions
        var button = new Button("Aggiungi",
                e -> {
            service.save(new WordCouple(italianWord.getValue(), dutchWord.getValue(), LocalDateTime.now()));
            wordCoupleGrid.setItems(service.fetchAll());
            wordCoupleGrid.getDataProvider().refreshAll();
            dutchWord.clear();
            italianWord.clear();
        } );

        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");

        HorizontalLayout addWordContainer = new HorizontalLayout();
        addWordContainer.add(dutchWord, italianWord, button);



        add(addWordContainer, wordCoupleGrid);
    }

    private TextField textField(String label) {
        TextField dutchWord = new TextField(label);
        dutchWord.addThemeName("bordered");
        return dutchWord;
    }

    private Grid<WordCouple> buildGrid(WordCoupleService service) {
        var wordCoupleGrid = new Grid<WordCouple>();

        wordCoupleGrid.setItems(service.fetchAll());
        wordCoupleGrid.addColumn(WordCouple::italian).setHeader("italian");
        wordCoupleGrid.addColumn(WordCouple::dutch).setHeader("dutch");
        return wordCoupleGrid;
    }

}

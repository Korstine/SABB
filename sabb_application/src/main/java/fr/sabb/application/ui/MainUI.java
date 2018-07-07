package fr.sabb.application.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.mapper.SeasonMapper;
import fr.sabb.application.data.object.Season;

@SpringUI
@Theme("valo")
public class MainUI  extends UI {

    @Autowired
    private SeasonMapper mapper;
    private Season season;

    private Grid grid = new Grid();
    private TextField name = new TextField("Name");
    private TextField website = new TextField("Website");

    @Override
    protected void init(VaadinRequest request) {
    	List seasons = mapper.getAll();

        VerticalLayout layout = new VerticalLayout(
            grid, name, website);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }


    private void setFormVisible(boolean visible) {
        name.setVisible(visible);
        website.setVisible(visible);
    }

}

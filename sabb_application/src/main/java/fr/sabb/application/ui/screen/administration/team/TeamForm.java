package fr.sabb.application.ui.screen.administration.team;

import com.vaadin.data.Binder;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.administration.CommonForm;

public class TeamForm extends CommonForm<Team> {

	private Binder<Team> binder = new Binder<>(Team.class);

	private TextField name = new TextField("Equipe");
	private ListSelect<Season> seasonSelect = new ListSelect<>("Saison");
	private ListSelect<Association> seasonAssociation = new ListSelect<>("Club porteur");
	private ListSelect<Category> seasonCategory = new ListSelect<>("Category");
	private CheckBox active = new CheckBox("Active");

	public TeamForm(TeamView view) {
		super(view);
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);

		seasonCategory.setItems(view.ui.getCategoryService().getAllActive());
		seasonCategory.setRows(2);
		seasonCategory.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setCategory(e.getFirstSelectedItem().get());
			}
		});

		seasonSelect.setItems(view.ui.getSeasonService().getAll());
		seasonSelect.setRows(2);
		seasonSelect.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setSeason(e.getFirstSelectedItem().get());
			}
		});

		seasonAssociation.setItems(view.ui.getAssociationService().getAllActive());
		seasonAssociation.setRows(2);
		seasonAssociation.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setAssociation(e.getFirstSelectedItem().get());
			}
		});
		HorizontalLayout selectors = new HorizontalLayout(seasonCategory, seasonSelect, seasonAssociation);
		addComponents(name, selectors, active, buttons);

		binder.bindInstanceFields(this);
	}

	@Override
	public Binder<Team> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}

}

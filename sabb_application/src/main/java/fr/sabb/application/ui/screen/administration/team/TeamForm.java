package fr.sabb.application.ui.screen.administration.team;

import org.springframework.stereotype.Component;

import com.vaadin.data.Binder;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.data.object.Season;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonForm;

@Component
public class TeamForm extends CommonForm<Team> {

	private Binder<Team> binder = new Binder<>(Team.class);

	private TextField name = new TextField("Equipe");
	private ListSelect<Season> selectSeason = new ListSelect<>("Saison");
	private ListSelect<Association> selectAssociation = new ListSelect<>("Club porteur");
	private ListSelect<Category> selectCategory = new ListSelect<>("Category");
	private CheckBox active = new CheckBox("Active");
	private TextField ffbbUniqueId = new TextField("Identifiant FFB");

	public TeamForm(TeamView view) {
		super(view);
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);

		selectCategory.setItems(view.getCategoryService().getAllActive());
		selectCategory.setRows(5);
		selectCategory.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setCategory(e.getFirstSelectedItem().get());
			}
		});

		selectAssociation.setItems(view.getAssociationService().getAllActive());
		selectAssociation.setRows(2);
		selectAssociation.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setAssociation(e.getFirstSelectedItem().get());
			}
		});

		selectSeason.setItems(view.getSeasonService().getCurrentSeason());
		selectSeason.setRows(1);
		selectSeason.setEnabled(false);
		
		Button reloadMatchs = new Button("Recharger les matchs");
		reloadMatchs.addClickListener(e -> {
			try {
				view.getMatchFillerBusiness().test(getItem());
			} catch (ValidationException ex) {
				reloadMatchs.setComponentError(new UserError(ex.getMessage()));
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		addComponent(reloadMatchs);

		HorizontalLayout selectors = new HorizontalLayout(selectCategory, selectAssociation, selectSeason);
		addComponents(name, selectors, ffbbUniqueId, active, buttons);

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

	@Override
	public String getValidationExceptionMessage() {
		return "Tous les champs sont obligatoires";
	}

}

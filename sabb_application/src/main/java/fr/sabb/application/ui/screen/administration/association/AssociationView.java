package fr.sabb.application.ui.screen.administration.association;

import com.vaadin.ui.Grid;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.data.object.Category;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.screen.administration.CommonForm;
import fr.sabb.application.ui.screen.administration.CommonView;

public class AssociationView  extends CommonView<Association> {

	public AssociationView(SabbUI ui) {
		super(ui);
	}

	public static final String VIEW_NAME = "Gestion des clubs";

	@Override
	public Grid<Association> getGrid() {
		return new Grid<>(Association.class);
	}

	@Override
	public SabbObjectService<Association> getService() {
		return ui.getAssociationService();
	}

	@Override
	public CommonForm<Association> getForm() {
		return new AssociationForm(this);
	}

	@Override
	public Association createItem() {
		return new Association();
	}

	@Override
	public String getButtonName() {
		return "Ajout d'un club";
	}

	@Override
	public void setColumns(Grid<Association> grid) {
		grid.setColumns("id", "name", "main", "active");
	}

}

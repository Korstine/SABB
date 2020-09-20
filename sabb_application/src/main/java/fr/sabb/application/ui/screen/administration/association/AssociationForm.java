package fr.sabb.application.ui.screen.administration.association;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Association;
import fr.sabb.application.ui.screen.CommonForm;
import fr.sabb.application.utils.SabbConstantes;

public class AssociationForm extends CommonForm<Association> {

	private Binder<Association> binder = new Binder<>(Association.class);

	private TextField name = new TextField("Club");
	private CheckBox active = new CheckBox("Active");
	private CheckBox main = new CheckBox("Club principal");
	private TextField nameFfbb = new TextField("Nommage FFBB");
	private TextField nameFfbbCtc = new TextField("Nommage FFBB CTC");
	private TextField ffbbLocation = new TextField("Valeur Plan FFBB");
	private TextField ffbbLocationBis = new TextField("Valeur Bis Plan FFBB");

	public AssociationForm(AssociationView view) {
		super(view);
		setSizeUndefined();
		Button reloadLicensee = new Button("Recharger les licenciés");
		reloadLicensee.addClickListener(e -> reloadAssociationLicensee(view));

		HorizontalLayout buttons = new HorizontalLayout(save, delete, reloadLicensee);
		addComponents(name, nameFfbb, nameFfbbCtc, ffbbLocation, ffbbLocationBis, main, active, buttons);

		binder.bindInstanceFields(this);
	}

	@Override
	public Binder<Association> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return name;
	}

	@Override
	public String getValidationExceptionMessage() {
		return null;
	}

	@Override
	public void clearAllFormFields() {
		// TODO Auto-generated method stub
	}
	
	private void reloadAssociationLicensee(AssociationView view) {
		if (getItem() == null || getItem().getId() == 0) {
			return;
		}
		if (getItem().isMain()) {
			view.getLicenseeService()
			.fillDBWithCsvFile(getItem(), String.format("C:/Users/flori/OneDrive/Basket/%s/SABB_licencies_extraction.csv", SabbConstantes.CURRENT_SEASON));
		} else {
			view.getLicenseeService()
			.fillDBWithCsvFile(getItem(), String.format("C:/Users/flori/OneDrive/Basket/%s/FBC_licencies_extraction.csv", SabbConstantes.CURRENT_SEASON));
		}
	}

}

package fr.sabb.application.ui.screen.business.licensee;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonForm;

public class LicenseeForm extends CommonForm<Licensee> {

	private Binder<Licensee> binder = new Binder<>(Licensee.class);

	private TextField numLicensee = new TextField("Num License");
	private TextField name = new TextField("Nom");
	private TextField firstname = new TextField("Prenom");
	private TextField phone = new TextField("Telephone");
	private ListSelect<Team> selectTeam = new ListSelect<>("Team");
	private TextField mail = new TextField("Mail");
	private TextField adress = new TextField("Adresse");

	public LicenseeForm(LicenseeView view) {
		super(view);
		setSizeUndefined();
		
		Button reloadLicensee = new Button("Tmp");
		reloadLicensee.addClickListener(e -> view.getLicenseeService().fillDBWithCsvFile("C:/Users/flori/OneDrive/Basket/Saison 18-19/M01_qualifies_complet_L_E_P_V.csv"));
		
		HorizontalLayout buttons = new HorizontalLayout(save, delete, reloadLicensee);

		selectTeam.setItems(view.getTeamService().getAllActiveForCurrentSeason());
		selectTeam.setRows(5);
		selectTeam.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setTeam(e.getFirstSelectedItem().get());
			}
		});

		mail.setWidth("350px");
		adress.setWidth("350px");
		numLicensee.setEnabled(false);
		name.setEnabled(false);
		firstname.setEnabled(false);
		HorizontalLayout h1 = new HorizontalLayout(numLicensee, name, firstname);
		HorizontalLayout h2 = new HorizontalLayout(selectTeam, phone, mail, adress);
		addComponents(h1, h2, buttons);
		binder.bindInstanceFields(this);
	}

	@Override
	public Binder<Licensee> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return mail;
	}

	@Override
	public String getValidationExceptionMessage() {
		return "Tous les champs sont obligatoires";
	}

	@Override
	public void clearAllFormFields() {
		selectTeam.clear();
		
	}
}

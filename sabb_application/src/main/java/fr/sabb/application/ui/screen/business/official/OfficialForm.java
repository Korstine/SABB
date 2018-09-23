package fr.sabb.application.ui.screen.business.official;

import java.util.stream.Collectors;

import com.vaadin.data.Binder;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Official;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonForm;

public class OfficialForm extends CommonForm<Official> {

	private Binder<Official> binder = new Binder<>(Official.class);

	private ListSelect<Team> teamTable = new ListSelect<>("TeamTable");
	private ListSelect<Team> teamReferee = new ListSelect<>("TeamReferee");
	private ListSelect<Licensee> selectLicenseeTable1 = new ListSelect<>("Table 1");
	private ListSelect<Licensee> selectLicenseeTable2 = new ListSelect<>("Table 2");
	private ListSelect<Licensee> selectLicenseeReferee1 = new ListSelect<>("Arbitre 1");
	private ListSelect<Licensee> selectLicenseeReferee2 = new ListSelect<>("Arbitre 2");

	private OfficialView view;

	public OfficialForm(OfficialView view) {
		super(view);
		this.view = view;
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save);

		teamTable.setItems(view.getTeamService().getAllActiveForCurrentSeason().stream()
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
		teamTable.setRows(5);
		teamTable.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setTeamTable(e.getFirstSelectedItem().get());
				enterForm();
			}
		});

		teamReferee.setItems(view.getTeamService().getAllActiveForCurrentSeason().stream()
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
		teamReferee.setRows(5);
		teamReferee.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setTeamReferee(e.getFirstSelectedItem().get());
				enterForm();
			}
		});

		HorizontalLayout h2 = new HorizontalLayout(teamTable, teamReferee);
		HorizontalLayout h3 = new HorizontalLayout();

		selectLicenseeTable1.setRows(5);
		selectLicenseeTable1.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicenseeTable1(e.getFirstSelectedItem().get());
			}
		});

		selectLicenseeTable2.setRows(5);
		selectLicenseeTable2.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicenseeTable2(e.getFirstSelectedItem().get());
			}
		});

		h3.addComponent(selectLicenseeTable1);
		h3.addComponent(selectLicenseeTable2);

		selectLicenseeReferee1.setRows(5);
		selectLicenseeReferee1.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicenseeReferee1(e.getFirstSelectedItem().get());
			}
		});

		selectLicenseeReferee2.setRows(5);
		selectLicenseeReferee2.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicenseeReferee2(e.getFirstSelectedItem().get());
			}
		});

		h3.addComponent(selectLicenseeReferee1);
		h3.addComponent(selectLicenseeReferee2);

		addComponents(h2, h3, buttons);
	}

	@Override
	public Binder<Official> getBinder() {
		return binder;
	}

	@Override
	public TextField getDefaultSelectAll() {
		return null;
	}

	@Override
	public String getValidationExceptionMessage() {
		return "Tous les champs sont obligatoires";
	}

	@Override
	public void clearAllFormFields() {
		teamTable.clear();
		teamReferee.clear();
	}

	@Override
	public void enterForm() {
		if (getItem() != null && getItem().getTeamTable() != null) {
			selectLicenseeTable1.setItems(view.getLicenseeService().getAllByTeam(getItem().getTeamTable()));
			selectLicenseeTable2.setItems(view.getLicenseeService().getAllByTeam(getItem().getTeamTable()));
			selectLicenseeTable1.markAsDirty();
			selectLicenseeTable2.markAsDirty();
		}
		if (getItem() != null && getItem().getTeamReferee() != null) {
			selectLicenseeReferee1.setItems(view.getLicenseeService().getAllByTeam(getItem().getTeamReferee()));
			selectLicenseeReferee2.setItems(view.getLicenseeService().getAllByTeam(getItem().getTeamReferee()));
			selectLicenseeReferee1.markAsDirty();
			selectLicenseeReferee2.markAsDirty();
		}
	}

}

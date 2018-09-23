package fr.sabb.application.ui.screen.business.official;

import java.util.stream.Collectors;

import com.vaadin.data.Binder;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Official;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.ui.screen.CommonForm;

public class OfficialForm extends CommonForm<Official> {

	private Binder<Official> binder = new Binder<>(Official.class);

	private ListSelect<Team> teamTable = new ListSelect<>("TeamTable");
	private ListSelect<Team> teamReferee = new ListSelect<>("TeamReferee");

	public OfficialForm(OfficialView view) {
		super(view);
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save);

		teamTable.setItems(view.getTeamService().getAllActiveForCurrentSeason().stream()
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
		teamTable.setRows(5);
		teamTable.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setTeamTable(e.getFirstSelectedItem().get());
			}
		});

		teamReferee.setItems(view.getTeamService().getAllActiveForCurrentSeason().stream()
				.sorted((t1, t2) -> t1.getName().compareTo(t2.getName())).collect(Collectors.toList()));
		teamReferee.setRows(5);
		teamReferee.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setTeamReferee(e.getFirstSelectedItem().get());
			}
		});

		HorizontalLayout h2 = new HorizontalLayout(teamTable, teamReferee);
		addComponents(h2, buttons);
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

}

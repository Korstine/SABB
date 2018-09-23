package fr.sabb.application.ui.screen.business.transport;

import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Team;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.ui.screen.CommonForm;

public class TransportForm extends CommonForm<Transport> {
	private Binder<Transport> binder = new Binder<>(Transport.class);

	private ListSelect<Team> selectTeam = new ListSelect<>("Equipe");
	private ListSelect<Match> selectMatch = new ListSelect<>("Rencontre");
	private ListSelect<Licensee> selectLicensee1 = new ListSelect<>("Transport 1");
	private ListSelect<Licensee> selectLicensee2 = new ListSelect<>("Transport 2");
	private ListSelect<Licensee> selectLicensee3 = new ListSelect<>("Transport 3");
	private Button generateTransportSheet = new Button("Génerer la feuille de transport");
	
	private Team team;

	public TransportForm(TransportView view) {
		super(view);

		selectTeam.setItems((view.getTeamService().getAll()));
		selectTeam.setRows(5);
		selectTeam.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				this.selectTeam(e.getFirstSelectedItem().get(), view);
			}
		});

		selectMatch.setEnabled(false);
		selectLicensee1.setEnabled(false);
		selectLicensee2.setEnabled(false);
		selectLicensee3.setEnabled(false);
		generateTransportSheet.setEnabled(false);

		selectMatch.setItems(view.getMatchService().getAllExt());
		selectMatch.setRows(5);
		selectMatch.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setMatch(e.getFirstSelectedItem().get());
			}
		});

		selectLicensee1.setItems(view.getLicenseeService().getAll());
		selectLicensee1.setRows(5);
		selectLicensee1.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicensee1(e.getFirstSelectedItem().get());
			}
		});

		selectLicensee2.setItems(view.getLicenseeService().getAll());
		selectLicensee2.setRows(5);
		selectLicensee2.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicensee2(e.getFirstSelectedItem().get());
			}
		});

		selectLicensee3.setItems(view.getLicenseeService().getAll());
		selectLicensee3.setRows(5);
		selectLicensee3.addSelectionListener(e -> {
			if (e.getFirstSelectedItem().isPresent()) {
				getItem().setLicensee3(e.getFirstSelectedItem().get());
			}
		});

		generateTransportSheet.addClickListener(e -> {
			try {
				view.getTransportSheetGeneratorBusiness().generateTransportSheet(this.team);
			} catch (ValidationException ex) {
				generateTransportSheet.setComponentError(new UserError(ex.getMessage()));
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		
		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		HorizontalLayout licensees = new HorizontalLayout(selectLicensee1, selectLicensee2, selectLicensee3);
		HorizontalLayout rencontres = new HorizontalLayout(selectTeam, selectMatch, generateTransportSheet);
		addComponents(rencontres, licensees, buttons);
		
	}

	private void selectTeam(Team team, TransportView view) {
		
		this.team = team;
		
		selectMatch.setEnabled(true);
		selectLicensee1.setEnabled(true);
		selectLicensee2.setEnabled(true);
		selectLicensee3.setEnabled(true);
		generateTransportSheet.setEnabled(true);

		selectMatch.setItems(view.getMatchService().getAllExtMatchByTeam(team));
		
		List<Licensee> teamLicensees = view.getLicenseeService().getAllByTeam(team);
		selectLicensee1.setItems(teamLicensees);
		selectLicensee2.setItems(teamLicensees);
		selectLicensee3.setItems(teamLicensees);

	}

	@Override
	public TextField getDefaultSelectAll() {
		return null;
	}

	@Override
	public Binder<Transport> getBinder() {
		return binder;
	}

	@Override
	public String getValidationExceptionMessage() {
		return null;
	}

	@Override
	public void clearAllFormFields() {
		// TODO Auto-generated method stub
		
	}
}

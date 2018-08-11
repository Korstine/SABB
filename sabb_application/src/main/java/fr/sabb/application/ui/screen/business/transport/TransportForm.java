package fr.sabb.application.ui.screen.business.transport;

import com.vaadin.data.Binder;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;

import fr.sabb.application.data.object.Licensee;
import fr.sabb.application.data.object.Match;
import fr.sabb.application.data.object.Transport;
import fr.sabb.application.ui.screen.CommonForm;

public class TransportForm extends CommonForm<Transport> {
	private Binder<Transport> binder = new Binder<>(Transport.class);

	private ListSelect<Match> selectMatch = new ListSelect<>("Rencontre");
	private ListSelect<Licensee> selectLicensee1 = new ListSelect<>("Licencié");

	public TransportForm(TransportView view) {
		super(view);

		selectMatch.setItems(view.getMatchService().getAll());
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

		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		addComponents(selectMatch,selectLicensee1, buttons);

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
}

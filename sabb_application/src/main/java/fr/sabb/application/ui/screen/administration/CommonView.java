package fr.sabb.application.ui.screen.administration;

import java.util.List;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.SabbObject;
import fr.sabb.application.service.SabbObjectService;
import fr.sabb.application.ui.SabbUI;

public abstract class CommonView<T extends SabbObject> extends CssLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 262342928460737393L;

	private Grid<T> grid = getGrid();
	private CommonForm<T> form;
	public SabbUI ui;

	public CommonView(SabbUI ui) {
		this.ui = ui;
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		form = getForm();

		List<T> gridItems = getService().getAll();

		setColumns(grid);

		Button addSeasonBtn = new Button(getButtonName());
		addSeasonBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			form.setItem(createItem());
		});

		HorizontalLayout main = new HorizontalLayout(grid);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);

		grid.setItems(gridItems);

		form.setVisible(false);

		grid.asSingleSelect().addValueChangeListener(e -> {
			if (e.getValue() == null) {
				form.setVisible(false);
			} else {
				form.setItem(e.getValue());
			}
		});

		HorizontalLayout formLayout = new HorizontalLayout(addSeasonBtn, form);
		VerticalLayout layout = new VerticalLayout(main, formLayout);
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponent(layout);
	}

	public abstract Grid<T> getGrid();

	public void updateList() {
		List<T> items = getService().getAll();
		grid.setItems(items);
	}

	/**
	 * @return the service
	 */
	public abstract SabbObjectService<T> getService();

	public abstract CommonForm<T> getForm();

	public abstract T createItem();

	public abstract String getButtonName();

	public abstract void setColumns(Grid<T> grid);

}

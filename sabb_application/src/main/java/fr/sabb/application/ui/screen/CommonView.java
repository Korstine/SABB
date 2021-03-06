package fr.sabb.application.ui.screen;

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

public abstract class CommonView<T extends SabbObject> extends CssLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 262342928460737393L;

	protected Grid<T> grid = getGrid();
	private CommonForm<T> form;
	protected CommonFilter<T> filter;

	public CommonView() {
		setSizeFull();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		
		/** FILTER */
		if (this.getFilter() != null) {
			addComponent(new HorizontalLayout(this.getFilter()));
		}
		
		
		form = getForm();

		List<T> gridItems = getItems();

		setColumns(grid);

		HorizontalLayout main = new HorizontalLayout(grid);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);

		grid.setItems(gridItems);
		
		Button addItemBtn = null;
		if (getButtonName() != null) {
			addItemBtn = new Button(getButtonName());
			addItemBtn.addClickListener(e -> {
				grid.asSingleSelect().clear();
				form.setItem(createItem());
			});
		}

		HorizontalLayout formLayout = null;
		if (form != null) {
			form.setVisible(false);

			grid.asSingleSelect().addValueChangeListener(e -> {
				if (e.getValue() == null) {
					form.setVisible(false);
				} else {
					form.setItem(e.getValue());
				}
			});

			if (addItemBtn == null) {
				formLayout = new HorizontalLayout(form);
			} else {
				formLayout = new HorizontalLayout(addItemBtn, form);
			}
			
		}
		
		VerticalLayout layout;
		if (formLayout != null) {
			layout = new VerticalLayout(main, formLayout);
		} else {
			layout = new VerticalLayout(main);
		}
		
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponent(layout);
	}

	public abstract Grid<T> getGrid();

	public void updateList() {
		List<T> items = getItems();
		grid.setItems(items);
		if (getFilter() != null && getFilter().getDataProvider() != null) {
			grid.setDataProvider(getFilter().getDataProvider());
			grid.markAsDirty();
		}
	}
	
	/**
	 * @return the service
	 */
	public abstract SabbObjectService<T> getService();

	public abstract CommonForm<T> getForm();

	public abstract T createItem();

	public abstract String getButtonName();

	public abstract void setColumns(Grid<T> grid);
	
	public abstract CommonFilter<T> getFilter();
	
	public List<T> getItems() {
		return getService().getAll();
	}

}

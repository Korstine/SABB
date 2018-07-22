package fr.sabb.application.ui.screen;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.ValidationException;
import fr.sabb.application.data.object.SabbObject;

public abstract class CommonForm<T extends SabbObject> extends FormLayout {

	private T item;

	private CommonView<T> view;

	public Button save = new Button("Save");
	public Button delete = new Button("Delete");
	
	public CommonForm(CommonView<T> view) {
		this.view = view;
		
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
	    save.setClickShortcut(KeyCode.ENTER);
	    
	    save.addClickListener(e -> this.save());
	    delete.addClickListener(e -> this.delete());
	    
	}

	public abstract Binder<T> getBinder();
	
	public abstract TextField getDefaultSelectAll();

	public void setItem(T item) {
		this.item = item;
		getBinder().setBean(item);
		setVisible(true);
		getDefaultSelectAll().selectAll();
	}

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	private void save() {
		try {
			view.getService().updateOrInsert(item);
		} catch (ValidationException ex) {
			save.setComponentError(new UserError(getValidationExceptionMessage()));
		}
		view.updateList();
	}

	private void delete() {
		view.getService().delete(item);
		view.updateList();
	}

	/**
	 * @return the save
	 */
	public Button getSave() {
		return save;
	}

	/**
	 * @return the delete
	 */
	public Button getDelete() {
		return delete;
	}
	
	public abstract String getValidationExceptionMessage();
}

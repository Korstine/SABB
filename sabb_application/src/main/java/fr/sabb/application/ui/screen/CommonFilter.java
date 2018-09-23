package fr.sabb.application.ui.screen;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.VerticalLayout;

import fr.sabb.application.data.object.SabbObject;

public class CommonFilter<T extends SabbObject>  extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4308695790284040539L;
	
	
	protected ListDataProvider<T> dataProvider;

	/**
	 * @return the dataProvider
	 */
	public ListDataProvider<T> getDataProvider() {
		return dataProvider;
	}
	
}

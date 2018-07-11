package fr.sabb.application.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import fr.sabb.application.service.assocation.AssociationService;
import fr.sabb.application.service.category.CategoryService;
import fr.sabb.application.service.season.SeasonService;
import fr.sabb.application.ui.screen.MainScreen;

@SpringUI
@Theme("valo")
public class SabbUI  extends UI {

	@Autowired
    private SeasonService seasonService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private AssociationService associationService;
   
    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("SabbApp");
        showMainView();
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen(SabbUI.this));
        getNavigator().navigateTo(getNavigator().getState());
    }

    public static SabbUI get() {
        return (SabbUI) UI.getCurrent();
    }

	/**
	 * @return the seasonService
	 */
	public SeasonService getSeasonService() {
		return seasonService;
	}

	/**
	 * @return the categoryService
	 */
	public CategoryService getCategoryService() {
		return categoryService;
	}

	/**
	 * @return the associationService
	 */
	public AssociationService getAssociationService() {
		return associationService;
	}

}

package fr.sabb.application.ui.screen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

import fr.sabb.application.ui.SabbUI;
import fr.sabb.application.ui.screen.administration.association.AssociationView;
import fr.sabb.application.ui.screen.administration.category.CategoryView;
import fr.sabb.application.ui.screen.administration.season.SeasonView;
import fr.sabb.application.ui.screen.administration.subcategory.SubCategoryView;
import fr.sabb.application.ui.screen.administration.team.TeamView;
import fr.sabb.application.ui.screen.business.licensee.LicenseeView;
import fr.sabb.application.ui.screen.business.match.MatchView;
import fr.sabb.application.ui.screen.business.transport.TransportView;
import fr.sabb.application.ui.view.AboutView;
import fr.sabb.application.ui.view.ErrorView;

public class MainScreen extends HorizontalLayout {
    private Menu menu;
    
    public MainScreen(SabbUI ui) {

        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        menu.addView(ui.getSeasonView(), SeasonView.VIEW_NAME,
        		 SeasonView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(ui.getCategoryView(), CategoryView.VIEW_NAME,
        		CategoryView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(ui.getSubCategoryView(), SubCategoryView.VIEW_NAME,
        		SubCategoryView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(ui.getAssociationView(), AssociationView.VIEW_NAME,
        		AssociationView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(ui.getTeamView(), TeamView.VIEW_NAME,
        		TeamView.VIEW_NAME, VaadinIcons.EDIT);
        menu.addView(ui.getMatchView(), MatchView.VIEW_NAME,
        		MatchView.VIEW_NAME, VaadinIcons.LIST);
        menu.addView(ui.getLicenseeView(), LicenseeView.VIEW_NAME,
        		LicenseeView.VIEW_NAME, VaadinIcons.LIST);
        menu.addView(ui.getTransportView(), TransportView.VIEW_NAME,
        		TransportView.VIEW_NAME, VaadinIcons.LIST);
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
        		VaadinIcons.INFO_CIRCLE);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}

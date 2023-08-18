package com.example.application.views;

import com.example.application.data.entity.Persons;
import com.example.application.data.service.PersonsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("Stats | HBYS CEM")
public class DashboardView extends VerticalLayout {
    private final PersonsService personsService;

    public DashboardView(PersonsService personsService) {
        this.personsService = personsService;
        addClassName("dashboard-view");
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        add(getPersonStats(), getPersonChart());
    }

    private Component getPersonStats() {
        Span stats = new Span(personsService.countPersons() + " persons");
        stats.addClassNames("text-xl", "mt-m");
        return stats;
    }

    private Chart getPersonChart() {
        Chart chart = new Chart(ChartType.PIE);

        DataSeries dataSeries = new DataSeries();
        for (Persons person : personsService.findAllPersons(null)) {
            dataSeries.add(
                    new DataSeriesItem(person.getFirstName() + " " + person.getLastName(), person.getPatient().size()));
        }

        chart.getConfiguration().setSeries(dataSeries);
        return chart;
    }
}

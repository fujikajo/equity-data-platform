package com.tikalabs.portfolio.start;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DummyBodyDataProvider;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.tikalabs.core.gui.components.table.AbstractTableDataClient;
import com.tikalabs.core.gui.components.table.IControlComposite;

public abstract class AbstractNatTableView extends AbstractTableDataClient implements IControlComposite {

	private String name = null;

	public AbstractNatTableView(String name) {
		this.setName(name);
	}

	@Override
	public Control createControl(Composite parent) {

		ViewportLayer layer = new ViewportLayer(
				new SelectionLayer(new DataLayer(new DummyBodyDataProvider(1000000, 1000000))));
		layer.setRegionName(GridRegion.BODY);

		return new NatTable(parent, layer);

	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

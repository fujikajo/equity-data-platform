package com.tikalabs.portfolio.start;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tikalabs.core.gui.swt.action.ExitAction;
import com.tikalabs.core.gui.swt.utils.GuiTools;
import com.tikalabs.core.gui.swt.utils.MyShellListener;
import com.tikalabs.portfolio.controller.StockPortfolioController;

public class AppViewer extends ApplicationWindow implements SelectionListener {

	// Erstellen Sie ein Logger-Objekt für die Klasse MyClass
	private static final Logger logger = LoggerFactory.getLogger(AppViewer.class);

	private int screenWidth;
	private int screenHeight;

	public AppViewer(Shell parentShell) {
		this(parentShell, 1080, 700);
	}

	public AppViewer(Shell parentShell, int width, int height) {
		super(parentShell);
		logger.info("Opening View...");
		parentShell.addShellListener(new MyShellListener(this.getParentShell().getDisplay(), parentShell));
		this.setScreenWidth(width);
		this.setScreenHeight(height);
		this.setBlockOnOpen(true);
		this.addMenuBar();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addStatusLine();
	}

	@Override
	protected void configureShell(Shell newShell) {

		super.configureShell(newShell);
		newShell.setSize(this.getScreenWidth(), this.getScreenHeight());
		newShell.setText("AppViewer");
		GuiTools.centerShellOnScreen(newShell);

	}

	@Override
	protected MenuManager createMenuManager() {

		MenuManager menuManager = new MenuManager();
		MenuManager fileMenu = new MenuManager("&File");

		Action _exitAction = new ExitAction(this.getShell());

		fileMenu.add(new Separator());
		fileMenu.add(_exitAction);

		menuManager.add(fileMenu);
		return menuManager;

	}

	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);

		// Lädt das größere Bild
		Image originalImage = new Image(Display.getCurrent(), "img/csv-import-64.png");

		// Skaliert das Bild auf die gewünschte Größe (z.B. 24x24 Pixel)
		Image scaledImage = new Image(Display.getCurrent(), originalImage.getImageData().scaledTo(32, 32));

		// Erstellt einen ImageDescriptor aus dem skalierten Bild
		ImageDescriptor scaledImageDescriptor = ImageDescriptor.createFromImage(scaledImage);

		// Adds tool bar items using actions.
		final Action actionForward = new Action("&Import", scaledImageDescriptor) {
			@Override
			public void run() {
				logger.info("starting Importdialog...");
				StockPortfolioController controller = new StockPortfolioController();
				controller.execute();
			}
		};

		actionForward.setAccelerator(SWT.CTRL + 'F');

		ActionContributionItem openItem = new ActionContributionItem(actionForward);
		openItem.setMode(ActionContributionItem.MODE_FORCE_TEXT);
		// toolBarManager.add(new Separator());
		toolBarManager.add(openItem);
		return toolBarManager;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

	private int getScreenWidth() {
		return screenWidth;
	}

	private void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	private int getScreenHeight() {
		return screenHeight;
	}

	private void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

}

package com.mifmif.gefmmat.testbed.student;

/***********************************************************************
 * Copyright (c) 2004, 2007 Actuate Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Actuate Corporation - initial API and implementation
 ***********************************************************************/

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.birt.chart.api.ChartEngine;
import org.eclipse.birt.chart.device.IDeviceRenderer;
import org.eclipse.birt.chart.exception.ChartException;
import org.eclipse.birt.chart.factory.GeneratedChartState;
import org.eclipse.birt.chart.factory.Generator;
import org.eclipse.birt.chart.model.Chart;
import org.eclipse.birt.chart.model.ChartWithAxes;
import org.eclipse.birt.chart.model.attribute.AxisType;
import org.eclipse.birt.chart.model.attribute.Bounds;
import org.eclipse.birt.chart.model.attribute.ColorDefinition;
import org.eclipse.birt.chart.model.attribute.IntersectionType;
import org.eclipse.birt.chart.model.attribute.LineAttributes;
import org.eclipse.birt.chart.model.attribute.LineStyle;
import org.eclipse.birt.chart.model.attribute.MarkerType;
import org.eclipse.birt.chart.model.attribute.Position;
import org.eclipse.birt.chart.model.attribute.TickStyle;
import org.eclipse.birt.chart.model.attribute.impl.BoundsImpl;
import org.eclipse.birt.chart.model.attribute.impl.ColorDefinitionImpl;
import org.eclipse.birt.chart.model.component.Axis;
import org.eclipse.birt.chart.model.component.Series;
import org.eclipse.birt.chart.model.component.impl.SeriesImpl;
import org.eclipse.birt.chart.model.data.NumberDataSet;
import org.eclipse.birt.chart.model.data.SeriesDefinition;
import org.eclipse.birt.chart.model.data.impl.NumberDataSetImpl;
import org.eclipse.birt.chart.model.data.impl.SeriesDefinitionImpl;
import org.eclipse.birt.chart.model.impl.ChartWithAxesImpl;
import org.eclipse.birt.chart.model.layout.Legend;
import org.eclipse.birt.chart.model.layout.Plot;
import org.eclipse.birt.chart.model.type.LineSeries;
import org.eclipse.birt.chart.model.type.impl.LineSeriesImpl;
import org.eclipse.birt.core.framework.PlatformConfig;

/**
 * Generates a combination of live chart (Line chart + bar chart) using a Swing
 * JPanel.
 */

public final class ChartViewer extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * A chart model instance
	 */
	private Chart cm = null;

	/**
	 * The swing rendering device
	 */
	private IDeviceRenderer dRenderer = null;

	/**
	 * Maintains the structure of the chart for quick refresh
	 */
	private transient GeneratedChartState gcState = null;

	/**
	 * Used in building the chart for the first time
	 */
	private boolean bFirstPaint = true;

	private boolean bDisposed = false;

	/**
	 * execute application
	 * 
	 * @param result
	 * 
	 * @param args
	 */
	public void start(String trustModelName, Map<String, Map<Double, Double>> datas) {
		cm = createSuccessTaskChart(trustModelName, datas);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(this, BorderLayout.CENTER);

		// Center window on the screen
		Dimension dScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dApp = new Dimension(800, 480);
		frame.setSize(dApp);
		frame.setLocation((dScreen.width - dApp.width) / 2, (dScreen.height - dApp.height) / 2);

		frame.setTitle(this.getClass().getName() + " [device=" + this.dRenderer.getClass().getName() + "]");//$NON-NLS-1$//$NON-NLS-2$
		frame.setVisible(true);

		// Add a listener to close the TimerTask
		frame.addWindowListener(new WindowListener() {

			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			public void windowClosed(WindowEvent e) {

			}

			public void windowClosing(WindowEvent e) {
				bDisposed = true;
			}

			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * Constructor
	 */
	ChartViewer() {
		try {
			PlatformConfig config = new PlatformConfig();
			config.setProperty("STANDALONE", "true"); //$NON-NLS-1$ //$NON-NLS-2$
			dRenderer = ChartEngine.instance(config).getRenderer("dv.SWING");//$NON-NLS-1$
		} catch (ChartException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Called to refresh the panel that renders the chart
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		dRenderer.setProperty(IDeviceRenderer.GRAPHICS_CONTEXT, g2d);
		Dimension d = getSize();
		Bounds bo = BoundsImpl.create(0, 0, d.width, d.height);
		bo.scale(72d / dRenderer.getDisplayServer().getDpiResolution());

		final Generator gr = Generator.instance();

		try {
			gcState = gr.build(dRenderer.getDisplayServer(), cm, bo, null, null, null);
		} catch (ChartException ex) {
			ex.printStackTrace();
		}

		if (bFirstPaint) {
			bFirstPaint = false;
			Timer t = new Timer();
			t.schedule(new ChartRefresh(), 1000);
		}
		try {
			gr.render(dRenderer, gcState);
		} catch (ChartException ex) {
			ex.printStackTrace();
		}
	}

	public Chart createSuccessTaskChart(String trustModelName, Map<String, Map<Double, Double>> datas) {
		ChartWithAxes cwaBar = ChartWithAxesImpl.create();

		// Plot
		cwaBar.getBlock().setBackground(ColorDefinitionImpl.WHITE());
		Plot p = cwaBar.getPlot();
		p.getClientArea().setBackground(ColorDefinitionImpl.create(255, 255, 225));

		// Legend
		Legend lg = cwaBar.getLegend();
		LineAttributes lia = lg.getOutline();
		lg.getText().getFont().setSize(16);
		lia.setStyle(LineStyle.SOLID_LITERAL);
		lg.getInsets().setLeft(10);
		lg.getInsets().setRight(10);

		// Title
		cwaBar.getTitle().getLabel().getCaption().setValue("Trust Model Name: " + trustModelName);//$NON-NLS-1$

		// X-Axis
		Axis xAxisPrimary = cwaBar.getPrimaryBaseAxes()[0];

		xAxisPrimary.setType(AxisType.TEXT_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.VALUE_LITERAL);
		xAxisPrimary.getOrigin().setType(IntersectionType.MIN_LITERAL);

		xAxisPrimary.getTitle().getCaption().setValue("Tasks");//$NON-NLS-1$
		xAxisPrimary.setTitlePosition(Position.BELOW_LITERAL);

		xAxisPrimary.getLabel().getCaption().getFont().setRotation(75);
		xAxisPrimary.setLabelPosition(Position.BELOW_LITERAL);

		xAxisPrimary.getMajorGrid().setTickStyle(TickStyle.BELOW_LITERAL);
		xAxisPrimary.getMajorGrid().getLineAttributes().setStyle(LineStyle.SOLID_LITERAL);
		xAxisPrimary.getMajorGrid().getLineAttributes().setColor(ColorDefinitionImpl.create(64, 64, 64));
		xAxisPrimary.getMajorGrid().getLineAttributes().setVisible(false);

		// Y-Axis
		Axis yAxisPrimary = cwaBar.getPrimaryOrthogonalAxis(xAxisPrimary);

		yAxisPrimary.getLabel().getCaption().setValue("Success Rate");//$NON-NLS-1$
		yAxisPrimary.getLabel().getCaption().getFont().setRotation(37);
		yAxisPrimary.setLabelPosition(Position.LEFT_LITERAL);

		yAxisPrimary.setTitlePosition(Position.LEFT_LITERAL);
		yAxisPrimary.getTitle().getCaption().setValue("Linear Value Y-Axis");//$NON-NLS-1$

		yAxisPrimary.setType(AxisType.LINEAR_LITERAL);

		yAxisPrimary.getMajorGrid().setTickStyle(TickStyle.LEFT_LITERAL);
		yAxisPrimary.getMajorGrid().getLineAttributes().setStyle(LineStyle.SOLID_LITERAL);
		yAxisPrimary.getMajorGrid().getLineAttributes().setColor(ColorDefinitionImpl.GREEN());
		yAxisPrimary.getMajorGrid().getLineAttributes().setVisible(false);

		// X-Series
		Series seCategory = SeriesImpl.create();
		SeriesDefinition sdX = SeriesDefinitionImpl.create();
		xAxisPrimary.getSeriesDefinitions().add(sdX);
		sdX.getSeries().add(seCategory);

		// Y-Series (1)
		// BarSeries bs1 = (BarSeries) BarSeriesImpl.create();
		//		bs1.setSeriesIdentifier("Unit Price");//$NON-NLS-1$
		// bs1.setRiserOutline(null);
		// bs1.setRiser(RiserType.RECTANGLE_LITERAL);

		// Y-Series (2)
		int counter = 0;
		for (Entry<String, Map<Double, Double>> categoryBehavior : datas.entrySet()) {
			++counter;
			String agentBehavior = categoryBehavior.getKey();
			Double[] xAxis = categoryBehavior.getValue().keySet().toArray(new Double[0]);
			Double[] yAxis = categoryBehavior.getValue().values().toArray(new Double[0]);

			NumberDataSet categoryValues = NumberDataSetImpl.create(xAxis);
			NumberDataSet dataSet = NumberDataSetImpl.create(yAxis);

			LineSeries ls1 = (LineSeries) LineSeriesImpl.create();
			ls1.setSeriesIdentifier(agentBehavior);//$NON-NLS-1$
			ColorDefinition[] listColors = new ColorDefinition[5];
			listColors[0] = ColorDefinitionImpl.GREEN();
			listColors[1] = ColorDefinitionImpl.RED();
			listColors[2] = ColorDefinitionImpl.BLUE();
			listColors[3] = ColorDefinitionImpl.CREAM();
			listColors[4] = ColorDefinitionImpl.BLACK();


			ColorDefinition selectedColor = listColors[counter % 5];
			
			

			
			ls1.getLineAttributes().setVisible(true);
			ls1.getLineAttributes().setStyle(LineStyle.SOLID_LITERAL);
 			for (int i = 0; i < ls1.getMarkers().size(); i++) {
				ls1.getMarkers().get(i).setType(MarkerType.CROSS_LITERAL);
				ls1.getMarkers().get(i).getOutline().setColor(ColorDefinitionImpl.GREY());
				ls1.getMarkers().get(i).setSize(1);
				ls1.getMarkers().get(i).setVisible(false);
			}
			SeriesDefinition sdY = SeriesDefinitionImpl.create();
			yAxisPrimary.getSeriesDefinitions().add(sdY);
			sdY.getSeriesPalette().getEntries().add(selectedColor);
//			sdY.getSeriesPalette().shift(-1);

			ls1.setCurve(false);
			ls1.setDataSet(dataSet);
			seCategory.setDataSet(categoryValues);
			sdY.getSeries().add(ls1);

		}
		return cwaBar;
	}

	/**
	 * A background thread that scrolls/refreshes the chart (offscreeen)
	 */
	private final class ChartRefresh extends TimerTask {

		public final void run() {
			while (!bDisposed) {
				final Generator gr = Generator.instance();

				// Refresh
				try {
					gr.refresh(gcState);
				} catch (ChartException ex) {
					ex.printStackTrace();
				}
				repaint();

				// Delay
				try {
					Thread.sleep(50000000);
				} catch (InterruptedException iex) {
					iex.printStackTrace();
				}
			}
		}
	}
}
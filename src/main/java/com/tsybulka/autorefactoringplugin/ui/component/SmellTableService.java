package com.tsybulka.autorefactoringplugin.ui.component;

import com.intellij.ui.components.JBScrollPane;
import com.tsybulka.autorefactoringplugin.model.metric.ClassMetricType;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.architecture.ArchitectureSmell;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.implementation.ImplementationSmell;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.metric.ClassMetrics;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.test.TestSmell;
import com.tsybulka.autorefactoringplugin.ui.UiBundle;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SmellTableService {

	private static final String SMELL_TYPE_COLUMN = UiBundle.message("report.table.column.smell.type");
	private static final String PACKAGE_COLUMN = UiBundle.message("report.table.column.package");
	private static final String DESCRIPTION_COLUMN = UiBundle.message("report.table.column.description");
	private static final String CLASS_NAME_COLUMN = UiBundle.message("report.table.column.class.name");
	private static final String METHOD_COLUMN = UiBundle.message("report.table.column.method");

	public JPanel showArchitectureSmellsTable(List<ArchitectureSmell> architectureSmellList, JPanel panel) {
		JTable table = createTable(panel);
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn(SMELL_TYPE_COLUMN);
		model.addColumn(PACKAGE_COLUMN);
		model.addColumn(CLASS_NAME_COLUMN);
		model.addColumn(METHOD_COLUMN);
		model.addColumn(DESCRIPTION_COLUMN);

		table.getColumn(SMELL_TYPE_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(PACKAGE_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(CLASS_NAME_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(METHOD_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(DESCRIPTION_COLUMN).setCellRenderer(new TextAreaRenderer());

		table.getColumn(SMELL_TYPE_COLUMN).setPreferredWidth(150);
		table.getColumn(DESCRIPTION_COLUMN).setPreferredWidth(250);

		for (ArchitectureSmell smell : architectureSmellList) {
			Object[] row = new Object[5];

			row[0] = smell.getName();
			row[1] = smell.getClassPackage();
			row[2] = smell.getClassName();
			row[3] = smell.getMethodName();
			row[4] = smell.getDescription();

			model.addRow(row);
		}

		return showTable(panel, table);
	}

	public JPanel showImplementationSmellsTable(List<ImplementationSmell> implementationSmellList, JPanel panel) {
		JTable table = createTable(panel);
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn(SMELL_TYPE_COLUMN);
		model.addColumn(PACKAGE_COLUMN);
		model.addColumn(CLASS_NAME_COLUMN);
		model.addColumn(METHOD_COLUMN);
		model.addColumn(DESCRIPTION_COLUMN);

		table.getColumn(SMELL_TYPE_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(DESCRIPTION_COLUMN).setCellRenderer(new TextAreaRenderer());

		table.getColumn(SMELL_TYPE_COLUMN).setPreferredWidth(170);
		table.getColumn(DESCRIPTION_COLUMN).setPreferredWidth(300);

		for (ImplementationSmell smell : implementationSmellList) {
			Object[] row = new Object[5];

			row[0] = smell.getName();
			row[1] = smell.getClassPackage();
			row[2] = smell.getClassName();
			row[3] = smell.getMethodName();
			row[4] = smell.getDescription();

			model.addRow(row);
		}

		// Adjusting row height based on the content
		adjustRowHeights(table);

		return showTable(panel, table);
	}

	public JPanel showClassMetricsTable(List<ClassMetrics> classMetricsList, JPanel panel) {
		JTable table = createTable(panel);
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);

		ClassMetricType[] metricTypes = ClassMetricType.values();

		model.addColumn(PACKAGE_COLUMN);
		model.addColumn(CLASS_NAME_COLUMN);

		table.getColumn(CLASS_NAME_COLUMN).setPreferredWidth(20);

		for (ClassMetricType metricType : metricTypes) {
			model.addColumn(metricType.getValue());
		}

		for (ClassMetrics classMetrics : classMetricsList) {
			Object[] row = new Object[13];

			row[0] = classMetrics.getPackageName();
			row[1] = classMetrics.getClassName();

			int rowIndex = 2;

			for (ClassMetricType metricType : metricTypes) {
				row[rowIndex] = classMetrics.getMetrics().get(metricType).toString();
				rowIndex++;
			}

			model.addRow(row);
		}

		return showTable(panel, table);
	}

	public JPanel showTestSmellsTable(List<TestSmell> testSmellList, JPanel panel) {
		JTable table = createTable(panel);
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn(SMELL_TYPE_COLUMN);
		model.addColumn(PACKAGE_COLUMN);
		model.addColumn(CLASS_NAME_COLUMN);
		model.addColumn(METHOD_COLUMN);
		model.addColumn(DESCRIPTION_COLUMN);

		table.getColumn(SMELL_TYPE_COLUMN).setCellRenderer(new TextAreaRenderer());
		table.getColumn(DESCRIPTION_COLUMN).setCellRenderer(new TextAreaRenderer());

		table.getColumn(SMELL_TYPE_COLUMN).setPreferredWidth(170);
		table.getColumn(DESCRIPTION_COLUMN).setPreferredWidth(300);

		for (TestSmell smell : testSmellList) {
			Object[] row = new Object[5];

			row[0] = smell.getName();
			row[1] = smell.getClassPackage();
			row[2] = smell.getClassName();
			row[3] = smell.getMethodName();
			row[4] = smell.getDescription();

			model.addRow(row);
		}
		return showTable(panel, table);
	}

	private JPanel showTable(JPanel panel, JTable table) {
		JScrollPane scrollPane = new JBScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(900, 150));
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		panel.setLayout(new BorderLayout());
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();

		return panel;
	}

	private JTable createTable(JPanel panel) {
		JTable table = new JTable() {
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(false);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, centerRenderer);
		panel.removeAll();
		return table;
	}

	private void adjustRowHeights(JTable table) {
		for (int row = 0; row < table.getRowCount(); row++) {
			int rowHeight = table.getRowHeight();
			for (int column = 0; column < table.getColumnCount(); column++) {
				Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
				rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
			}
			table.setRowHeight(row, rowHeight);
		}
	}

}

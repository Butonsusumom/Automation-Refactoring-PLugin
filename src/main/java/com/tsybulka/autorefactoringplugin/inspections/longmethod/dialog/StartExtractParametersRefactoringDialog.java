package com.tsybulka.autorefactoringplugin.inspections.longmethod.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class StartExtractParametersRefactoringDialog extends DialogWrapper {

	public StartExtractParametersRefactoringDialog(Project project) {
		super(project, true);
		setTitle("Start Method Refactoring to Reduce Method Length.");
		init();
	}

	protected void init() {
		super.init();

	}

	@Override
	protected JComponent createNorthPanel() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = JBUI.insets(4, 0, 4, 4);

		//first line
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Complex methods with numerous parameters can be difficult to manage and understand."), gbc);

		//second line
		gbc.gridy++;
		panel.add(new JLabel("Extracting these parameters into a distinct object can enhance clarity and organization."), gbc);

		//third line
		gbc.gridwidth = 1;
		gbc.gridy++;
		panel.add(new JLabel("Click Start Refactoring to begin step by step method refactoring."), gbc);

		return panel;
	}

	@Nullable
	@Override
	protected JComponent createCenterPanel() {
		return null;
	}

	@NotNull
	@Override
	protected Action getOKAction() {
		Action okAction = super.getOKAction();
		okAction.putValue(Action.NAME, "Start Refactoring");
		return okAction;
	}

}

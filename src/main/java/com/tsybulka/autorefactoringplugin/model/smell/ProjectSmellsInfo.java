package com.tsybulka.autorefactoringplugin.model.smell;

import com.tsybulka.autorefactoringplugin.model.smell.codesmell.architecture.ArchitectureSmell;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.implementation.ImplementationSmell;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.metric.ClassMetrics;
import com.tsybulka.autorefactoringplugin.model.smell.codesmell.test.TestSmell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Object used to collect all code analyses data to present on UI
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSmellsInfo {

	@Builder.Default
	private List<ImplementationSmell> implementationSmellsList = new ArrayList<>();
	@Builder.Default
	private List<ArchitectureSmell> architectureSmellList = new ArrayList<>();
	@Builder.Default
	private List<TestSmell> testSmellsList = new ArrayList<>();
	@Builder.Default
	private List<ClassMetrics> classMetricsList = new ArrayList<>();

	@Builder.Default
	private Integer totalImplementationSmells = 0;
	@Builder.Default
	private Integer totalTestSmells = 0;
	@Builder.Default
	private Integer totalArchitectureSmells = 0;

	public ProjectSmellsInfo(List<ImplementationSmell> implementationSmellsList, List<ArchitectureSmell> architectureSmellList, List<TestSmell> testSmellsList, List<ClassMetrics> classMetricsList) {
		this.implementationSmellsList = implementationSmellsList;
		this.architectureSmellList = architectureSmellList;
		this.testSmellsList = testSmellsList;
		this.classMetricsList = classMetricsList;
		this.totalImplementationSmells = implementationSmellsList.size();
		this.totalTestSmells = testSmellsList.size();
		this.totalArchitectureSmells = architectureSmellList.size();
	}
}


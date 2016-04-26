package demo.clarity.api.impl;

import java.util.List;

import com.genologics.ri.project.Project;
import com.genologics.ri.sample.Sample;

public interface ClarityRepo {
	List<Sample> getSamples();
	List<Project> getProjects();
}
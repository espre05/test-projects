package demo.clarity.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import demo.clarity.AppConfigDEV;
import demo.clarity.AppConfigLocal;
import demo.clarity.api.impl.ClarityRepoImpl;
import demo.clarity.api.impl.ClarityRepoSpringImpl;

import com.genologics.ri.project.Project;
import com.genologics.ri.project.ProjectLink;
import com.genologics.ri.project.Projects;
import com.genologics.ri.sample.Sample;
import com.genologics.ri.sample.SampleLink;
import com.genologics.ri.sample.Samples;

public class TestClarityRepo {

	@Test
	public void restServerStandalone() {
		ClarityRepoImpl serv =  new ClarityRepoImpl(AppConfigDEV.defaultHost());
		List<Sample> samples= serv.getSamples();
		assertTrue("Atleast one sample", samples.size() > 0);

		List<Project> projects= serv.getProjects();
		assertTrue("Atleast one sample", projects.size() > 0);
	}

}

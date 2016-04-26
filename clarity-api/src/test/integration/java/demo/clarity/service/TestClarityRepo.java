package demo.clarity.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import demo.clarity.AppConfigDEV;
import demo.clarity.AppConfigLocal;
import demo.clarity.api.impl.ClarityRepo;
import demo.clarity.api.impl.ClarityRepoImpl;
import demo.clarity.api.impl.ClarityRepoSpringImpl;

import com.genologics.ri.project.Project;
import com.genologics.ri.project.ProjectLink;
import com.genologics.ri.project.Projects;
import com.genologics.ri.sample.Sample;
import com.genologics.ri.sample.SampleLink;
import com.genologics.ri.sample.Samples;

public class TestClarityRepo {
	ClarityRepo serv ;
	@Before
	public void init(){
		serv =  new ClarityRepoImpl(AppConfigDEV.defaultHost());
	}
	@Test
	public void getSample() {
		List<Sample> samples= serv.getSamples();
		assertTrue("Atleast one sample", samples.size() > 0);
	}
	@Test
	public void getProjects() {
		List<Project> projects= serv.getProjects();
		assertTrue("Atleast one sample", projects.size() > 0);
	}

}

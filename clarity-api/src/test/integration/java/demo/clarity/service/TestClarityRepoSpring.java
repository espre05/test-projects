package demo.clarity.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

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

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AppConfigLocal.class)
@SpringApplicationConfiguration(classes = AppConfigDEV.class)
public class TestClarityRepoSpring {
	@Autowired
	ClarityRepo serv;
	@Test
	public void getSample() {
		List<Sample> samples = serv.getSamples();
		assertTrue("Atleast one sample", samples.size() > 0);
	}
	public void getProjects() {
		List<Project> projects= serv.getProjects();
		assertTrue("Atleast one sample", projects.size() > 0);
	}

}

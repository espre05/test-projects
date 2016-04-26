package demo.clarity.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.genologics.ri.artifact.Sample;
import com.genologics.ri.project.Project;
import com.genologics.ri.project.ProjectLink;
import com.genologics.ri.project.Projects;
import com.genologics.ri.sample.SampleLink;
import com.genologics.ri.sample.Samples;

import demo.clarity.ClarityMeta;


public class Util {
	
	private static final Map<Class, ClarityMeta> myMap;
    static {
        Map<Class, ClarityMeta> aMap = new HashMap<>();
        aMap.put(Sample.class, new ClarityMeta(Samples.class, SampleLink.class));
        aMap.put(Project.class, new ClarityMeta(Projects.class, ProjectLink.class));
        myMap = Collections.unmodifiableMap(aMap);
    }
    
    public static ClarityMeta getMeta(Class classType){
    	ClarityMeta parentClass = myMap.get(classType);
    	return parentClass;
    }

}

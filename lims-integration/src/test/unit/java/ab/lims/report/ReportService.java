package ab.lims.report;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ab.lims.util.LimsUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {
  
//  report.baseDir.path
  
  
  public void generateReport(Report.ReportType reportType){
     String sourceFileName = "/ab/lims/report/template/testReport2.jasper";
     InputStream reportFileStream = LimsUtil.getResourceAsStream(sourceFileName);

     List<String> dummyData = Arrays.asList("DummyBoy");

     JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dummyData, false);

     Map<String,Object> parameters = new HashMap<String,Object>();
     /**
      * Passing ReportTitle and Author as parameters
      */
     parameters.put("report.title", "MRD Report");
     parameters.put("report.date", "YYYY-MM-DD");
     parameters.put("patient.name", "Jane,Doe");
     parameters.put("patient.dateOfBirth", "2015-01-01");
     parameters.put("patient.receptorList", "IgG,IgH");

     try {
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportFileStream, parameters, beanColDataSource);

        JasperViewer.viewReport(jasperPrint, false);
     } catch (JRException e) {
        e.printStackTrace();
     }
  }
}

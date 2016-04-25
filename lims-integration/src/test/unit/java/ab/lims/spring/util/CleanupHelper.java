package ab.lims.spring.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class CleanupHelper{
	public static void cleanup(JdbcTemplate jdbcTemplate, String undoSql) {
		List<String> undoSqlList = getUndoSqlList(undoSql);
		int tryCount = 0, TRY_MAX = 5;
		while (undoSqlList.isEmpty() == false) {
			if(tryCount > TRY_MAX){
				throw new RuntimeException("Incomplete cleanup not able to delete="+ undoSqlList);
			}
			tryCount++;
			Iterator<String> undoIterator = undoSqlList.iterator();
			while (undoIterator.hasNext()) {
				String delSql = undoIterator.next();
				if (delSql.isEmpty()) {
					undoIterator.remove();
					continue;
				}
				try {
					jdbcTemplate.execute(delSql);
					undoIterator.remove();
				} catch (Exception e) {
					// swallow?
					e.printStackTrace();
				}
			} // undoIterator
		}
	}
	
	public static List<String> getUndoSqlList(String undoSql){
		List<String> undoSqlList=  new ArrayList<String>(10);

		String [] undoItems = undoSql.split("\\|");
		for(String curUndo : undoItems){
			if (curUndo.isEmpty()) continue;
			String [] keyVal = curUndo.split("=");
			String entityName = keyVal[0];
			String entityId = keyVal[1];
			String delSql = getDelStatement(entityName, entityId);
			undoSqlList.add(delSql);
		}
		return undoSqlList;
	}
	
	private static String getDelStatement(String entityName, String entityVal){
		StringBuilder sb = new StringBuilder(100);
		sb.append("delete from ");
		String tableName = null;
		String delcolumnName = null;
		switch (entityName) {
		case "SAMPLE":
			tableName = "s_sample";
			delcolumnName = "s_sampleid";
			break;
		case "SEQSAMPLE":
			tableName = "u_seqsample";
			delcolumnName = "u_seqsampleid";
			break;
		case "REQUEST":
			tableName = "s_request";
			delcolumnName = "s_requestid";
			break;
		case "RACKTUBE":
			tableName = "s_RACKTUBE";
			delcolumnName = "s_RACKTUBEid";
			break;
		case "LOCCONTENT":
			tableName = "s_LOCCONTENT";
			delcolumnName = "s_LOCCONTENTid";
			break;
		default:
			throw new RuntimeException("Unknown Key or Val entityName="+entityName +" , val="+ entityVal);	
		}
		return "delete from "+ tableName+ " where " + delcolumnName +"='" + entityVal + "'";
	}
}
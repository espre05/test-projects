/**
 * MappingMapTosubmission.java
 *
 * This file was generated by MapForce 2011.
 *
 * YOU SHOULD NOT MODIFY THIS FILE, BECAUSE IT WILL BE
 * OVERWRITTEN WHEN YOU RE-RUN CODE GENERATION.
 *
 * Refer to the MapForce Documentation for further details.
 * http://www.altova.com/mapforce
 */
package com.mapforce;

import com.altova.mapforce.*;
import com.altova.types.*;
import com.altova.xml.*;
import com.altova.text.tablelike.*;
import com.altova.text.*;
import com.altova.text.edi.*;
import java.util.*;

public class MappingMapTosubmission extends com.altova.TraceProvider 
{
	static class seq1_Main implements IEnumerable
	{
		com.altova.mapforce.IMFNode var1_instance_Ondemand_MapForce;
	
		public seq1_Main(com.altova.mapforce.IMFNode var1_instance_Ondemand_MapForce)
		{
			this.var1_instance_Ondemand_MapForce = var1_instance_Ondemand_MapForce;
		}

		public IEnumerator enumerator() {return new Enumerator(this);}
		
		public static class Enumerator implements IEnumerator
		{
			int pos = 0;
			int state = 1;
			Object current;
			seq1_Main closure;
			public Enumerator(seq1_Main closure) 
			{
				this.closure = closure;
			}
			
			public Object current() {return current;}
			
			public int position() {return pos;}
			
			public boolean moveNext() throws Exception
			{
				while (state != 0)
				{
					switch (state) 
					{
					case 1:	if (moveNext_1()) return true; break;
 					}
				}
				return false;
			}

			private boolean moveNext_1() throws Exception {
				state = 0;				
				current = new MFElement("SUBMISSION", "", "", (new seq2_content_SUBMISSION(closure.var1_instance_Ondemand_MapForce)));
				pos++;
				return true;
			}

			
			public void close()
			{
				try
				{
				}
				catch (Exception e)
				{
				}
			}
		}
				
	}
	static class seq2_content_SUBMISSION implements IEnumerable
	{
		com.altova.mapforce.IMFNode var2_Ondemand_MapForce;
	
		public seq2_content_SUBMISSION(com.altova.mapforce.IMFNode var2_Ondemand_MapForce)
		{
			this.var2_Ondemand_MapForce = var2_Ondemand_MapForce;
		}

		public IEnumerator enumerator() {return new Enumerator(this);}
		
		public static class Enumerator implements IEnumerator
		{
			int pos = 0;
			int state = 1;
			Object current;
			seq2_content_SUBMISSION closure;
			com.altova.mapforce.IEnumerable var9_select_Root;
			IEnumerator var1_map_select_Root;
			IEnumerator var3_map_select_SplitOnce__HeaderAndConent;
			IEnumerator var8_map_select_Root;
			IEnumerator var4_map_select_RepeatSplit_HeaderRows;
			IEnumerator var10_map_select_SplitOnce__HeaderAndConent;
			IEnumerator var5_map_select_SwitchEachContentLine;
			IEnumerator var15_map_select_Root;
			IEnumerator var11_map_select_RepeatSplit_HeaderRows;
			IEnumerator var6_map_select_L1__ONDEMANDJob;
			IEnumerator var16_map_select_SplitOnce__HeaderAndConent;
			IEnumerator var12_map_select_SwitchEachContentLine;
			com.altova.mapforce.IEnumerable var7_select_reportId;
			IEnumerator var17_map_select_RepeatSplit_HeaderRows;
			IEnumerator var13_map_select_L1__ONDEMANDJob;
			IEnumerator var18_map_select_SwitchEachContentLine;
			com.altova.mapforce.IEnumerable var14_select_description;
			IEnumerator var19_map_select_L5__Archive_File_Name_;
			com.altova.mapforce.IEnumerable var20_select_archiveFileName;
			public Enumerator(seq2_content_SUBMISSION closure) 
			{
				this.closure = closure;
			}
			
			public Object current() {return current;}
			
			public int position() {return pos;}
			
			public boolean moveNext() throws Exception
			{
				while (state != 0)
				{
					switch (state) 
					{
					case 1:	if (moveNext_1()) return true; break;
					case 5:	if (moveNext_5()) return true; break;
					case 6:	if (moveNext_6()) return true; break;
					case 7:	if (moveNext_7()) return true; break;
					case 10:	if (moveNext_10()) return true; break;
					case 11:	if (moveNext_11()) return true; break;
					case 14:	if (moveNext_14()) return true; break;
					case 15:	if (moveNext_15()) return true; break;
					case 18:	if (moveNext_18()) return true; break;
					case 19:	if (moveNext_19()) return true; break;
					case 22:	if (moveNext_22()) return true; break;
					case 23:	if (moveNext_23()) return true; break;
					case 33:	if (moveNext_33()) return true; break;
					case 34:	if (moveNext_34()) return true; break;
					case 37:	if (moveNext_37()) return true; break;
					case 38:	if (moveNext_38()) return true; break;
					case 41:	if (moveNext_41()) return true; break;
					case 42:	if (moveNext_42()) return true; break;
					case 45:	if (moveNext_45()) return true; break;
					case 46:	if (moveNext_46()) return true; break;
					case 49:	if (moveNext_49()) return true; break;
					case 50:	if (moveNext_50()) return true; break;
					case 60:	if (moveNext_60()) return true; break;
					case 61:	if (moveNext_61()) return true; break;
					case 64:	if (moveNext_64()) return true; break;
					case 65:	if (moveNext_65()) return true; break;
					case 68:	if (moveNext_68()) return true; break;
					case 69:	if (moveNext_69()) return true; break;
					case 72:	if (moveNext_72()) return true; break;
					case 73:	if (moveNext_73()) return true; break;
					case 76:	if (moveNext_76()) return true; break;
					case 77:	if (moveNext_77()) return true; break;
 					}
				}
				return false;
			}

			private boolean moveNext_1() throws Exception {
				state = 5;				
				var9_select_Root = new com.altova.functions.Core.SequenceCache((closure.var2_Ondemand_MapForce).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "Root")));
				current = new MFAttribute("noNamespaceSchemaLocation", "http://www.w3.org/2001/XMLSchema-instance", "xsi", com.altova.functions.Core.box("D:/_WebDownloads/submission.xsd"));
				pos++;
				return true;
			}
			private boolean moveNext_5() throws Exception {
				state = 6;				
				var1_map_select_Root = (var9_select_Root).enumerator();
				return false;
			}
			private boolean moveNext_6() throws Exception {
				state = 10;				
				if (!var1_map_select_Root.moveNext()) {state = 7; return false; }
				var3_map_select_SplitOnce__HeaderAndConent = ((((com.altova.mapforce.IMFNode)(var1_map_select_Root.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SplitOnce: HeaderAndConent"))).enumerator();
				return false;
			}
			private boolean moveNext_7() throws Exception {
				state = 33;				
				var1_map_select_Root.close(); var1_map_select_Root = null;
				var8_map_select_Root = (var9_select_Root).enumerator();
				return false;
			}
			private boolean moveNext_10() throws Exception {
				state = 14;				
				if (!var3_map_select_SplitOnce__HeaderAndConent.moveNext()) {state = 11; return false; }
				var4_map_select_RepeatSplit_HeaderRows = ((((com.altova.mapforce.IMFNode)(var3_map_select_SplitOnce__HeaderAndConent.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "RepeatSplit HeaderRows"))).enumerator();
				return false;
			}
			private boolean moveNext_11() throws Exception {
				state = 6;				
				var3_map_select_SplitOnce__HeaderAndConent.close(); var3_map_select_SplitOnce__HeaderAndConent = null;
				return false;
			}
			private boolean moveNext_14() throws Exception {
				state = 18;				
				if (!var4_map_select_RepeatSplit_HeaderRows.moveNext()) {state = 15; return false; }
				var5_map_select_SwitchEachContentLine = ((((com.altova.mapforce.IMFNode)(var4_map_select_RepeatSplit_HeaderRows.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SwitchEachContentLine"))).enumerator();
				return false;
			}
			private boolean moveNext_15() throws Exception {
				state = 10;				
				var4_map_select_RepeatSplit_HeaderRows.close(); var4_map_select_RepeatSplit_HeaderRows = null;
				return false;
			}
			private boolean moveNext_18() throws Exception {
				state = 22;				
				if (!var5_map_select_SwitchEachContentLine.moveNext()) {state = 19; return false; }
				var6_map_select_L1__ONDEMANDJob = ((((com.altova.mapforce.IMFNode)(var5_map_select_SwitchEachContentLine.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "L1: ONDEMANDJob"))).enumerator();
				return false;
			}
			private boolean moveNext_19() throws Exception {
				state = 14;				
				var5_map_select_SwitchEachContentLine.close(); var5_map_select_SwitchEachContentLine = null;
				return false;
			}
			private boolean moveNext_22() throws Exception {
				state = 22;				
				if (!var6_map_select_L1__ONDEMANDJob.moveNext()) {state = 23; return false; }
				var7_select_reportId = new com.altova.functions.Core.SequenceCache((((com.altova.mapforce.IMFNode)(var6_map_select_L1__ONDEMANDJob.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "reportId")));
				if (!(com.altova.functions.Core.exists(var7_select_reportId))) {state = 22; return false; }
				current = new MFElement("reportId", "", null, com.altova.functions.Core.box(com.altova.CoreTypes.nodeToString((com.altova.mapforce.IMFNode)com.altova.functions.Core.first(var7_select_reportId))));
				pos++;
				return true;
			}
			private boolean moveNext_23() throws Exception {
				state = 18;				
				var6_map_select_L1__ONDEMANDJob.close(); var6_map_select_L1__ONDEMANDJob = null;
				return false;
			}
			private boolean moveNext_33() throws Exception {
				state = 37;				
				if (!var8_map_select_Root.moveNext()) {state = 34; return false; }
				var10_map_select_SplitOnce__HeaderAndConent = ((((com.altova.mapforce.IMFNode)(var8_map_select_Root.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SplitOnce: HeaderAndConent"))).enumerator();
				return false;
			}
			private boolean moveNext_34() throws Exception {
				state = 60;				
				var8_map_select_Root.close(); var8_map_select_Root = null;
				var15_map_select_Root = (var9_select_Root).enumerator();
				return false;
			}
			private boolean moveNext_37() throws Exception {
				state = 41;				
				if (!var10_map_select_SplitOnce__HeaderAndConent.moveNext()) {state = 38; return false; }
				var11_map_select_RepeatSplit_HeaderRows = ((((com.altova.mapforce.IMFNode)(var10_map_select_SplitOnce__HeaderAndConent.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "RepeatSplit HeaderRows"))).enumerator();
				return false;
			}
			private boolean moveNext_38() throws Exception {
				state = 33;				
				var10_map_select_SplitOnce__HeaderAndConent.close(); var10_map_select_SplitOnce__HeaderAndConent = null;
				return false;
			}
			private boolean moveNext_41() throws Exception {
				state = 45;				
				if (!var11_map_select_RepeatSplit_HeaderRows.moveNext()) {state = 42; return false; }
				var12_map_select_SwitchEachContentLine = ((((com.altova.mapforce.IMFNode)(var11_map_select_RepeatSplit_HeaderRows.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SwitchEachContentLine"))).enumerator();
				return false;
			}
			private boolean moveNext_42() throws Exception {
				state = 37;				
				var11_map_select_RepeatSplit_HeaderRows.close(); var11_map_select_RepeatSplit_HeaderRows = null;
				return false;
			}
			private boolean moveNext_45() throws Exception {
				state = 49;				
				if (!var12_map_select_SwitchEachContentLine.moveNext()) {state = 46; return false; }
				var13_map_select_L1__ONDEMANDJob = ((((com.altova.mapforce.IMFNode)(var12_map_select_SwitchEachContentLine.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "L1: ONDEMANDJob"))).enumerator();
				return false;
			}
			private boolean moveNext_46() throws Exception {
				state = 41;				
				var12_map_select_SwitchEachContentLine.close(); var12_map_select_SwitchEachContentLine = null;
				return false;
			}
			private boolean moveNext_49() throws Exception {
				state = 49;				
				if (!var13_map_select_L1__ONDEMANDJob.moveNext()) {state = 50; return false; }
				var14_select_description = new com.altova.functions.Core.SequenceCache((((com.altova.mapforce.IMFNode)(var13_map_select_L1__ONDEMANDJob.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "description")));
				if (!(com.altova.functions.Core.exists(var14_select_description))) {state = 49; return false; }
				current = new MFElement("reportDesc", "", null, com.altova.functions.Core.box(com.altova.CoreTypes.nodeToString((com.altova.mapforce.IMFNode)com.altova.functions.Core.first(var14_select_description))));
				pos++;
				return true;
			}
			private boolean moveNext_50() throws Exception {
				state = 45;				
				var13_map_select_L1__ONDEMANDJob.close(); var13_map_select_L1__ONDEMANDJob = null;
				return false;
			}
			private boolean moveNext_60() throws Exception {
				state = 64;				
				if (!var15_map_select_Root.moveNext()) {state = 61; return false; }
				var16_map_select_SplitOnce__HeaderAndConent = ((((com.altova.mapforce.IMFNode)(var15_map_select_Root.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SplitOnce: HeaderAndConent"))).enumerator();
				return false;
			}
			private boolean moveNext_61() throws Exception {
				state = 0;				
				var15_map_select_Root.close(); var15_map_select_Root = null;
				return false;
			}
			private boolean moveNext_64() throws Exception {
				state = 68;				
				if (!var16_map_select_SplitOnce__HeaderAndConent.moveNext()) {state = 65; return false; }
				var17_map_select_RepeatSplit_HeaderRows = ((((com.altova.mapforce.IMFNode)(var16_map_select_SplitOnce__HeaderAndConent.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "RepeatSplit HeaderRows"))).enumerator();
				return false;
			}
			private boolean moveNext_65() throws Exception {
				state = 60;				
				var16_map_select_SplitOnce__HeaderAndConent.close(); var16_map_select_SplitOnce__HeaderAndConent = null;
				return false;
			}
			private boolean moveNext_68() throws Exception {
				state = 72;				
				if (!var17_map_select_RepeatSplit_HeaderRows.moveNext()) {state = 69; return false; }
				var18_map_select_SwitchEachContentLine = ((((com.altova.mapforce.IMFNode)(var17_map_select_RepeatSplit_HeaderRows.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "SwitchEachContentLine"))).enumerator();
				return false;
			}
			private boolean moveNext_69() throws Exception {
				state = 64;				
				var17_map_select_RepeatSplit_HeaderRows.close(); var17_map_select_RepeatSplit_HeaderRows = null;
				return false;
			}
			private boolean moveNext_72() throws Exception {
				state = 76;				
				if (!var18_map_select_SwitchEachContentLine.moveNext()) {state = 73; return false; }
				var19_map_select_L5__Archive_File_Name_ = ((((com.altova.mapforce.IMFNode)(var18_map_select_SwitchEachContentLine.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "L5: Archive File Name "))).enumerator();
				return false;
			}
			private boolean moveNext_73() throws Exception {
				state = 68;				
				var18_map_select_SwitchEachContentLine.close(); var18_map_select_SwitchEachContentLine = null;
				return false;
			}
			private boolean moveNext_76() throws Exception {
				state = 76;				
				if (!var19_map_select_L5__Archive_File_Name_.moveNext()) {state = 77; return false; }
				var20_select_archiveFileName = new com.altova.functions.Core.SequenceCache((((com.altova.mapforce.IMFNode)(var19_map_select_L5__Archive_File_Name_.current()))).select(IMFNode.MFQueryKind_ChildrenByQName, new javax.xml.namespace.QName("", "archiveFileName")));
				if (!(com.altova.functions.Core.exists(var20_select_archiveFileName))) {state = 76; return false; }
				current = new MFElement("archiveFileName", "", null, com.altova.functions.Core.box(com.altova.CoreTypes.nodeToString((com.altova.mapforce.IMFNode)com.altova.functions.Core.first(var20_select_archiveFileName))));
				pos++;
				return true;
			}
			private boolean moveNext_77() throws Exception {
				state = 72;				
				var19_map_select_L5__Archive_File_Name_.close(); var19_map_select_L5__Archive_File_Name_ = null;
				return false;
			}

			
			public void close()
			{
				try
				{
				var6_map_select_L1__ONDEMANDJob.close(); var6_map_select_L1__ONDEMANDJob = null;
				var5_map_select_SwitchEachContentLine.close(); var5_map_select_SwitchEachContentLine = null;
				var4_map_select_RepeatSplit_HeaderRows.close(); var4_map_select_RepeatSplit_HeaderRows = null;
				var3_map_select_SplitOnce__HeaderAndConent.close(); var3_map_select_SplitOnce__HeaderAndConent = null;
				var1_map_select_Root.close(); var1_map_select_Root = null;
				var13_map_select_L1__ONDEMANDJob.close(); var13_map_select_L1__ONDEMANDJob = null;
				var12_map_select_SwitchEachContentLine.close(); var12_map_select_SwitchEachContentLine = null;
				var11_map_select_RepeatSplit_HeaderRows.close(); var11_map_select_RepeatSplit_HeaderRows = null;
				var10_map_select_SplitOnce__HeaderAndConent.close(); var10_map_select_SplitOnce__HeaderAndConent = null;
				var8_map_select_Root.close(); var8_map_select_Root = null;
				var19_map_select_L5__Archive_File_Name_.close(); var19_map_select_L5__Archive_File_Name_ = null;
				var18_map_select_SwitchEachContentLine.close(); var18_map_select_SwitchEachContentLine = null;
				var17_map_select_RepeatSplit_HeaderRows.close(); var17_map_select_RepeatSplit_HeaderRows = null;
				var16_map_select_SplitOnce__HeaderAndConent.close(); var16_map_select_SplitOnce__HeaderAndConent = null;
				var15_map_select_Root.close(); var15_map_select_Root = null;
				}
				catch (Exception e)
				{
				}
			}
		}
				
	}


	private boolean runDoesCloseAll = true;		

	// instances
		protected com.altova.text.ITextNode varOndemand_MapForceInstance;

	public void run(String Ondemand_MapForceSourceFilename, String submissionTargetFilename) throws Exception {
		// open source streams
		writeTrace("Loading " + Ondemand_MapForceSourceFilename + "...\n");
		com.altova.io.FileInput Ondemand_MapForceSource = new com.altova.io.FileInput(Ondemand_MapForceSourceFilename);
		// open target stream
		com.altova.io.FileOutput submissionTarget = new com.altova.io.FileOutput(submissionTargetFilename);

		// run
		run(Ondemand_MapForceSource, submissionTarget);

		// close source streams
		Ondemand_MapForceSource.close();
		// close target stream
		writeTrace("Saving " + submissionTargetFilename + "...\n");
		submissionTarget.close();
	}


	// main entry point

	public void run(com.altova.io.Input Ondemand_MapForceSource, com.altova.io.Output submissionTarget) throws Exception {
		// Open the source(s)
		writeTrace("Parsing Text...\n");
		com.mapforce.Ondemand_MapForce.Ondemand_MapForceDocument Ondemand_MapForceDocument = new com.mapforce.Ondemand_MapForce.Ondemand_MapForceDocument();
		Ondemand_MapForceDocument.setEncoding( "windows-1252", false, false );
		Ondemand_MapForceDocument.parse(Ondemand_MapForceSource);
		varOndemand_MapForceInstance = Ondemand_MapForceDocument.getGenerator();
	
		Ondemand_MapForceSource.close();

		// Create the target
		org.w3c.dom.Document submission2Doc = (submissionTarget.getType() == com.altova.io.Output.IO_DOM) ? submissionTarget.getDocument() : XmlTreeOperations.createDocument();

		// Execute mapping

		seq1_Main mapping = new seq1_Main(new com.altova.text.TextDocumentAsMFNodeAdapter(varOndemand_MapForceInstance, Ondemand_MapForceSource.getFilename()));

		com.altova.xml.MFDOMWriter.write(mapping, submission2Doc);
		// Close the target
		XmlTreeOperations.saveDocument(submission2Doc, submissionTarget, "UTF-8", false, false, true);

		
		if (runDoesCloseAll)
		{
			Ondemand_MapForceSource.close();
			submissionTarget.close();
		}
	}


	public void setCloseObjectsAfterRun(boolean c) {runDoesCloseAll = c;}
	public boolean getCloseObjectsAfterRun() {return runDoesCloseAll;}
	static IEnumerable throwUserException(String content)
	{
		throw new com.altova.UserException(content);
	}
}

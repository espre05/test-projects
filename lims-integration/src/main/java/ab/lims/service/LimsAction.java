package ab.lims.service;

/**
 * @author pnatar
 * 
 *         Used specifically for cstoring actions involved in calling AS-> LIMS workflow. 
 *         
 *         Client classes to use LimsAction.CREATE_RUN.toString()
 */
public enum LimsAction {
  CREATE_RUN("SQ_UpdateRunStatus"), //
  ANALYSE_RUN("SQ_UpdateRunStatus"), //
  ANALYSE_SAMPLE("SQ_UpdateSampStatus"), //
  ANALYSE_SEQUENCE("SQ_UpdateSeqStatus"),//
  
  ;

  private final String name;

  private LimsAction(String s) {
    name = s;
  }

  public boolean equalsName(String otherName) {
    return (otherName == null) ? false : name.equals(otherName);
  }

  public String actionName() {
    return name;
  }
}


package Classes;

import java.sql.Timestamp;

/**
 *
 * @author Filip
 */
public class Delivery {
   
    private String moduleName;
    private String deliveryID;
    private String studentID;
    private String studentName;
    private String moduleID;
    private String deliveryContent;
    private String worklistID;
    private String deliveryTimestamp;
    private boolean deliveryIsEvaluated;

    public Delivery (String delivery_id, String student_id, String module_id, String delivery_content, String worklist_id, String delivery_timestamp, boolean delivery_isEvaluated) {
    
    this.deliveryID = delivery_id;
    this.studentID = student_id;
    this.moduleID = module_id;
    this.deliveryContent = delivery_content;
    this.worklistID = worklist_id;
    this.deliveryTimestamp = delivery_timestamp;
    this.deliveryIsEvaluated = delivery_isEvaluated;
    }
   
    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isDeliveryIsEvaluated() {
        return deliveryIsEvaluated;
    }

    public void setDeliveryIsEvaluated(boolean deliveryIsEvaluated) {
        this.deliveryIsEvaluated = deliveryIsEvaluated;
    }

    
    public String getModuleName()   {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
        
    }
    
    public void setDeliveryID(String deliveryID)    {
        this.deliveryID = deliveryID;
    }
    
    public String getDeliveryID()  {
        return deliveryID;
    }
    
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    
    public String getStudentID()   {
        return studentID;
    }
    
    public void setModuleID(String moduleID)   {
        this.moduleID = moduleID;
    }
    
    public String getModuleID()    {
        return moduleID;
    }
    
    public void setDeliveryContent(String deliveryContent)  {
        this.deliveryContent = deliveryContent;
    }
    
    public String getDeliveryContent()  {
        return deliveryContent;
    }
    
    public void setWorklistID(String worklistID)   {
        this.worklistID = worklistID;
    }
    
    public String getWorklistID()  {
        return worklistID;
    }
    
    public void setDeliveryTimestamp(String worklistTimestamp)  {
        this.deliveryTimestamp = worklistTimestamp;
    }
    
    public String getDeliveryTimestamp()    {
        return deliveryTimestamp;
    }
    
    public void setIsEvaluated(boolean deliveryIsEvaluated) {
        this.deliveryIsEvaluated = deliveryIsEvaluated;
    }
    
    public boolean getIsEvaluted()  {
        return deliveryIsEvaluated;
    }
}



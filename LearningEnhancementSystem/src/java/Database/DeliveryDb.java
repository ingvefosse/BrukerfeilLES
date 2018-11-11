package Database;


import java.sql.*;
import Classes.Delivery;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import java.sql.*;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Filip
 */
public class DeliveryDb extends Database{
<<<<<<< HEAD

    private static final String SELECT_DELIVERY = "select * from Delivery where (student_id, module_id) = (?,?)";
    private static final String SELECT_DELIVERY_WITH_USER_NAME = "select u.user_name, d.delivery_id, d.delivery_timestamp, d.student_id, d.module_id,  d.worklist_id, d.delivery_content from Delivery d inner join Users u on d.student_id = u.user_id where d.delivery_id = ?";
=======
    

>>>>>>> feature_delivery
    private static final String ADD_DELIVERY = "insert into Delivery values (default, ?, ?, ?, ?, default, default)";
    private static final String GET_DELIVERY_FORM ="select * from Module where module_id = ?";
    private static final String SLCT_ALL_DELIVERIES = "select * from Delivery where module_id = ?";
    
    public DeliveryDb() {
        init();
    }
    
    public Delivery getDeliveryWithUser(String deliveryId) {
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SELECT_DELIVERY_WITH_USER_NAME);
                ) {
            ps.setString(1, deliveryId);
            try (
                    ResultSet rs = ps.executeQuery();
                    ) {
                rs.next();
         
                Delivery delivery = new Delivery();
                
                delivery.setDeliveryID(rs.getInt("delivery_id"));
                delivery.setStudentID(rs.getString("student_id"));
                delivery.setStudentName(rs.getString("user_name"));
                delivery.setModuleID(rs.getString("module_id"));
                delivery.setDeliveryContent(rs.getString("delivery_content"));
                delivery.setWorklistID(rs.getInt("worklist_id"));
                delivery.setDeliveryTimestamp(rs.getString("delivery_timestamp"));
                
                return delivery;
            }
        } catch (SQLException ex) {
            System.out.println("Method: getDeliveryWithUser(), error: " + ex);
            return null;
        }
    }
   
        public void getNrOfDeliveries(String module_id,PrintWriter out){
           try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(SLCT_ALL_DELIVERIES); 
                   ){
                ps.setString(1, module_id);
                try (ResultSet rs = ps.executeQuery();) {
                   int i = 0; 
                    while (rs.next()){
                       i++;
                    }
                    out.println("<div class=\"alert alert-info\" role=\"alert\">For modul " + module_id + ", er det " + i + " stk som har levert"+"</div>");
                }
        }
           catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void getDeliveryForm(String moduleid, PrintWriter out) {
            try(
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(GET_DELIVERY_FORM);
                ){  
                ps.setString(1, moduleid);
                try (ResultSet rs = ps.executeQuery();) {
                    while (rs.next()) {
                        String desc = rs.getString("module_desc");
                        String content = rs.getString("module_content");
                        Boolean inInterview = rs.getBoolean("module_inInterview");
                        out.println("<h2>"+ moduleid + "</h2>");
                        out.println("<p>" + desc + "</p>");
                        out.println("<p>" + content + "</p>");
                        
                    
                        if (inInterview.equals(0)||(inInterview.equals(false))){
                            out.println("<form action=\"Delivery?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<h3>Her kan du skrive inn linken til youtube-videoen<h3>");
                            out.println("<input type=\"text\" name=\"link\">");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Upload\">");
                            //out.println("<href=\"OneModule?id="+ moduleid +">");
                            out.println("</form>");
                        } else {
                            out.println("<form action=\"Delivery?id="+ moduleid+"\" method=\"POST\">");
                            out.println("<h3>Modulen godkjennes av lærer eller hjelpelærer</h3>");
                            out.println("<p>Trykk på knappen for å gi beskjed om at du ønsker modulgodkjenning</p><br>");
                            out.println("<input type=\"submit\" class=\"btn btn-outline-danger\" value=\"Send\">");
                            out.println("</form>");
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Query error:" + ex);
        }
<<<<<<< HEAD
    }
=======
>>>>>>> feature_delivery
    public ArrayList<Delivery> getDelivery(PrintWriter out, String studentID) {
        
        String deliveries = "select d.delivery_id, d.delivery_content, d.delivery_timestamp, d.delivery_isEvaluated, w.worklist_id, s.user_id, m.module_id, m.module_name\n" +
        "from Delivery d\n" +
        "inner join Users s on d.student_id = s.user_id\n" +
        "inner join Module m on d.module_id = m.module_id\n" +
        "inner join Worklist w on d.worklist_id = w.worklist_id\n" +
        "where student_id = ?;";
      
       try (
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(deliveries);
           
            ){
           
           prepStatement.setString(1, studentID);  
          
           
           try(ResultSet rset = prepStatement.executeQuery();)  { 
            
            ArrayList <Delivery> deliveryList = new ArrayList<>();

            while(rset.next())   {
                Delivery del = new Delivery();
                del.setModuleName(rset.getString("module_name"));
                del.setDeliveryID(rset.getInt("delivery_id"));
                del.setStudentID(rset.getString("user_id"));
                del.setModuleID(rset.getString("module_id"));
                del.setDeliveryContent(rset.getString("delivery_content"));
                del.setWorklistID(rset.getInt("worklist_id"));
                del.setDeliveryTimestamp(rset.getString("delivery_timestamp"));
                del.setIsEvaluated(rset.getBoolean("delivery_isEvaluated"));

                deliveryList.add(del);
           }
           return deliveryList;
           }
           
          
       }
       
        catch(SQLException e)    {
           out.println("SQLException in getDelivery(): " + e);

    }
       

        return null;
    
<<<<<<< HEAD
    }
=======

>>>>>>> feature_delivery
    public void addDelivery(String studentid, String moduleid, String content, String worklistid) {
        try( Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(ADD_DELIVERY);
                ) {
            
            ps.setString(1,studentid);
            ps.setString(2,moduleid);
            ps.setString(3,content);
            ps.setString(4,worklistid);
            ps.executeUpdate();
            
        }
        catch(SQLException ex)   {
            System.out.println(ex);
        }
    }
<<<<<<< HEAD
    
    public boolean editModule (String moduleId, String content){
        String moduleId = request.getParameter("id");
        String editDeliveryContent = "update Delivery set delivery_content = ?, where delivery_id = ?";
        
        try(
            Connection connection = getConnection();
            PreparedStatement prepStatement = connection.prepareStatement(editDeliveryContent);
        
        ) {
            prepStatement.setString(1, content);
            prepStatement.setString(1, content);
        
            prepStatement.executeUpdate();
            
            
            return true;
          }
        
        catch(SQLException ex)  {
            out.println("Error");
        }
        return false;
    }
    
    //VVVV FOSSE VVVVV

    public int getAllEvaluatedDeliveries(PrintWriter out) {
        String oneDelivery = ("select * from Delivery where delivery_isEvaluated = 1;");
        int allEvaluatedDeliveriesCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneDelivery);
                ){
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    allEvaluatedDeliveriesCount++;
                }  
                return allEvaluatedDeliveriesCount;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getAllEvaluatedDeliveries" + liste);
        }  
        return allEvaluatedDeliveriesCount;
    }     
    
    
    public int getEvaluatedDeliveries(PrintWriter out, String id) {
        String oneDelivery = ("select * from Delivery where student_id = ? AND delivery_isEvaluated = 1;");
        int evaluatedDeliveriesCount = 0;
        
        try(    Connection connection = getConnection();
                PreparedStatement prepStatement = connection.prepareStatement(oneDelivery);
                ){
            prepStatement.setString(1, id);
            
            try(ResultSet rset = prepStatement.executeQuery(); ){
                while(rset.next())   {
                    evaluatedDeliveriesCount++;
                }  
                return evaluatedDeliveriesCount;
            }
        }
        catch(SQLException liste) {
            out.println("SQL exception: in getEvaluatedDeliveries" + liste);
        }  
        return evaluatedDeliveriesCount;
    }     
    //^^^^ FOSSE ^^^^
    
    
=======
>>>>>>> feature_delivery
}
 
     


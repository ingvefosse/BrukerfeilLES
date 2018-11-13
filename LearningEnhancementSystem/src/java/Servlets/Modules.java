package Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import HtmlTemplates.BootstrapTemplate;
import Database.ModuleDb;
import Database.LearningGoalDb;
import java.util.HashMap;
/**
 *
 * @author Vegard
 */
@WebServlet(name = "Modules", urlPatterns = {"/Modules"})
public class Modules extends SuperServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Objekt for å generere UI
    BootstrapTemplate bst = new BootstrapTemplate();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()){  
            super.processRequest(request, response, "Modules", out);
         ModuleDb db = new ModuleDb();
         LearningGoalDb learnGoal = new LearningGoalDb();
         db.init();
        
         HashMap<String, String> hashmap = new HashMap<>();
         
        if (request.getMethod().equals("POST"))  {
                
                String modulnavn = request.getParameter("Modulnavn");
                
                String beskrivelse = request.getParameter("Beskrivelse");
         
                String innhold = request.getParameter("Innhold");
                
                String leveringsform = request.getParameter("leveringsform");
                
                String learnGoalText = request.getParameter("Laringsmal");
                
                
                String learnGoalPoints = request.getParameter("Poeng");
                
               
                           
                db.addModule(out, modulnavn, beskrivelse, innhold, leveringsform);
                learnGoal.addLearningGoals(out, learnGoalText, learnGoalPoints, modulnavn);
            }
            
            
            bst.containerOpen(out);
            
            db.skrivModuler(out);
            
            bst.containerClose(out);
            
            addModuleForm(out);
            
            bst.bootstrapFooter(out);
            
        }
    }
        private void addModuleForm(PrintWriter out)  {
            
            
         
            out.println("<html>");
       
            out.println("<div>");
 
            out.println("<h1>Legg til modul</h1>");
            
            out.println("<form action=\"Modules\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type =\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<input type=\"text\" name=\"Innhold\"><br>");      
            out.println("<br>");
            out.println("<h3>Velg leveringsform</h3><br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Muntlig\">Muntlig");
            out.println("<br>");
            out.println("<input type=\"radio\" name=\"leveringsform\" value=\"Video\">Video");
            out.println("<br>");
            
        
            
          
            out.println("<h1>Legg til læringsmål</h1>");
            
          //  out.println("<input type=\"text\" name=\"Laringsmal\">");
            
            out.println("<script language=\"javascript\">");
            out.println("var i = 0;");
            out.println("function add() {");
            
       
	    out.println("var div = document.createElement(\"div\");");
            
            
            out.println("div.innerHTML = '<input type=\"text\" name=\"Laringsmal' + i + '\"></input> <input type=\"text\" name=\"Poeng\"></input><br>';");
            out.println("i++");
            out.println("var id = document.getElementById(\"inputID\");");
            
            
            out.println("id.appendChild(div);");
            
            
            out.println("}");
            out.println("</script>");
    
            
            out.println("<style>");
            out.println("h3 {display:inline;}");
            out.println("</style>");
            
            out.println("<h3>Læringsmål</h3>");
            
            out.println("<h3>Poeng</h3>");
            
            out.println("<span id=\"inputID\">&nbsp;</span><br>");
            out.println("<input type=\"button\" value=\"Nytt læringsmål\" onclick=\"add()\"/>");
            
            
            out.println("<input type=\"submit\" value=\"Publiser modul\"><br>");
            out.println("</form>");
            
            out.println("</div>");
            out.println("</html>");
            
            
            //  out.println("<h3>Poeng</h3><br>"); document.forms[0].element.value
    //        out.println("<input type=\"text\" name=\"Poeng\">");
            
           // out.println("<input type=\"submit\" value=\"Legg til læringsmål\"><br>");
            
            
        }
        
    
        
            // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
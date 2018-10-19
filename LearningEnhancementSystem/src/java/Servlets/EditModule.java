package Servlets;

import Classes.Module;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Database.ModuleDb;
/**
 *
 * @author Gorm-Erik
 */
@WebServlet(urlPatterns = {"/EditModule"})
public class EditModule extends HttpServlet {

    

  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        try (PrintWriter out = response.getWriter()) {
            
           
            editModuleForm(out, request, module_id);
            
        }   
}
 
    
    
    private void editModuleForm(PrintWriter out, HttpServletRequest request, String module_id)    {
             
            
            String id = request.getParameter("id");  
            
            out.println("<h1>Rediger modul</h1>");
            out.println("<form action=\"EditModule?="+id+"\" method=\"POST\">");
            out.println("<h3>Modulnummer</h3><br>");
            
            ModuleDb db = new ModuleDb();
            Module module = db.getModuleWithLearningGoals(module_id);
            int modulID = module.getModuleid();
            
            out.println("<input type=\"text\" name=\"Modulnummer\" value="+modulID+"/>");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type=\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse av læringsmål</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Rediger modul\"><br>");        
            out.println("<br>");
            out.println("</form>");
            out.println("</div>");
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

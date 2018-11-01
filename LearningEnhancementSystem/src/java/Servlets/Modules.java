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
         db.init();
        
        if (request.getMethod().equals("POST"))  {
                
                String modulnavn = request.getParameter("Modulnavn");
                
                String beskrivelse = request.getParameter("Beskrivelse");
         
                String innhold = request.getParameter("Innhold");
                           
                db.addModule(out, modulnavn, beskrivelse, innhold);
                
            }
            
            
            bst.containerOpen(out);
            
            db.skrivModuler(out);
            
            bst.containerClose(out);
            
            addModuleForm(out);
            
            bst.bootstrapFooter(out);
            
        }
    }
        private void addModuleForm(PrintWriter out)  {
            
            out.println("<div>");
          //  out.println("<a href=\"Modules\">");
           // out.println("</a>");
            out.println("<h1>Legg til modul</h1>");
            out.println("<form action=\"Modules\" method=\"POST\">");
            out.println("<h3>Modulnavn</h3><br>");
            out.println("<input type =\"text\" name=\"Modulnavn\"><br>");
            out.println("<h3>Beskrivelse</h3><br>");
            out.println("<input type=\"text\" name=\"Beskrivelse\"><br>");
            out.println("<h3>Innhold</h3><br>");
            out.println("<input type=\"text\" name=\"Innhold\"><br>");
            out.println("<br>");
            out.println("<input type=\"submit\" value=\"Legg til modul\"><br>");        
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotaw.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import proyectotaw.ejb.TalertFacadeLocal;
import proyectotaw.ejb.TcitasFacadeLocal;
import proyectotaw.entity.Talert;
import proyectotaw.entity.Tcitas;
import proyectotaw.entity.Tusers;

/**
 *
 * @author Alberto
 */
@WebServlet(name = "AlertDateServlet", urlPatterns = {"/showAlertsDates"})
public class AlertDateServlet extends HttpServlet {

    @EJB
    private TcitasFacadeLocal tcitasFacade;
    @EJB
    private TalertFacadeLocal talertFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Tusers user = (Tusers) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/index");
            return;
        }
        String query = request.getQueryString();
        if (query == null || query.isEmpty()) {
            List<Talert> alerts = talertFacade.findByUserId(user.getId());
            List<Tcitas> dates = new ArrayList<>(user.getTcitasCollection());
            request.setAttribute("alerts", alerts);
            request.setAttribute("dates", dates);
            getServletContext().getRequestDispatcher("/showAlertsADates.jsp").forward(request, response);
        } else{
            switch(query.split("=")[0]){
                case "alert": request.setAttribute("alert", talertFacade
                        .findById(Integer.parseInt(query.split("=")[1])));
                    break;
                case "date": request.setAttribute("date", tcitasFacade.findById(Integer
                        .parseInt(query.split("=")[1])));
                    break;
            }
            getServletContext().getRequestDispatcher("/showInfoDateAlert.jsp").forward(request, response);
        }
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

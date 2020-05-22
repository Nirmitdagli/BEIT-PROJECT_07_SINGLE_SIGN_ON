/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.NewCookie;

/**
 *
 * @author ankit
 */
public class Login extends HttpServlet {

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
            throws ServletException, IOException 
    {
         response.setContentType("text/html");
         PrintWriter out=response.getWriter();
         String x,y;
         x=request.getParameter("username");
         y=request.getParameter("pass");
         try
         {
         URL urlForGetRequest = new URL("http://localhost:8080/WebApplication1/webresources/generic?u="+x+"&p="+y);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
       
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) 
        {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User or password incorrect');");
            out.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("/Login.html");
            rd.include(request,response);
              
        }
        else if(responseCode == HttpURLConnection.HTTP_OK)
        {
            String u=conection.getHeaderField("Set-Cookie");
            String[] k=u.split("=");
            String[] k1=k[1].split(";");
       
            Cookie c=new Cookie(k[0],k1[0]);
            c.setPath("/");
            response.addCookie(c);
            String site="http://localhost:8080/Test1/index.jsp";
            response.sendRedirect(site);
        }
       else
        {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Technical error try again later');");
            out.println("</script>");
            RequestDispatcher rd=request.getRequestDispatcher("/Login.html");
            rd.include(request,response);
        }
         }
         catch(Exception e)
         {
             out.println(e);
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

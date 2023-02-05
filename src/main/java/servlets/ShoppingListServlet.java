/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jifang
 */
public class ShoppingListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
    	HttpSession session = request.getSession();
    	String action = request.getParameter("action");
    	String username = (String)session.getAttribute("username");
    	String url = null;
    	if (username != null) { // already logged in
    		if (action == null) { // new user
	    		url = "/WEB-INF/shoppingList.jsp";
	    		ServletContext sc = getServletContext();
    		} else if (action.equals("logout")) { // user click logout link
    			request.getSession().removeAttribute("username");
    			request.getSession().removeAttribute("items");
    			request.getSession().invalidate();
    			url = "/WEB-INF/register.jsp";
    		} else {
    			// todo, nothing todo. 
    		}
    	} else { // new user.
	    	url = "/WEB-INF/register.jsp";
    	}
    	getServletContext().getRequestDispatcher(url).forward(request, response);
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
    	// dispatch according to action field. 
    	String action = request.getParameter("action");
    	String url = "";
    	if (action.equals("register")) { // new user
    		String username = request.getParameter("username");
    		// save username in session. 
    		HttpSession session = request.getSession();
    		session.setAttribute("username", username);
    		url = "/WEB-INF/shoppingList.jsp";
    	} else if (action.equals("delete")) { // delete an item
    		String itemname = request.getParameter("itemname");
    		@SuppressWarnings("unchecked")
			List<String> items = (List<String>)request.getSession().getAttribute("items");
    		
    		// itemname == null means user choose nothing to delete, just skip.
    		if (items != null && itemname != null) {
    			items.remove(itemname);
    			url = "/WEB-INF/shoppingList.jsp";
    		} else {
    			// do nothing.
    		}
    	} else if (action.equals("add")) { // add an item
    		// append the new item to the items array in session. 
    		String itemname = request.getParameter("itemname");
    		@SuppressWarnings("unchecked")
			List<String> items = (List<String>)request.getSession().getAttribute("items");
    		if (items == null) {
    			items = new LinkedList<>();
    			request.getSession().setAttribute("items", items);
    		}
    		items.add(itemname);
    		url = "/WEB-INF/shoppingList.jsp";
    	} else {
    		// todo, nothing todo. 
    	}
    	ServletContext sc = getServletContext();
		sc.getRequestDispatcher(url).forward(request, response);
    }


}

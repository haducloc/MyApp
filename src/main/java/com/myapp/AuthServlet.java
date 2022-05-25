package com.myapp;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private SecurityContext securityContext;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {

			response.sendRedirect(request.getServletContext().getContextPath() + "/TestServlet");
			return;
		}

		if (!"login".equals(action) && !"logout".equals(action)) {
			throw new ServletException("action must be login or logout");
		}

		if ("login".equals(action)) {

			// authenticate
			AuthenticationParameters parameters = new AuthenticationParameters();
			parameters.credential(new UsernamePasswordCredential("user1", "password1"));

			securityContext.authenticate(request, response, parameters);

			// Redirect to TestServlet
			response.sendRedirect(request.getServletContext().getContextPath() + "/TestServlet");
		} else {

			// Logout

			// JSESSIONID cookie was supposed to be deleted
			request.logout();

			// Redirect to TestServlet
			response.sendRedirect(request.getServletContext().getContextPath() + "/TestServlet");
		}

	}
}

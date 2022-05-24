package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setStatus(200);

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println();
		out.println("<head>");
		out.println(" <meta charset=\"utf-8\">");
		out.println(" <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println(" <title>TestServlet</title>");
		out.println();
		out.println("</head>");
		out.println("<body>");

		// getRemoteUser
		out.format(" <h3>User: %s</h3>", request.getRemoteUser());
		out.println();

		// request.getSession(false)
		if (request.getSession(false) != null) {
			out.format(" <h3>SessionID: %s</h3>", request.getSession(false).getId());
			out.println();
		}

		// Login or Logout links
		if (request.getRemoteUser() != null) {
			out.format(" <p><a href='%s'>Logout</a></p>", request.getServletContext().getContextPath() + "/AuthServlet?action=logout");
			out.println();
		} else {
			out.format(" <p><a href='%s'>Login</a></p>", request.getServletContext().getContextPath() + "/AuthServlet?action=login");
			out.println();
		}

		out.println("</body>");
		out.print("</html>");

	}
}

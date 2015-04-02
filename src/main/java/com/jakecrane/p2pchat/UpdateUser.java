package com.jakecrane.p2pchat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {

	private static final long serialVersionUID = -7367097802572047490L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection connection = Database.getConnection()) {
			if (connection == null) {
				System.err.println("Failed to connect to database.");
				return;
			}

			String displayName = request.getParameter("display_name").toUpperCase();
			int listeningPort = Integer.parseInt(request.getParameter("listening_port"));

			PreparedStatement statment = connection.prepareStatement(
					"UPDATE user "
					+ "SET ipv4_address = ?, listening_port = ?, last_active = ? "
					+ "WHERE display_name = ?");
			
			statment.setString(1, request.getRemoteAddr());
			statment.setInt(2, listeningPort);
			statment.setDate(3, new Date(new java.util.Date().getTime()));
			statment.setString(4, displayName);
			
			statment.execute();
			response.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		}
	}

}

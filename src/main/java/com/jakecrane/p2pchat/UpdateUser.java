package com.jakecrane.p2pchat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			int listeningPort = Integer.parseInt(request.getParameter("listening_port"));

			if (Database.userAndPassAreValid(connection, username, password)) {
				PreparedStatement statment = connection.prepareStatement(
						"UPDATE user "
								+ "SET ipv4_address = ?, listening_port = ?, last_active = ? "
								+ "WHERE display_name = ?");

				statment.setString(1, request.getRemoteAddr());
				statment.setInt(2, listeningPort);
				statment.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
				statment.setString(4, username);

				statment.execute();
				response.setStatus(HttpServletResponse.SC_OK);
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		}
	}

}

package com.jakecrane;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewFriends")
public class ViewFriends extends HttpServlet {

	private static final long serialVersionUID = 7048533897266949417L;

	public ViewFriends() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter printWriter = response.getWriter()) {

			try (Connection connection = Database.getConnection()) {
				if (connection == null) {
					System.err.println("Failed to connect to database.");
					return;
				}

				String displayName = request.getParameter("display_name").toUpperCase();

				PreparedStatement statment = connection.prepareStatement(
						"SELECT u2.display_name, u2.ipv4_address, u2.listening_port "
						+ "FROM friend_list, user u1, user u2 "
						+ "WHERE u1.display_name = ? "
						+ "AND u1.user_id = friend_list.user_id "
						+ "AND u2.user_id = friend_list.friend_id");
				statment.setString(1, displayName);

				ResultSet rs = statment.executeQuery();
				printWriter.write(displayName + "'s Friends:\n");
				while (rs.next()) {
					printWriter.write("\t" + rs.getString(1) + ", " + rs.getString(2) + ":" + rs.getString(3) + "\n");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

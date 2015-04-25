package com.jakecrane.p2pchat;

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

@WebServlet("/DeleteFriend")
public class DeleteFriend extends HttpServlet {

	private static final long serialVersionUID = 4686187840897406320L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (PrintWriter printWriter = response.getWriter()) {

			try (Connection connection = Database.getConnection()) {
				if (connection == null) {
					System.err.println("Failed to connect to database.");
					return;
				}

				String username = request.getParameter("username");
				String password = request.getParameter("password");

				String friendUsername = request.getParameter("friend_username");
				
				if (Database.userAndPassAreValid(connection, username, password)) {

					PreparedStatement selectStatment = connection.prepareStatement(
							"SELECT user_id FROM user where display_name = ?");
					selectStatment.setString(1, username);

					ResultSet rs = selectStatment.executeQuery();
					int myId = -1;
					if (rs.next()) {
						myId = rs.getInt(1);
					}

					selectStatment.setString(1, friendUsername);
					rs = selectStatment.executeQuery();
					int friendId = -1;
					if (rs.next()) {
						friendId = rs.getInt(1);
					}

					if (myId != friendId && myId != -1 && friendId != -1) {
						PreparedStatement statment = connection.prepareStatement(
								"DELETE FROM friend_list WHERE user_id = ? AND friend_id = ?");
						statment.setInt(1, myId);
						statment.setInt(2, friendId);

						statment.execute();

						response.setStatus(HttpServletResponse.SC_OK);
					}
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}

			} catch (SQLException e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				e.printStackTrace();
			}
		}
	}

}

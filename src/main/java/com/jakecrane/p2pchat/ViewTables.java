package com.jakecrane.p2pchat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewTables")
public class ViewTables extends HttpServlet {

	private static final long serialVersionUID = 5598696047276130988L;

	public static void printHTMLTable(Connection connection, PrintWriter printWriter, PreparedStatement preparedStatement) throws SQLException {
		ResultSet rs = preparedStatement.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();

		printWriter.println("<table>");

		printWriter.println("<tr>");
		printWriter.println("<th colspan=\"" + rsmd.getColumnCount() + "\">" + rsmd.getTableName(1) + "</th>");
		printWriter.println("</tr>");

		printWriter.println("<tr>");
		for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			printWriter.println("<th>" + rsmd.getColumnName(i) + "</th>");
		}
		printWriter.println("</tr>");

		while(rs.next()) {
			printWriter.println("<tr>");
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
				printWriter.println("<td>" + rs.getObject(rsmd.getColumnName(i)) + "</td>");
			}
			printWriter.println("</tr>");
		}
		printWriter.println("</table>");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try (PrintWriter printWriter = response.getWriter()) {

			printWriter.println("<!DOCTYPE html><html>");
			printWriter.println("<head>");
			printWriter.println("<meta charset=\"UTF-8\">");
			printWriter.println("<title>View Tables</title>");
			printWriter.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"TableStyle.css\">");
			printWriter.println("</head>");
			printWriter.println("<body style=\"text-align: center;\">");

			try (Connection connection = Database.getConnection()) {
				if (connection == null) {
					System.err.println("Failed to connect to database.");
					return;
				}

				final String[] tables = {"user", "friend_list"};
				for (String string : tables) {
					PreparedStatement preparedStatment = connection.prepareStatement("SELECT * FROM " + string);
					printHTMLTable(connection, printWriter, preparedStatment);
					printWriter.println("<br><br>");
				}


			} catch (SQLException e) {
				e.printStackTrace();
			}
			printWriter.println("</body></html>");
		} 

	}

}

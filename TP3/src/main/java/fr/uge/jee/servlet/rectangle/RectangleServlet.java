package fr.uge.jee.servlet.rectangle;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/rectangle")
public class RectangleServlet extends HttpServlet {




    public static String readFromInputStream(InputStream inputStream) throws IOException {
        var lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines();
        return lines.collect(Collectors.joining("\n"));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        InputStream inputStream = getServletContext().getResourceAsStream("WEB-INF/templates/rectangle-form.html");
        writer.println(readFromInputStream(inputStream));
        writer.flush();

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String height = request.getParameter("height");
        String width = request.getParameter("width");
        if(!height.isEmpty() && !width.isEmpty()){

            var result =Integer.parseInt(height) * Integer.parseInt(width);
            PrintWriter writer = response.getWriter();
            writer.println(String.valueOf(result));
            writer.flush();
        }
        else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // Set HTTP status to 400
            PrintWriter writer = response.getWriter();
            writer.println("Error: Missing height or width parameter.");
            writer.flush();
        }

    }
}

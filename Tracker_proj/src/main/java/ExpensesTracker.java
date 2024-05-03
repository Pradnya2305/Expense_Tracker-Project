import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@WebServlet("/ExpensesTracker")
public class ExpensesTracker extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ArrayList<Double> expenses = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Expenses Tracker</title></head>");
        out.println("<body>");
        out.println("<h1>Expenses Tracker</h1>");
        out.println("<form method='post'>");
        out.println("<label for='amount'>Enter expense amount:</label>");
        out.println("<input type='text' id='amount' name='amount'>");
        out.println("<button type='submit' name='action' value='add'>Add Expense</button>");
        out.println("<button type='submit' name='action' value='view'>View Expenses</button>");
        out.println("<button type='submit' name='action' value='delete'>Delete Expense</button>");
        out.println("<button type='submit' name='action' value='report'>Generate Report</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    addExpense(request, response);
                    break;
                case "view":
                    viewExpenses(request, response);
                    break;
                case "delete":
                    deleteExpense(request, response);
                    break;
                case "report":
                    generateReport(request, response);
                    break;
                default:
                    response.sendRedirect("ExpensesTrackerServlet");
            }
        }
    }

    private void addExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String amountStr = request.getParameter("amount");
        if (amountStr != null && !amountStr.isEmpty()) {
            try {
                double amount = Double.parseDouble(amountStr);
                expenses.add(amount);
                response.sendRedirect("ExpensesTrackerServlet");
            } catch (NumberFormatException e) {
                showError(response, "Invalid expense amount.");
            }
        } else {
            showError(response, "Expense amount cannot be empty.");
        }
    }

    private void viewExpenses(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Expenses Tracker</title></head>");
        out.println("<body>");
        out.println("<h1>Expenses</h1>");
        out.println("<ul>");
        for (int i = 0; i < expenses.size(); i++) {
            out.println("<li>" + (i + 1) + ") " + expenses.get(i) + "</li>");
        }
        out.println("</ul>");
        out.println("<a href='ExpensesTrackerServlet'>Back to Menu</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void deleteExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexStr = request.getParameter("index");
        if (indexStr != null && !indexStr.isEmpty()) {
            try {
                int index = Integer.parseInt(indexStr);
                if (index >= 1 && index <= expenses.size()) {
                    expenses.remove(index - 1);
                    response.sendRedirect("ExpensesTrackerServlet");
                }
            } catch (NumberFormatException e) {
                showError(response, "Invalid expense number.");
            }
        } else {
            showError(response, "Expense number cannot be empty.");
        }
    }

    private void generateReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double total = 0;
        for (double expense : expenses) {
            total += expense;
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Expenses Tracker Report</title></head>");
        out.println("<body>");
        out.println("<h1>Total Expenses: " + total + "</h1>");
        out.println("<a href='ExpensesTrackerServlet'>Back to Menu</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void showError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Error</title></head>");
        out.println("<body>");
        out.println("<h1>Error</h1>");
        out.println("<p>" + message + "</p>");
        out.println("<a href='ExpensesTrackerServlet'>Back to Menu</a>");
        out.println("</body>");
        out.println("</html>");
    }
}

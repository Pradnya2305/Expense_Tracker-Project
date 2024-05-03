
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ExpensesTrackerServlet")
public class ExpensesTrackerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ArrayList<Double> expenses = (ArrayList<Double>) session.getAttribute("expenses");
        if (expenses == null) {
            expenses = new ArrayList<>();
        }

        String action = request.getParameter("action");
        if ("addExpense".equals(action)) {
            try {
                double amount = Double.parseDouble(request.getParameter("expenseAmount"));
                expenses.add(amount);
                session.setAttribute("expenses", expenses);
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        } else if ("deleteExpense".equals(action)) {
            try {
                int index = Integer.parseInt(request.getParameter("deleteExpense")) - 1;
                if (index >= 0 && index < expenses.size()) {
                    expenses.remove(index);
                    session.setAttribute("expenses", expenses);
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        }

        // Redirect back to the JSP page
        response.sendRedirect("add.jsp");
    }
}
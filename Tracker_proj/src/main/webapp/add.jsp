<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Expenses Tracker</title>
    <style> 
    	 h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
    	label {
    		
            color: #666;
            margin-bottom: 10px;
        }
        input[type="text"] {
            padding: 10px;
            width: 20%;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            ;
        }
        button {
        	
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
         
            color: Black;
            cursor: pointer;
            margin-bottom: 10px;s
            transition: background-color 0.3s;
        }
</style>
</head>
<body>

     <h1>Expenses Tracker</h1>
    <form method="post" action="ExpensesTracker">
        <label for="amount">Enter Expense Amount:</label><br>
        <input type="text" id="amount" name="amount"><br>
        <button type="submit" name="action" value="add">Add Expense</button>
        <button type="submit" name="action" value="view">View Expenses</button>
        <button type="submit" name="action" value="delete">Delete Expense</button>
        <button type="submit" name="action" value="report">Generate Report</button>
    </form>
</body>
</html>
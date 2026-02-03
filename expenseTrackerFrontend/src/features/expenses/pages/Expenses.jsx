import { useEffect, useState } from "react";
import { getAllExpenses } from "../service/expenseService";

const Expenses = () => {
  const [expenses, setExpenses] = useState([]);

  useEffect(() => {
    const fetchExpenses = async () => {
      try {
        const data = await getAllExpenses();
        setExpenses(data.data.content);
      } catch (err) {
        console.error("Failed to fetch expenses", err);
        setExpenses([]);
      }
    };

    fetchExpenses();
  }, []);

  return (
    <div style={{ padding: "2rem" }}>
      <h2>Expenses</h2>
      {expenses.length === 0 ? (
        <p>No expenses found.</p>
      ) : (
        <table style={{ width: "100%", borderCollapse: "collapse" }}>
          <thead>
            <tr style={{ background: "#f0f0f0" }}>
              <th style={thStyle}>Title</th>
              <th style={thStyle}>Description</th>
              <th style={thStyle}>Amount</th>
              <th style={thStyle}>Type</th>
              <th style={thStyle}>Date</th>
              <th style={thStyle}>Category</th>
            </tr>
          </thead>
          <tbody>
            {expenses.map((exp) => (
              <tr key={exp.id} style={{ borderBottom: "1px solid #ccc" }}>
                <td style={tdStyle}>{exp.title}</td>
                <td style={tdStyle}>{exp.description}</td>
                <td style={tdStyle}>{exp.amount}</td>
                <td style={tdStyle}>{exp.type}</td>
                <td style={tdStyle}>{new Date(exp.date).toLocaleDateString()}</td>
                <td style={tdStyle}>{exp.categoryName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

// simple inline styles
const thStyle = { padding: "0.5rem", textAlign: "left", borderBottom: "1px solid #ccc" };
const tdStyle = { padding: "0.5rem" };

export default Expenses;

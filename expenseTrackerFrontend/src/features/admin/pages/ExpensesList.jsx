import { useEffect, useState } from "react";
import { getAllExpenses, deleteExpenseById, updateExpense } from "../adminService";
import { useNavigate } from "react-router-dom";
import "../styles/expenseList.css";
const ExpenseList = () => {
  const [expenses, setExpenses] = useState([]);
  const [editingExpenseId, setEditingExpenseId] = useState(null);
  const [editFormData, setEditFormData] = useState({});
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredExpenses, setFilteredExpenses] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    fetchExpenses();
  }, []);

  // Fetch all expenses
  const fetchExpenses = async () => {
    const expense = await getAllExpenses();
    setExpenses(expense.content); // your API returns content array
    setFilteredExpenses(expense.content);
  };

  // Delete expense
  const deleteExpenses = async (id) => {
    if (window.confirm("Are you sure you want to delete this expense?")) {
      await deleteExpenseById(id);
      fetchExpenses();
    }
  };

  // Start editing
  const startEdit = (expense) => {
    setEditingExpenseId(expense.id);
    setEditFormData({
      title: expense.title,
      description: expense.description,
      amount: expense.amount,
      type: expense.type,
      categoryName: expense.categoryName || "",
    });
  };

  // Handle form change
  const handleChange = (e) => {
    setEditFormData({ ...editFormData, [e.target.name]: e.target.value });
  };

  // Save updated expense
  const handleUpdate = async (id) => {
    if (!editFormData.categoryName || editFormData.categoryName.trim() === "") {
        alert("Category cannot be empty");
        return;
    }
    await updateExpense(id, editFormData);
    setEditingExpenseId(null);
    fetchExpenses();
  };
  const handleSearch = (e) => {
  const value = e.target.value;
  setSearchTerm(value);

  const filtered = expenses.filter((exp) =>
    exp.title.toLowerCase().includes(value.toLowerCase()) ||
    (exp.categoryName || "").toLowerCase().includes(value.toLowerCase()) ||
    exp.type.toLowerCase().includes(value.toLowerCase())
  );

  setFilteredExpenses(filtered);
};


return (
  <div className="expenses-page">
    <button className="back-btn" onClick={() => navigate("/admin/dashboard")}>
      Back
    </button>
    <div>
        <input
  type="text"
  className="search-input"
  placeholder="Search by title, category or type..."
  value={searchTerm}
  onChange={handleSearch}
/>
    </div>


    {filteredExpenses.length === 0 && (
      <p className="no-expenses">No expenses found</p>
    )}

    <div className="expenses-grid">
    {filteredExpenses.map((e) => (
      <div key={e.id} className="expense-card">
        {editingExpenseId === e.id ? (
          <>
            <div className="edit-form">
              <input name="title" value={editFormData.title} onChange={handleChange} />
              <input name="description" value={editFormData.description} onChange={handleChange} />
              <input type="number" name="amount" value={editFormData.amount} onChange={handleChange} />
              <select name="type" value={editFormData.type} onChange={handleChange}>
                <option value="EXPENSE">EXPENSE</option>
                <option value="INCOME">INCOME</option>
              </select>
              <input
                name="categoryName"
                value={editFormData.categoryName}
                onChange={handleChange}
                placeholder="Category"
              />
            </div>

            <div className="edit-actions">
              <button className="save-btn" onClick={() => handleUpdate(e.id)}>
                Save
              </button>
              <button className="cancel-btn" onClick={() => setEditingExpenseId(null)}>
                Cancel
              </button>
            </div>
          </>
        ) : (
          <>
            <p><b>Title:</b> {e.title}</p>
            <p><b>Description:</b> {e.description}</p>
            <p><b>Amount:</b> ₹{e.amount}</p>
            <p><b>Type:</b> {e.type}</p>
            <p><b>Category:</b> {e.categoryName || "-"}</p>

            <div className="expense-actions">
              <button className="edit-btn" onClick={() => startEdit(e)}>
                Update
              </button>
              <button className="delete-btn" onClick={() => deleteExpenses(e.id)}>
                Delete
              </button>
            </div>
          </>
        )}
      </div>
      
    ))}
    </div>
  </div>
);

};

export default ExpenseList;

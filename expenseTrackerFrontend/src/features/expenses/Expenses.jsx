import { useEffect, useState } from "react";
import { addExpense, deleteExpense, getAllExpenses, updateExpense } from "./expenseService";
import { useNavigate } from "react-router-dom";
import { getAllCategories } from "../categories/categoryService";
import "./expenses.css";

const Expenses = () => {
  const navigate = useNavigate();

  const [expenses, setExpenses] = useState([]);
  const [categories, setCategories] = useState([]);
  const [categorySearch, setCategorySearch] = useState("");
  const [showCategoryDropdown, setShowCategoryDropdown] = useState(false);

  const [isLoading, setIsLoading] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState(null);

  const [expense, setExpense] = useState({
    title: "",
    description: "",
    amount: "",
    type: "",
    categoryName: ""
  });

  const [editingId, setEditingId] = useState(null);
  const [editingExpense, setEditingExpense] = useState({});

  useEffect(() => {
    fetchExpenses();
    fetchCategories();
  }, []);

  const fetchCategories = async () => {
    try {
      const res = await getAllCategories();
      setCategories(res);
    } catch (err) {
      console.error("Failed to fetch categories", err);
    }
  };

  const fetchExpenses = async () => {
    setIsLoading(true);
    try {
      const res = await getAllExpenses();
      setExpenses(res);
    } catch (err) {
      console.error("Failed to fetch expenses", err);
      setError("Failed to load expenses");
      setExpenses([]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setExpense((prev) => ({
      ...prev,
      [name]: name === "amount" ? Number(value) : value
    }));
  };

  const handleAddExpense = async (e) => {
    e.preventDefault();
    setError(null);
    if (!expense.title || !expense.amount || !expense.type || !expense.categoryName) {
      setError("Please fill all required fields");
      return;
    }

    setIsSubmitting(true);
    try {
      await addExpense(expense);
      setExpense({
        title: "",
        description: "",
        amount: "",
        type: "",
        categoryName: ""
      });
      setCategorySearch("");
      fetchExpenses();
    } catch {
      setError("Failed to add expense");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this expense?")) return;
    try {
      await deleteExpense(id);
      fetchExpenses();
    } catch (err) {
      console.error(err);
    }
  };

  const handleEdit = (exp) => {
    setEditingId(exp.id);
    setEditingExpense({ ...exp });
  };

  const handleCancelEdit = () => {
    setEditingId(null);
    setEditingExpense({});
  };

  const handleInlineChange = (e) => {
    const { name, value } = e.target;
    setEditingExpense((prev) => ({
      ...prev,
      [name]: name === "amount" ? Number(value) : value
    }));
  };

  const handleSaveEdit = async () => {
    try {
      await updateExpense(editingId, editingExpense);
      setEditingExpense({});
      setEditingId(null);
      fetchExpenses();
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <div className="page expenses-page">
      <button className="back-btn" onClick={() => navigate("/")}>← Back</button>
      <h2 className="page-title">Expenses</h2>

      {error && <p className="error">{error}</p>}

      <form className="expense-form" onSubmit={handleAddExpense}>
        <input
          name="title"
          value={expense.title}
          placeholder="Title"
          onChange={handleChange}
          required
        />
        <small className="input-hint">Enter a short title for the expense or income e.g., Grocery, Rent, Salary</small>

        <input
          name="description"
          value={expense.description}
          placeholder="Description"
          onChange={handleChange}
        />
         <small className="input-hint">Optional: Add details about this expense e.g., Bought fruits and vegetables</small>

        <input
          type="number"
          name="amount"
          value={expense.amount}
          placeholder="Amount"
          onChange={handleChange}
          required
        />
        <small className="input-hint">Enter the amount in Rupees e.g., 50, 1200, 15.75</small>

        <div className="radio-group">
          <label>
            <input
              type="radio"
              name="type"
              value="EXPENSE"
              checked={expense.type === "EXPENSE"}
              onChange={handleChange}
            />
            Expense
          </label>
          <label>
            <input
              type="radio"
              name="type"
              value="INCOME"
              checked={expense.type === "INCOME"}
              onChange={handleChange}
            />
            Income
          </label>
        </div>

        <div className="category-select">
          <input
            type="text"
            placeholder="Select category"
            value={categorySearch}
            onChange={(e) => {
              setCategorySearch(e.target.value);
              setShowCategoryDropdown(true);
            }}
            onFocus={() => setShowCategoryDropdown(true)}
            required
          />

          {showCategoryDropdown && (
            <ul className="category-dropdown">
              {categories
                .filter((cat) =>
                  cat.name.toLowerCase().includes(categorySearch.toLowerCase())
                )
                .map((cat) => (
                  <li
                    key={cat.id}
                    onClick={() => {
                      setExpense((prev) => ({ ...prev, categoryName: cat.name }));
                      setCategorySearch(cat.name);
                      setShowCategoryDropdown(false);
                    }}
                  >
                    {cat.name}
                  </li>
                ))}
            </ul>
          )}
        </div>

        <button type="submit" disabled={isSubmitting}>
          {isSubmitting ? "Saving..." : "Add Expense"}
        </button>
      </form>

      {isLoading ? (
        <p>Loading expenses...</p>
      ) : expenses.length === 0 ? (
        <p>No expenses found.</p>
      ) : (
        <div className="expenses-grid">
          {expenses.map((exp) => (
            <div
              key={exp.id}
              className={`expense-card ${editingId === exp.id ? "editing" : ""}`}
            >
              {editingId === exp.id ? (
                <div className="card-content">
                  <input
                    name="title"
                    value={editingExpense.title}
                    onChange={handleInlineChange}
                    placeholder="Title"
                  />
                  <input
                    name="description"
                    value={editingExpense.description}
                    onChange={handleInlineChange}
                    placeholder="Description"
                  />
                  <input
                    type="number"
                    name="amount"
                    value={editingExpense.amount}
                    onChange={handleInlineChange}
                    placeholder="Amount"
                  />
                  <select
                    name="type"
                    value={editingExpense.type}
                    onChange={handleInlineChange}
                  >
                    <option value="EXPENSE">Expense</option>
                    <option value="INCOME">Income</option>
                  </select>
                  <input
                    name="categoryName"
                    value={editingExpense.categoryName}
                    onChange={handleInlineChange}
                    placeholder="Category"
                  />
                  <div className="card-actions">
                    <button className="save" onClick={handleSaveEdit}>
                      Save
                    </button>
                    <button className="cancel" onClick={handleCancelEdit}>
                      Cancel
                    </button>
                  </div>
                </div>
              ) : (
                <div className="card-content">
                  <h3>{exp.title}</h3>
                  <p>{exp.description}</p>
                  <p><strong>Amount:</strong> ${exp.amount}</p>
                  <p><strong>Type:</strong> {exp.type}</p>
                  <p><strong>Date:</strong> {new Date(exp.date).toLocaleDateString()}</p>
                  <p><strong>Category:</strong> {exp.categoryName}</p>
                  <div className="card-actions">
                    <button className="edit" onClick={() => handleEdit(exp)}>Edit</button>
                    <button className="delete" onClick={() => handleDelete(exp.id)}>Delete</button>
                  </div>
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Expenses;

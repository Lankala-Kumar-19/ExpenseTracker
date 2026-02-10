import { useEffect, useState } from "react";
import { getAllCategories, updateCategory, deleteCategory } from "../adminService";
import { useNavigate } from "react-router-dom";
import "../styles/categoriesList.css";
const CategoriesList = () => {
  const [categories, setCategories] = useState([]);
  const [editingCategory, setEditingCategory] = useState(null);
  const [editName, setEditName] = useState("");

  const [searchTerm, setSearchTerm] = useState("");
  const [filteredCategories, setFilteredCategories] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    fetchCategories();
  }, []);

  // Fetch all categories
  const fetchCategories = async () => {
    const data = await getAllCategories();
    const categoriesList = data.content; // assuming API returns content array
    setCategories(categoriesList);
    setFilteredCategories(categoriesList); // assuming API returns content array
  };

  // Start editing a category
  const startEdit = (category) => {
    setEditingCategory(category.id);
    setEditName(category.name);
  };

  // Save updated category
  const handleUpdate = async (id) => {
    if (!editName || editName.trim() === "") {
      alert("Category name cannot be empty");
      return;
    }
    await updateCategory(id, { name: editName });
    setEditingCategory(null);
    fetchCategories();
  };

  // Delete category
  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this category?")) {
      await deleteCategory(id);
      fetchCategories();
    }
  };
  const handleSearch = (e) => {
  const value = e.target.value;
  setSearchTerm(value);

  const filtered = categories.filter((cat) =>
    cat.name.toLowerCase().includes(value.toLowerCase())
  );

  setFilteredCategories(filtered);
};



return (
  <div className="categories-page">
    <button className="back-btn" onClick={() => navigate("/admin/dashboard")}>
      Back
    </button>
    <div>
            <input
  type="text"
  className="search-input"
  placeholder="Search by category name..."
  value={searchTerm}
  onChange={handleSearch}
/>
    </div>


    {filteredCategories.length > 0 ? (
      <table className="categories-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredCategories.map((c) => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>
                {editingCategory === c.id ? (
                  <input
                    type="text"
                    value={editName}
                    onChange={(e) => setEditName(e.target.value)}
                    className="edit-input"
                  />
                ) : (
                  c.name
                )}
              </td>
              <td className="actions">
                {editingCategory === c.id ? (
                  <>
                    <button className="save-btn" onClick={() => handleUpdate(c.id)}>
                      Save
                    </button>
                    <button
                      className="cancel-btn"
                      onClick={() => setEditingCategory(null)}
                    >
                      Cancel
                    </button>
                  </>
                ) : (
                  <>
                    <button className="edit-btn" onClick={() => startEdit(c)}>
                      Update
                    </button>
                    <button
                      className="delete-btn"
                      onClick={() => handleDelete(c.id)}
                    >
                      Delete
                    </button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    ) : (
      <p className="empty-state">No categories found</p>
    )}
  </div>
);
};



export default CategoriesList;

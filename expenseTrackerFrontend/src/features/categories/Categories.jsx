import { useEffect, useState } from "react";
import { addCategory, deleteCategory, editCategory, getAllCategories } from "./categoryService";
import "./categories.css";
const Categories = () => {
  const [categories, setCategories] = useState([]);
  const [category, setCategory] = useState({ name: "" });
  const [updateId, setUpdateId] = useState(null);
  const [updateCategory, setUpdateCategory] = useState({});
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchCategories = async () => {
    setIsLoading(true);
    setError(null);
    try {
      const data = await getAllCategories();
      setCategories(data);
    } catch (err) {
      console.error(err);
      setCategories([]);
      setError("Failed to load categories");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchCategories();
  }, []);

  const addCat = async () => {
    if (!category.name) {
      setError("Category name cannot be empty");
      return;
    }
    setError(null);
    setIsLoading(true);
    try {
      await addCategory(category);
      setCategory({ name: "" });
      fetchCategories();
    } catch {
      setError("Failed to add category");
    } finally {
      setIsLoading(false);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this category?")) return;
    setError(null);
    setIsLoading(true);
    try {
      await deleteCategory(id);
      fetchCategories();
    } catch {
      setError("Failed to delete category");
    } finally {
      setIsLoading(false);
    }
  };

  const handleEdit = (cat) => {
    setUpdateId(cat.id);
    setUpdateCategory(cat);
  };

  const handleCancelEdit = () => {
    setUpdateId(null);
    setUpdateCategory({});
  };

  const handleSaveEdit = async () => {
    if (!updateCategory.name) {
      setError("Name cannot be empty");
      return;
    }
    setError(null);
    setIsLoading(true);
    try {
      await editCategory(updateId, updateCategory);
      setUpdateId(null);
      setUpdateCategory({});
      fetchCategories();
    } catch {
      setError("Failed to update category");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="page categories-page">
      <h2 className="page-title">Categories</h2>

      {error && <p className="error">{error}</p>}

      <div className="form-row">
        <input
          name="name"
          value={category.name}
          placeholder="New category"
          onChange={(e) => setCategory({ name: e.target.value })}
        />
        <button onClick={addCat} disabled={isLoading}>
          Add
        </button>
      </div>
      <small className="input-hint">Enter a category name like "Food", "Rent", or "Travel"</small>

      {!isLoading && categories.length === 0 && (
  <p className="empty-message">
    No categories yet. Create your first category above 👆
  </p>
)}

      <div className="categories-grid">
        {categories.map((cat) => (
          <div key={cat.id}
           className={`category-card ${updateId === cat.id ? "editing" : ""}`}>
            {updateId === cat.id ? (
              <input
                value={updateCategory.name}
                onChange={(e) =>
                  setUpdateCategory({ ...updateCategory, name: e.target.value })
                }
                placeholder="e.g., Food, Rent, Utilities"
              />
              
            ) : (
              <h3>{cat.name}</h3>
            )}

            <div className="card-actions">
              {updateId === cat.id ? (
                <>
                  <button className="save" onClick={handleSaveEdit}>
                    Save
                  </button>
                  <button className="cancel" onClick={handleCancelEdit}>
                    Cancel
                  </button>
                </>
              ) : (
                <>
                  <button className="edit" onClick={() => handleEdit(cat)}>
                    Edit
                  </button>
                  <button className="delete" onClick={() => handleDelete(cat.id)}>
                    Delete
                  </button>
                </>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Categories;

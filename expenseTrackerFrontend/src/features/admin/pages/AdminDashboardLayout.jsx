import { Outlet, NavLink, useNavigate } from "react-router-dom";
import { useAuthStore } from "../../auth/store/authStore";
import "../styles/dashboard.css";

const AdminDashboardLayout = () => {
  const navigate = useNavigate();
  const logout = useAuthStore((state) => state.logout);

  return (
    <div className="dashboard">
      {/* Sidebar */}
      <aside className="sidebar">
        <h2 className="logo">Admin Panel</h2>

        <nav className="nav">
          <NavLink to="/admin/dashboard" end className="nav-link">
            Overview
          </NavLink>
          <NavLink to="/admin/users" className="nav-link">
            Users
          </NavLink>
          <NavLink to="/admin/expenses" className="nav-link">
            Expenses
          </NavLink>
          <NavLink to="/admin/categories" className="nav-link">
            Categories
          </NavLink>
        </nav>
      </aside>

      {/* Main Section */}
      <div className="main">
        {/* Top Navbar */}
        <header className="header">
          <button
            className="admin-btn"
            onClick={() => navigate("/")}
          >
            User Dashboard
          </button>

          <button className="logout-btn" onClick={logout}>
            Logout
          </button>
        </header>

        {/* Page Content */}
        <main className="content">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default AdminDashboardLayout;

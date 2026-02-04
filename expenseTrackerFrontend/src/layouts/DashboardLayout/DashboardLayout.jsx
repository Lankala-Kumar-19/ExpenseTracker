import { Outlet, NavLink } from "react-router-dom";
import { useAuthStore } from "../../features/auth/store/authStore";
import "./dashboard.css";

const DashboardLayout = () => {
  const logout = useAuthStore((state) => state.logout);

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <h2 className="logo">Expense Tracker</h2>

        <nav className="nav">
          <NavLink to="/" end className="nav-link">
            Dashboard
          </NavLink>
          <NavLink to="/expenses" className="nav-link">
            Expenses
          </NavLink>
          <NavLink to="/categories" className="nav-link">
            Categories
          </NavLink>
        </nav>
      </aside>

      <div className="main">
        <header className="header">
          <button className="logout-btn" onClick={logout}>
            Logout
          </button>
        </header>

        <main className="content">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;

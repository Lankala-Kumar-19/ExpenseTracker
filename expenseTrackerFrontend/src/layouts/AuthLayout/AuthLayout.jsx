import { Outlet } from "react-router-dom";

const AuthLayout = () => {
  return (
    <div style={styles.wrapper}>
      <div style={styles.card}>
        <h2 style={styles.title}>Expense Tracker</h2>
        <Outlet />
      </div>
    </div>
  );
};

const styles = {
  wrapper: {
    minHeight: "100vh",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
    background: "#f5f6fa",
  },
  card: {
    width: "100%",
    maxWidth: "400px",
    padding: "2rem",
    borderRadius: "8px",
    background: "#fff",
    boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
  },
  title: {
    textAlign: "center",
    marginBottom: "1.5rem",
  },
};

export default AuthLayout;

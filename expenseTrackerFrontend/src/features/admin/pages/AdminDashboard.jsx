const AdminDashboard = () => {
  return (
    <div className="page">
      <h2 className="page-title">Admin Dashboard 🛠️</h2>

      <p className="intro-text">
        Welcome, Admin! You have full control over users, expenses, and categories
        across the entire application.
      </p>

      {/* About Admin Panel */}
      <section className="info-card">
        <h3>About the Admin Panel</h3>
        <p>
          The Admin Panel allows you to manage the platform efficiently. You can
          monitor users, control access levels, and maintain all financial records
          to ensure data consistency and security.
        </p>
      </section>

      {/* Admin Capabilities */}
      <section className="info-card">
        <h3>Admin Capabilities</h3>
        <ul className="feature-list">
          <li>👥 View all registered users</li>
          <li>🔑 Change user roles (USER ↔ ADMIN)</li>
          <li>❌ Delete users (self-deletion restricted)</li>
          <li>💰 View all expenses across users</li>
          <li>✏️ Update any expense</li>
          <li>🗑 Delete any expense</li>
          <li>🗂 View all categories</li>
          <li>✏️ Update category names</li>
          <li>🗑 Delete categories</li>
        </ul>
      </section>

      {/* How Admin Uses Panel */}
      <section className="info-card">
        <h3>How to Use the Admin Panel</h3>
        <ol className="steps">
          <li>Go to <strong>Users</strong> to manage user accounts and roles.</li>
          <li>Navigate to <strong>Expenses</strong> to audit or correct entries.</li>
          <li>Use <strong>Categories</strong> to maintain clean and consistent data.</li>
          <li>Delete invalid or duplicate records when necessary.</li>
        </ol>
      </section>

      {/* Safety Tip */}
      <div className="tip-box">
        ⚠️ Tip: Role changes and deletions affect system-wide data. Always double-check before making changes.
      </div>

      {/* Security Notice */}
      <section className="info-card">
        <h3>Security & Restrictions 🔒</h3>
        <ul className="future-features">
          <li>🚫 Admins cannot delete their own account</li>
          <li>🔐 All admin actions are protected by role-based access</li>
          <li>📜 Backend validation ensures data integrity</li>
        </ul>
      </section>

      {/* Admin Note */}
      <div className="suggestion-box">
        🧠 Admin Note: Maintain clean data and user roles to ensure a smooth experience for all users.
      </div>
    </div>
  );
};

export default AdminDashboard;

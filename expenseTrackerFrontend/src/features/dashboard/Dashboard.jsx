const Dashboard = () => {
  return (
    <div className="page">
      <h2 className="page-title">Welcome to Expense Tracker 👋</h2>

      <p className="intro-text">
        Expense Tracker helps you manage your money by tracking your daily
        expenses and income in a simple and organized way.
      </p>

      {/* About Card */}
      <section className="info-card">
        <h3>About the App</h3>
        <p>
          Record where your money comes from and where it goes. Categorize expenses,
          track income, and keep everything neatly organized.
        </p>
      </section>

      {/* Features Card */}
      <section className="info-card">
        <h3>Key Features</h3>
        <ul className="feature-list">
          <li>💰 Add and manage expenses and income</li>
          <li>🗂 Create and organize categories</li>
          <li>✏️ Edit or delete entries anytime</li>
          <li>📊 View all your financial records in one place</li>
        </ul>
      </section>

      {/* How to Use Card */}
      <section className="info-card">
        <h3>How to Use</h3>
        <ol className="steps">
          <li>Go to <strong>Categories</strong> and create categories (Food, Rent, Salary, etc.).</li>
          <li>Navigate to <strong>Expenses</strong>.</li>
          <li>Fill in the expense title, amount, type (Expense or Income), and select a category.</li>
          <li>Click <strong>Add Expense</strong> to save it.</li>
          <li>Edit or delete entries anytime from the list.</li>
        </ol>
      </section>

      {/* Tip Box */}
      <div className="tip-box">
        💡 Tip: Add your categories first before recording expenses for a smoother experience.
      </div>

      {/* Future Features */}
      <section className="info-card">
        <h3>Future Features 🚀</h3>
        <ul className="future-features">
          <li>📊 Analytics dashboard with charts and graphs</li>
          <li>🔔 Budget alerts and notifications for overspending</li>
          <li>📅 Recurring expenses and income tracking</li>
          <li>🌙 Dark mode for comfortable viewing</li>
          <li>💾 Export your data to CSV or PDF</li>
          <li>🔒 Secure login with multi-device sync</li>
        </ul>
      </section>

      {/* Suggestion Box */}
      <div className="suggestion-box">
        💡 Feature Suggestions: Send your ideas to <strong>lankalakumar08@gmail.com</strong> or WhatsApp at <strong>8309643257</strong>.
      </div>
    </div>
  );
};

export default Dashboard;

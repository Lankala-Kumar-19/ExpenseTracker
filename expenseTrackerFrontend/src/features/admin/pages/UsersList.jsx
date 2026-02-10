import { useEffect, useState } from "react";
import { getAllUsers, deleteUserById, changeUserRole } from "../adminService";
import { useNavigate } from "react-router-dom";
import "../styles/usersList.css";
const UsersList = () => {
  const [users, setUsers] = useState([]);
  const [searchName, setSearchName] = useState("");
  const [filteredUsers, setFilteredUsers] = useState([]);

  const navigate = useNavigate();

  useEffect(() => {
    fetchUsers();
  }, []);

  // Fetch users from adminService
  const fetchUsers = async () => {
    try {
      const usersData = await getAllUsers();
      // Ensure each user has a role property for the dropdown
      const usersWithRole = usersData.map(u => ({ ...u, role: u.role || "USER" }));
      setUsers(usersWithRole);
      setFilteredUsers(usersWithRole);
    } catch (error) {
      console.error("Failed to fetch users:", error);
    }
  };

  // Search users by username
  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearchName(value);
    const filtered = users.filter(user =>
      user.username.toLowerCase().includes(value.toLowerCase())
    );
    setFilteredUsers(filtered);
  };

  // Delete user using adminService
  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      await deleteUserById(id);
      fetchUsers(); // refresh list after deletion
    }
  };

  const handleRoleChange = async (id, newRole) => {
    await changeUserRole(id, newRole);
    
    setUsers(prev => prev.map(u => u.id === id ? { ...u, role: newRole } : u));
    setFilteredUsers(prev => prev.map(u => u.id === id ? { ...u, role: newRole } : u));
  };


return (
  <div className="users-page">
    <button onClick={() => navigate("/admin/dashboard")}>Back</button>

    <div className="users-search">
      <input
        type="text"
        placeholder="Search by username"
        value={searchName}
        onChange={handleSearchChange}
      />
    </div>

    {filteredUsers.length > 0 ? (
      <table className="users-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Expenses</th>
            <th>Role</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {filteredUsers.map((u) => (
            <tr key={u.id}>
              <td>{u.id}</td>
              <td>{u.username}</td>
              <td>{u.mail}</td>
              <td>{u.expenses?.length || 0}</td>
              <td>
                <select
                  className="role-select"
                  value={u.role}
                  onChange={(e) => handleRoleChange(u.id, e.target.value)}
                >
                  <option value="USER">USER</option>
                  <option value="ADMIN">ADMIN</option>
                </select>
              </td>
              <td>
                <button
                  className="delete-btn"
                  onClick={() => handleDelete(u.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    ) : (
      <p className="no-users">No users found</p>
    )}
  </div>
);

};

export default UsersList;

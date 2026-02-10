import httpClient from "../../services/httpClient";


//expenses

export const getAllExpenses = async() => {
    const expenses = await httpClient.get('/admin/expenses');
    return expenses.data;
}

export const updateExpense = async (id, data) => {
    const response = await httpClient.put(`/admin/expenses/${id}`, data);
    return response.data;
}

export const deleteExpenseById = async (id) => {
    await httpClient.delete(`/admin/expenses/${id}`);
}


// categories

export const getAllCategories = async() => {
    const categories = await httpClient.get('/admin/categories');
    return categories.data;
}

export const updateCategory = async (id, data) => {
    const response = await httpClient.put(`/admin/categories/${id}`, data);
    return response.data;
}

export const deleteCategory = async (id) => {
    await httpClient.delete(`/admin/categories/${id}`);
}


//users

export const getAllUsers = async()=>{
    const users = await httpClient.get('/admin/users');
    return users.data.content;
}

export const getUserByName = async(username) => {
    const user = await httpClient.get(`/admin/users/${username}`);
    return user.data;
}

export const deleteUserById = async(id) => {
    await httpClient.delete(`/admin/users/${id}`);
}

export const changeUserRole = async (id, role) => {
    const response = await httpClient.put(`/admin/users/${id}/role`,  role );
    return response.data;
}
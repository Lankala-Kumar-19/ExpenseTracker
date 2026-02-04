import httpClient from "../../services/httpClient"



export const getAllExpenses = async() => {
    const expenses = await httpClient.get("/expenses")

    return expenses.data.content;
}

export const addExpense = async(expense) => {
    try{
        await httpClient.post("/expenses",expense);
    }
    catch(err){
        throw err;
    }

}

export const deleteExpense = async(expenseId) => {
    try{
        await httpClient.delete(`expenses/${expenseId}`);
    }catch(err){
        throw err;
    }
}

export const updateExpense = async(expenseId,updatedExpense) => {
    try{
        await httpClient.put(`expenses/${expenseId}`,updatedExpense);
    }catch(err){
        throw err;
    }
}
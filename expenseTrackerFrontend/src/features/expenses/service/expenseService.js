import httpClient from "../../../services/httpClient"



export const getAllExpenses = async() => {
    const expenses = await httpClient.get("/expenses")

    return expenses;
}
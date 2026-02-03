import httpClient from "../../../services/httpClient"

export const registerUser = async(userData) => {
    const res = await httpClient.post("/register",userData);
    return res.data;
}
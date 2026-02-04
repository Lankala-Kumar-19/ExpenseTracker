import httpClient from "../../services/httpClient"

export const getAllCategories = async() => {
    try{
        const res = await httpClient.get("/categories");
        return res.data.content;
    }
    catch(err){
        throw err;
    }
}

export const addCategory = async(category) => {
    try{
        await httpClient.post("/categories",category);
    }
    catch(err){
        throw err;
    }
}

export const deleteCategory = async(catId) => {
    try{
        await httpClient.delete(`categories/${catId}`);
    }catch(err){
        throw err;
    }
}

export const editCategory = async(catId,category) => {
    try{
        await httpClient.put(`categories/${catId}`,category);
    }catch(err){
        throw err;
    }
}
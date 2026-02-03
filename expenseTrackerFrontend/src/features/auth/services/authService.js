import httpClient from "../../../services/httpClient"


export const loginUser = async(credentials) => {
    console.log(credentials);
    
    try{
        const response = await httpClient.post("/login",credentials);
        console.log(response.data);
    
        return response.data;
    }catch(err){
        console.error("login failed: ", err.response || err);
        throw err; 
    }
}




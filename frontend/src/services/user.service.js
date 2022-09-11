import axios from "axios";
import authHeader from "./auth-header";


const getPublicContent = () => {
  return axios.get("/api/user",{ headers: authHeader() })
};

const getUserBoard = () => {
  return axios.get("/api/user", { headers: authHeader() });
};

const create = (username, email, password) => {
  return axios.post("/api/user", {
     username,
     email,
     password,
  }, { headers: authHeader()}).then((response) => {
  return response.data;
});
};

const UserService = {
  getPublicContent,
  getUserBoard,
  create
};

export default UserService;

import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8082/user/";

const getPublicContent = () => {
  return axios.get("/api/user")
};

const getUserBoard = () => {
  return axios.get("/api/user", { headers: authHeader() });
};

const getModeratorBoard = () => {
  return axios.get(API_URL + "mod", { headers: authHeader() });
};

const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
};

const UserService = {
  getPublicContent,
  getUserBoard,
  getModeratorBoard,
  getAdminBoard,
};

export default UserService;

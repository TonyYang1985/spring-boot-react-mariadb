import axios from "axios";


const register = (username, email, password) => {
  return axios.post("/api/auth/signup", {
    username,
    email,
    password,
  }).then((response) => {
  if (response.data.jwttoken && response.data.user) {
    localStorage.setItem("user", JSON.stringify(response.data.user));
    localStorage.setItem("token", JSON.stringify(response.data.jwttoken));
  }

  return response.data;
});

};

const login = (username, password) => {
  return axios
    .post("/api/auth/signin", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.jwttoken && response.data.jwttoken) {
        localStorage.setItem("user", JSON.stringify(response.data.user));
        localStorage.setItem("token", JSON.stringify(response.data.jwttoken));
      }
      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("token");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const getCurrentUserToken = () => {
  return JSON.parse(localStorage.getItem("token"));
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
  getCurrentUserToken
};

export default AuthService;

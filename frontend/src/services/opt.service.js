import axios from "axios";
import authHeader from "./auth-header";


const topup = (username, email, password) => {
  return axios.post("/api/opt/topup", {
    username,
    email,
    password,
  }, { headers: authHeader() }).then((response) => {
    return response.data;
});
};

const transfer = (amount, payee) => {
  return axios
    .post("/api/opt/transfer", {
       amount,
       payee,
    }),{ headers: authHeader() }
    .then((response) => {
      return response.data;
    });
};



const OptService = {
    topup,
    transfer
};

export default OptService;

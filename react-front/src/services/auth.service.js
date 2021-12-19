import axios from "axios";

const AUTH_API_URL = "http://localhost:1234/api/v1/auth";
const USER_API_URL = "http://localhost:2234/api/v1/user";

const register = (name, email, password) => {
  return axios
    .post(USER_API_URL, {
      name,
      email,
      password,
    })
    .then((response) => {
      return response;
    })
    .catch((error) => {
      console.log(error);
    });
};

const login = (email, password) => {
  return axios
    .post(AUTH_API_URL, {
      email,
      password,
    })
    .then((response) => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }

      return response.data;
    })
    .catch((error) => {
      console.log(error.response);
    });
};

const logout = () => {
  localStorage.removeItem("user");
};

export default {
  register,
  login,
  logout,
};

import axios from "axios";

const AUTH_API_URL = "http://localhost:10020/api/v1/auth";
const USER_API_URL = "http://localhost:10010/api/v1/user";

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

const sendEmail = (email) => {
  return axios
    .post(AUTH_API_URL + "/verify/send", {
      email,
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.log(error);
    });
};

const verify = (email, code) => {
  return axios
    .post(AUTH_API_URL + "/verify", {
      email,
      code,
    })
    .then((response) => {
      return response.status;
    })
    .catch((error) => {
      console.log(error.response);
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
  sendEmail,
  verify,
  logout,
};

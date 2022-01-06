import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:10010/api/v1/user";

const getAllUser = () => {
  return axios.get(API_URL, { headers: authHeader() });
};

const getMyProfile = () => {
  return axios.get(API_URL + "/me", { headers: authHeader() });
};

const getUserProfile = (id) => {
  return axios.get(API_URL + "/" + id, { headers: authHeader() });
};

export default {
  getAllUser,
  getMyProfile,
};

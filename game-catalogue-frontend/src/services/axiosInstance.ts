import axios from "axios";
import { BASE_URI } from "../constants/axios";

export const axiosInstance = axios.create({
  baseURL: BASE_URI,
  withCredentials: true,
  proxy: {
    host: BASE_URI,
    port: 8080,
  },
});

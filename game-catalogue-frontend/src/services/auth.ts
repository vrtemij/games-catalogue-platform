import { AxiosResponse } from "axios";
import { axiosInstance } from "./axiosInstance";
import { UserAuthFormData } from "../interfaces";

export const registerUser = (
  formData: UserAuthFormData
): Promise<AxiosResponse<UserAuthFormData>> =>
  axiosInstance.post("/authenticate/register", formData);
export const loginUser = (
  formData: UserAuthFormData
): Promise<AxiosResponse<UserAuthFormData>> =>
  axiosInstance.post("/authenticate/login", formData);

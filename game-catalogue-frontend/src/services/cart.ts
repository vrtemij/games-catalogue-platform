import { Cart, Game } from "../interfaces";
import { axiosInstance } from "./axiosInstance";
import { AxiosResponse } from "axios";

export const getCart = (): Promise<AxiosResponse<Cart>> =>
  axiosInstance.get("/cart");
export const updateCart = ({ id }: { id: Game["id"] }) =>
  axiosInstance.put("/cart", { id });
export const removeFromCart = ({ id }: { id: Game["id"] }) =>
  axiosInstance.delete("/cart", { data: { id } });

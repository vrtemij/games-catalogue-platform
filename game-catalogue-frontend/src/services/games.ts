import { AxiosResponse } from "axios";

import { Game, PagedListResponse } from "../interfaces";
import { axiosInstance } from "./axiosInstance";

export const getGamesList = (): Promise<
  AxiosResponse<PagedListResponse<Game>>
> => axiosInstance.get("/games");

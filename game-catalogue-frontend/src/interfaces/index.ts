export enum GameCategory {
  Action = "Action",
  Strategy = "Strategy",
  Adventure = "Adventure",
  Sport = "Sport",
  Racing = "Racing",
}

export interface Game {
  id: number;
  name: string;
  description: string;
  category: GameCategory;
  price: string;
}

export interface GameProduct extends Game {
  inCart?: boolean;
}

export interface Cart {
  total: string;
  games: Game[];
}

export interface PagedListResponse<T> {
  items: T[];
  totalCount: number;
}

export interface UserAuthFormData {
  username: string;
  password: string;
  token?: string;
}

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
  inCart: boolean;
}

import { FC, useState } from "react";
import { Flex } from "@chakra-ui/react";

import { Game, GameCategory, GameProduct } from "../interfaces";

import { GameCard } from "../components";

interface Props {}

export const Home: FC<Props> = () => {
  const [gamesList, setGamesList] = useState<GameProduct[]>([
    {
      id: 1,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Adventure,
      price: "$119",
      inCart: false,
    },
    {
      id: 2,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Action,
      price: "$119",
      inCart: false,
    },
    {
      id: 3,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Racing,
      price: "$119",
      inCart: false,
    },
    {
      id: 4,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Sport,
      price: "$119",
      inCart: false,
    },
    {
      id: 5,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Strategy,
      price: "$119",
      inCart: false,
    },
  ]);

  const handleAddToCart = (id: Game["id"]) => {
    setGamesList(
      gamesList.map((game) => {
        if (id === game.id) {
          return {
            ...game,
            inCart: true,
          };
        }

        return game;
      })
    );
  };

  const handleRemoveFromCart = (id: Game["id"]) => {
    setGamesList(
      gamesList.map((game) => {
        if (id === game.id) {
          return {
            ...game,
            inCart: false,
          };
        }

        return game;
      })
    );
  };

  return (
    <Flex p={3} gap={3} flexWrap="wrap" alignContent="baseline">
      {gamesList.map((gameProps) => (
        <GameCard
          key={gameProps.id}
          handleAddToCart={handleAddToCart}
          handleRemoveFromCart={handleRemoveFromCart}
          {...gameProps}
        />
      ))}
    </Flex>
  );
};

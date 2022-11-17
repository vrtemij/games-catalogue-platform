import { FC, useEffect, useState } from "react";
import { Flex } from "@chakra-ui/react";

import { Game, GameProduct } from "../interfaces";
import { GameCard, GamesListLoader } from "../components";
import { getGamesList, removeFromCart, updateCart } from "../services";
import { HTTP_STATUSES } from "../constants";

export const Home: FC = () => {
  const [isLoading, setLoading] = useState(false);
  const [gamesList, setGamesList] = useState<GameProduct[]>([]);

  useEffect(() => {
    setLoading(true);
    getGamesList()
      .then(({ data }) => {
        if (data?.items) {
          setGamesList(data.items);
        }
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  const handleAddToCart = async (id: Game["id"]) => {
    updateCart({ id }).then(({ status }) => {
      if (status === HTTP_STATUSES.OK) {
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
      }
    });
  };

  const handleRemoveFromCart = (id: Game["id"]) => {
    removeFromCart({ id }).then(({ status }) => {
      if (status === HTTP_STATUSES.OK) {
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
      }
    });
  };

  if (isLoading) {
    return <GamesListLoader />;
  }

  if (!isLoading && !gamesList?.length) {
    return (
      <Flex h="100%" w="100%" justify="center" align="center">
        We are sorry, but all games are sold out :(
      </Flex>
    );
  }

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

import { FC, useState } from "react";
import {
  Flex,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Text,
  Heading,
  StackDivider,
  Box,
  Stack,
  Badge,
  Button,
} from "@chakra-ui/react";

import { Game, GameCategory } from "../interfaces";

interface Props {}

export const Cart: FC<Props> = () => {
  const [gamesList] = useState<Game[]>([
    {
      id: 1,
      name: "Grand Theft Auto",
      description: ` Grand Theft Auto is a series of action-adventure games created by
          David Jones and Mike Dailly. Later titles were developed under the
          oversight of brothers Dan and Sam Houser, Leslie Benzies and Aaron
          Garbut.`,
      category: GameCategory.Adventure,
      price: "$119",
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
    },
  ]);

  const totalSum = "$" + 119 * 4;

  return (
    <Card>
      <CardHeader>
        <Heading alignSelf="left">Checkout invoice</Heading>
      </CardHeader>
      <CardBody>
        <Stack divider={<StackDivider />} spacing="4">
          {gamesList.map((game) => (
            <Box key={game.id}>
              <Heading size="xs" textTransform="uppercase">
                {game.name}
              </Heading>
              <Badge colorScheme="pink">{game.category}</Badge>
              <Text pt="2" fontSize="sm">
                {game.description}
              </Text>
              <Text>{game.price}</Text>
            </Box>
          ))}
        </Stack>
      </CardBody>
      <CardFooter justify="space-between" alignItems="center">
        Total: {totalSum}
        <Button colorScheme="teal">Checkout</Button>
      </CardFooter>
    </Card>
  );
};

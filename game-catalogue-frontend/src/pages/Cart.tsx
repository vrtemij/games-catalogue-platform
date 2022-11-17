import { FC, useEffect, useState } from "react";
import {
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

import { Cart as CartInterface } from "../interfaces";
import { getCart } from "../services";

interface Props {}

export const Cart: FC<Props> = () => {
  const [isLoading, setLoading] = useState(true);
  const [cart, setCart] = useState<CartInterface>({
    total: "",
    games: [],
  });

  useEffect(() => {
    setLoading(true);
    getCart()
      .then(({ data }) => {
        setCart(data);
      })
      .finally(() => {
        setLoading(false);
      });
  }, []);

  return (
    <Card>
      <CardHeader>
        <Heading alignSelf="left">Checkout invoice</Heading>
      </CardHeader>
      <CardBody>
        <Stack divider={<StackDivider />} spacing="4">
          {cart?.games?.length
            ? cart.games.map((game) => (
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
              ))
            : "Your cart is empty"}
        </Stack>
      </CardBody>
      <CardFooter justify="space-between" alignItems="center">
        {cart?.total && `Total: ${cart?.total}`}
        <Button disabled={isLoading || !cart?.games?.length} colorScheme="teal">
          Checkout
        </Button>
      </CardFooter>
    </Card>
  );
};

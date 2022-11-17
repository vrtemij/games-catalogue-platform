import { FC } from "react";
import { Badge, Box, IconButton, Center, Flex, Text } from "@chakra-ui/react";
import { FaGamepad, FaCartPlus, FaMinus } from "react-icons/fa";
import { GameProduct } from "../interfaces";

interface Props extends GameProduct {
  handleAddToCart: (id: GameProduct["id"]) => void;
  handleRemoveFromCart: (id: GameProduct["id"]) => void;
}

export const GameCard: FC<Props> = ({
  id,
  name,
  description,
  category,
  price,
  inCart,
  handleAddToCart,
  handleRemoveFromCart,
}) => {
  return (
    <Box p="5" w="320px" h="375px" borderRadius="10px" borderWidth="1px">
      <Center>
        <Box as={FaGamepad} h={100} w={100} />
      </Center>
      <Flex mt={2}>
        <Badge colorScheme="pink">{category}</Badge>
      </Flex>
      <Flex mt={2}>
        <Text fontSize="xl" fontWeight="semibold" lineHeight="short">
          {name}
        </Text>
      </Flex>
      <Flex mt={2}>
        <Text fontSize="sm" align="left">
          {description}
        </Text>
      </Flex>
      <Flex justify="space-between">
        <Text mt={2}>{price}</Text>
        <IconButton
          onClick={() =>
            !inCart ? handleAddToCart(id) : handleRemoveFromCart(id)
          }
          icon={inCart ? <FaMinus /> : <FaCartPlus />}
          colorScheme="teal"
          aria-label="add-to-cart"
        />
      </Flex>
    </Box>
  );
};

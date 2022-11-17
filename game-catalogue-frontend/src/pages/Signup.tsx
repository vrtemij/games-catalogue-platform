import React from "react";
import { Button, Flex, FormControl, FormLabel, Input } from "@chakra-ui/react";

export const Signup = () => {
  return (
    <Flex direction="column" w="50%" justifySelf="center">
      <FormControl>
        <FormLabel>Username</FormLabel>
        <Input autoComplete="username" placeholder="John Doe" />
      </FormControl>
      <FormControl mt={4}>
        <FormLabel>Password</FormLabel>
        <Input
          autoComplete="new-password"
          type="password"
          placeholder="Secret"
        />
      </FormControl>
      <Button colorScheme="blue" mt={4}>
        Create
      </Button>
    </Flex>
  );
};

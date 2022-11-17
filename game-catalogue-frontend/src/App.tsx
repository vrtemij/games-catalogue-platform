import * as React from "react";
import { ChakraProvider, Grid, theme, Flex } from "@chakra-ui/react";
import {
  CheckoutButton,
  ColorModeSwitcher,
  AccountButton,
  HomeButton,
} from "./components";
import { Outlet } from "react-router-dom";

export const App = () => {
  return (
    <ChakraProvider theme={theme}>
      <Flex maxH="10vh" justifyContent="space-between" p={3}>
        <HomeButton />
        <Flex>
          <CheckoutButton />
          <AccountButton />
          <ColorModeSwitcher />
        </Flex>
      </Flex>
      <Grid h="90vh" p={3}>
        <Outlet />
      </Grid>
    </ChakraProvider>
  );
};

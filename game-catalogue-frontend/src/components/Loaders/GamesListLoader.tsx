import { FC } from "react";
import {
  Box,
  Center,
  Flex,
  Skeleton,
  SkeletonCircle,
  SkeletonText,
} from "@chakra-ui/react";

export const GamesListLoader: FC = () => {
  return (
    <>
      {new Array(3).fill("").map((val, index) => (
        <Flex
          key={index}
          p="5"
          w="320px"
          h="375px"
          borderRadius="10px"
          borderWidth="1px"
          direction="column"
          justify="space-between"
          boxShadow="lg"
        >
          <Box>
            <Center>
              <SkeletonCircle size="100" />
            </Center>
            <Skeleton mt="8" height="18px" width="55px" />
            <Skeleton mt="4" height="18px" width="155px" />
            <SkeletonText mt="4" noOfLines={4} spacing="4" />
          </Box>
          <Flex align="center" justify="space-between">
            <Skeleton height="26px" width="32px" />
            <Skeleton height="40px" width="40px" />
          </Flex>
        </Flex>
      ))}
    </>
  );
};

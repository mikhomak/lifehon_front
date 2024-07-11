package com.lifehon_front.service

import com.apollographql.apollo3.ApolloClient
import org.springframework.stereotype.Service

@Service
class ApolloServerConnector() {

    final var apolloClient: ApolloClient = ApolloClient.Builder()
        .serverUrl("http://localhost:8600/front-api/v1/")
        .build();

}
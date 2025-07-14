package com.rohitEnterprises.firstSuccessfullAPI.service;
import com.rohitEnterprises.firstSuccessfullAPI.entity.User;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;


import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("jyoti").password("shyam").build()),
                Arguments.of(User.builder().userName("shambhu").password("").build())
        );
    }
}

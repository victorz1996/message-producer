package co.com.testrabbit.api;

import co.com.testrabbit.model.message.Message;
import co.com.testrabbit.usecase.sendmessage.SendMessageUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {
    @Value("${broker.routingKey}")
    private String routingKey;

    @Value("${broker.routingKeyImpares}")
    private String routingKeyImpares;
    private final SendMessageUseCase sendMessageUseCase;

    public Mono<ServerResponse> listenPOSTUseCase(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Message.class)
                .flatMap(message -> message.getIsForAll().equals(Boolean.TRUE) ? sendMessageUseCase.sendMessage(message, routingKey) : sendMessageUseCase.sendMessage(message, routingKeyImpares))
                .flatMap(response -> response.equals(Boolean.TRUE) ? ServerResponse.ok().bodyValue("Se envio el mensaje") : ServerResponse.noContent().build());
    }
}

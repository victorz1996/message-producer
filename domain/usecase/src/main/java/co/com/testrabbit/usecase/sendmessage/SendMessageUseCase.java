package co.com.testrabbit.usecase.sendmessage;

import co.com.testrabbit.model.message.Message;
import co.com.testrabbit.model.message.gateways.MessageMQService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SendMessageUseCase {
    private final MessageMQService messageMQService;

    public Mono<Boolean> sendMessage(Message message, String routingKey){
     return messageMQService.send(message, routingKey);
    }
}

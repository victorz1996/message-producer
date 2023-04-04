package co.com.testrabbit.model.message.gateways;

import co.com.testrabbit.model.message.Message;
import reactor.core.publisher.Mono;

public interface MessageMQService {
    Mono<Boolean> send(Message message, String routingKey);
}
